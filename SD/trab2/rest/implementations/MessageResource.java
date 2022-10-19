package sd1920.trab2.rest.implementations;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import sd1920.trab2.api.Message;
import sd1920.trab2.api.User;
import sd1920.trab2.api.rest.MessageService;
import sd1920.trab2.clients.ClientFactory;
import sd1920.trab2.clients.MessagesEmailClient;
import sd1920.trab2.clients.UsersEmailClient;
import sd1920.trab2.clients.utils.MessageUtills;
import sd1920.trab2.server.Discovery;
import sd1920.trab2.server.Request;

@Singleton
public class MessageResource implements MessageService {

	public final static int MAX_RETRIES = 3;
	public final static int RETRY_PERIOD = 1000;

	private Random randomNumberGenerator;

	private final Map<Long, Message> allMessages;
	private final Map<String, Set<Long>> userInboxs;
	private final Map<String, BlockingQueue<Request>> requestsByServer;
	private final Map<String, MessagesEmailClient> clients;
	private final String domain;
	private final String internalSecret;
	private final Discovery d;
	private final UsersEmailClient users;

	private static Logger Log = Logger.getLogger(MessageResource.class.getName());

	public MessageResource(String domain, URI self, Discovery d, String secret) {
		this.requestsByServer = new HashMap<String, BlockingQueue<Request>>();
		this.allMessages = new HashMap<Long, Message>();
		this.clients = new HashMap<String, MessagesEmailClient>();
		this.userInboxs = new HashMap<String, Set<Long>>();
		this.randomNumberGenerator = new Random(System.currentTimeMillis());
		this.domain = domain;
		this.d = d;
		this.internalSecret = secret;
		users = ClientFactory.getUsersClient(self, MAX_RETRIES, RETRY_PERIOD);
	}

	@Override
	public long postMessage(String pwd, Message msg) {
		Log.info("Received request to register a new message (Sender: " + msg.getSender() + "; Subject: "
				+ msg.getSubject() + ")");

		long newID = checkPostMessage(pwd, msg);
		msg.setId(newID);
		// Add the message (identifier) to the inbox of each recipient
		processLocalPost(msg);
		// Return the id of the registered message to the client (in the body of a HTTP
		// Response with 200)
		Log.info("Recorded message with identifier: " + newID);
		return newID;
	}

	@Override
	public synchronized List<String> postMessageOutside(Message msg, String secret) {

		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		List<String> unavailable = new ArrayList<>();
		allMessages.put(msg.getId(), msg);
		Log.info("Created new message with id: " + msg.getId());
		MessageUtills.printMessage(allMessages.get(msg.getId()));
		for (String recipient : msg.getDestination()) {
			String dstDomain = recipient.split("@")[1];
			String dstName = recipient.split("@")[0];
			if (dstDomain.equals(domain))
				if (userInboxs.containsKey(dstName))
					userInboxs.get(dstName).add(msg.getId());
				else
					unavailable.add(recipient);
		}
		return unavailable;

	}

	@Override
	public Message getMessage(String user, long mid, String pwd) {
		Log.info("Received request for message with id: " + mid + ".");

		checkUser(user, pwd);

		Message m = null;

		synchronized (this) {
			if (!userInboxs.get(user).contains(mid)) { // check if message exists
				Log.info("Requested message does not exists.");
				throw new WebApplicationException(Status.NOT_FOUND); // if not send HTTP 404 back to client
			}
			m = allMessages.get(mid);
		}

		Log.info("Returning requested message to user.");
		return m;
	}

	@Override
	public List<Long> getMessages(String user, String pwd) {
		Log.info("Received request for messages with optional user parameter set to: '" + user + "'");
		List<Long> messages = new ArrayList<Long>();

		checkUser(user, pwd);
		Log.info("Collecting all messages in server for user " + user);
		Set<Long> mids = null;
		synchronized (this) {
			mids = userInboxs.getOrDefault(user, Collections.emptySet());
		}
		for (Long l : mids)
			messages.add(l);
		Log.info("Returning message list to user with " + messages.size() + " messages.");
		return messages;
	}

	@Override
	public void deleteMessage(String user, long mid, String pwd) {
		Log.info("Received request to delete the message with id: " + mid + ".");

		checkUser(user, pwd);

		deleteMessageOp(user, mid);
	}

	@Override
	public synchronized void processDelete(long mid, String secret) {

		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		Message m = allMessages.remove(mid);
		for (String recipient : m.getDestination()) {
			String dstDomain = recipient.split("@")[1];
			String dstName = recipient.split("@")[0];
			if (dstDomain.equals(domain))
				userInboxs.get(dstName).remove(mid);
		}
	}

	@Override
	public void removeFromUserInbox(String user, long mid, String pwd) {
		Log.info("Received request to delete the message with id: " + mid + ", from the inbox of the user: " + user
				+ ".");

		checkUser(user, pwd);
		removeFromUserInboxOp(user, mid);

	}

	@Override
	public synchronized void postInbox(String user, String secret) {

		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		userInboxs.put(user, new HashSet<Long>());
	}

	@Override
	public synchronized void removeInbox(String user, String secret) {

		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		userInboxs.remove(user);
	}

	public long checkPostMessage(String pwd, Message msg) {
		String senderName = msg.getSender().split("@")[0];
		User u = getUser(senderName, pwd);

		// Check if message is valid, if not return HTTP FORBIDDEN (403)
		synchronized (this) {
			if (u == null || !userInboxs.containsKey(senderName)) {
				Log.info("Message was rejected due to invalid or inexistent password or user.");
				throw new WebApplicationException(Status.FORBIDDEN);
			}
		}

		if (msg.getDestination() == null || msg.getDestination().size() == 0) {
			Log.info("Message was rejected due to lack of recepients.");
			throw new WebApplicationException(Status.CONFLICT);
		}

		// Generate a new id for the message, that is not in use yet
		long newID = getNewID();
		// Add the message to the global list of messages
		msg.setId(newID);
		msg.setSender(String.format("%s <%s@%s>", u.getDisplayName(), u.getName(), u.getDomain()));
		return newID;
	}

	public synchronized void processLocalPost(Message msg) {
		allMessages.put(msg.getId(), msg);
		Log.info("Created new message with id: " + msg.getId());
		MessageUtills.printMessage(allMessages.get(msg.getId()));

		List<String> requestedDomain = new ArrayList<String>();
		for (String recipient : msg.getDestination()) {
			String dstDomain = recipient.split("@")[1];
			String dstName = recipient.split("@")[0];
			if (dstDomain.equals(domain)) {
				if (userInboxs.containsKey(dstName))
					userInboxs.get(dstName).add(msg.getId());
				else
					this.postErrorMessage(msg, recipient);
			} else if (!requestedDomain.contains(dstDomain)) {
				// add outside requests to waiting queue
				if (!requestsByServer.containsKey(dstDomain)) {
					requestsByServer.put(dstDomain, new LinkedBlockingQueue<Request>());
					addThreadToDomain(dstDomain);
				}
				requestsByServer.get(dstDomain).add(new Request(msg, true));
				requestedDomain.add(dstDomain);
			}
		}

	}

	public void deleteMessageOp(String user, long mid) {
		Message m = null;
		synchronized (this) {
			m = allMessages.get(mid);
		}
		if (m != null && m.getSender().split("<")[1].split("@")[0].equals(user))
			processLocalDelete(mid);
	}

	public void checkUser(String user, String pwd) {
		User u = getUser(user, pwd);
		if (u == null) {
			Log.info("Request was rejected due to invalid or inexistent password or user.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}
	}

	private synchronized void processLocalDelete(long mid) {
		// so devia ser replicado quando veio de um dominio exterior
		Message m = allMessages.remove(mid);
		List<String> requestedDomains = new ArrayList<>();
		for (String recipient : m.getDestination()) {
			String dstDomain = recipient.split("@")[1];
			String dstName = recipient.split("@")[0];
			if (dstDomain.equals(domain))
				userInboxs.get(dstName).remove(mid);
			// dst is outside, sender is local and we want to add the request to the waiting
			// queue of the dstDomain
			else if (!requestedDomains.contains(dstDomain)) {
				if (!requestsByServer.containsKey(dstDomain)) {
					requestsByServer.put(dstDomain, new LinkedBlockingQueue<Request>());
					addThreadToDomain(dstDomain);
				}
				requestedDomains.add(dstDomain);
				requestsByServer.get(dstDomain).add(new Request(m, false));
			}
		}
	}

	private void removeFromUserInboxOp(String user, long mid) {
		Set<Long> msgs = null;
		synchronized (this) {
			msgs = userInboxs.get(user);
			if (msgs != null && msgs.contains(mid)) {
				msgs.remove(mid);
			} else
				throw new WebApplicationException(Status.NOT_FOUND);
		}
	}

	private void addThreadToDomain(String dstDomain) {
		new Thread(() -> {
			for (;;) {
				try {
					Request r = requestsByServer.get(dstDomain).take();
					String serverUrl = this.getServer(dstDomain);
					if (serverUrl != null) {
						MessagesEmailClient m = clients.get(serverUrl);
						if (m == null)
							clients.put(serverUrl, m = ClientFactory.getMessagesClient(URI.create(serverUrl),
									MAX_RETRIES, RETRY_PERIOD));
						if (r.isPost())
							this.processPostRequest(dstDomain, r, m);
						else
							m.forwardDeleteSentMessage(r.getMessage().getId(), internalSecret);
					}
				} catch (InterruptedException e) {
				}
			}
		}).start();
	}

	private String getServer(String domain) {
		URI[] uris = d.knownUrisOf(domain);
		try {
			return uris[0].toURL().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Generate a new id for the message, that is not in use yet
	private synchronized long getNewID() {
		long newID;
		newID = Math.abs(randomNumberGenerator.nextLong());
		while (allMessages.containsKey(newID)) {
			newID = Math.abs(randomNumberGenerator.nextLong());
		}

		return newID;
	}

	private void processPostRequest(String dstDomain, Request r, MessagesEmailClient m) {
		List<String> unavailable = m.forwardSendMessage(r.getMessage(), internalSecret).getEntity();
		for (String user : unavailable)
			this.postErrorMessage(r.getMessage(), user);
	}

	private void postErrorMessage(Message msg, String dst) {
		long newID = getNewID();
		// Add the message to the global list of messages
		String subject = String.format("FALHA NO ENVIO DE %d PARA %s", msg.getId(), dst);
		Message errorMessage = new Message(newID, msg.getSender(), msg.getDestination(), subject, msg.getContents());
		String senderName = msg.getSender().split("<")[1].split("@")[0];
		synchronized (this) {
			if (userInboxs.containsKey(senderName)) {
				allMessages.put(newID, errorMessage);
				userInboxs.get(senderName).add(newID);
			}
		}
	}

	private User getUser(String name, String pwd) {
		return users.getUser(name, pwd).getEntity();
	}

}
