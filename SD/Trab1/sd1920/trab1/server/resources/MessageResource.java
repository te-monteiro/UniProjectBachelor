package sd1920.trab1.server.resources;

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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientConfig;

import sd1920.trab1.api.Message;
import sd1920.trab1.api.User;
import sd1920.trab1.api.rest.MessageService;
import sd1920.trab1.api.rest.UserService;
import sd1920.trab1.client.FakeClient;
import sd1920.trab1.clients.utils.MessageUtills;
import sd1920.trab1.server.Discovery;
import sd1920.trab1.server.resources.Request;

@Singleton
public class MessageResource implements MessageService {

	public final static int CONNECTION_TIMEOUT = 1000;
	public final static int REPLY_TIMEOUT = 600;

	private Random randomNumberGenerator;

	private final Map<Long, Message> allMessages;
	private final Map<String, Set<Long>> userInboxs;
	private final Map<String, BlockingQueue<Request>> requestsByServer;
	private final String domain;
	private final Discovery d;

	private static Logger Log = Logger.getLogger(MessageResource.class.getName());

	public MessageResource(String domain, Discovery d) {
		this.requestsByServer = new HashMap<String, BlockingQueue<Request>>();
		this.allMessages = new HashMap<Long, Message>();
		this.userInboxs = new HashMap<String, Set<Long>>();
		this.randomNumberGenerator = new Random(System.currentTimeMillis());
		this.domain = domain;
		this.d = d;
	}

	@Override
	public long postMessage(String pwd, Message msg) {
		Log.info("Received request to register a new message (Sender: " + msg.getSender() + "; Subject: "
				+ msg.getSubject() + ")");

		String senderName = msg.getSender().split("@")[0];
		User u = getUser(senderName, pwd);

		// Check if message is valid, if not return HTTP FORBIDDEN (403)
		synchronized (this) {
			if ((msg.getSender().contains("@") && !msg.getSender().split("@")[1].equals(domain))
					|| !userInboxs.containsKey(senderName)) {
				Log.info("Message was rejected due to invalid or inexistent password or user.");
				throw new WebApplicationException(Status.FORBIDDEN);
			}
		}

		if (u == null || msg.getDestination() == null || msg.getDestination().size() == 0) {
			Log.info("Message was rejected due to lack of recepients.");
			throw new WebApplicationException(Status.CONFLICT);
		}

		// Generate a new id for the message, that is not in use yet
		long newID = getNewID();
		// Add the message to the global list of messages
		msg.setId(newID);
		msg.setSender(String.format("%s <%s@%s>", u.getDisplayName(), u.getName(), u.getDomain()));

		synchronized (this) {
			allMessages.put(newID, msg);
			Log.info("Created new message with id: " + newID);
			MessageUtills.printMessage(allMessages.get(newID));
		}

		// Add the message (identifier) to the inbox of each recipient
		processLocalPost(msg);
		// Return the id of the registered message to the client (in the body of a HTTP
		// Response with 200)
		Log.info("Recorded message with identifier: " + newID);
		return newID;
	}

	private synchronized void processLocalPost(Message msg) {
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
				requestsByServer.get(dstDomain).add(new Request(msg.getId(), true));
				requestedDomain.add(dstDomain);
			}
		}

	}

	@Override
	public synchronized List<String> postMessageOutside(Message msg) {
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

		User u = getUser(user, pwd);
		if (u == null) {
			Log.info("Request was rejected due to invalid or inexistent password or user.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}

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

		User u = getUser(user, pwd);
		if (u == null) {
			Log.info("Request was rejected due to invalid or inexistent password or user.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}
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

		User u = getUser(user, pwd);
		if (u == null) {
			Log.info("Request was rejected due to invalid or inexistent password or user.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}

		Message m = null;
		synchronized (this) {
			m = allMessages.get(mid);
		}
		if (m != null && m.getSender().split("<")[1].split("@")[0].equals(user))
			processDelete(mid);
	}

	@Override
	public synchronized void processDelete(long mid) {
		Message m = allMessages.remove(mid);
		List<String> requestedDomains = new ArrayList<>();
		for (String recipient : m.getDestination()) {
			String dstDomain = recipient.split("@")[1];
			String dstName = recipient.split("@")[0];
			if (dstDomain.equals(domain))
				userInboxs.get(dstName).remove(mid);
			// dst is outside, sender is local and we want to add the request to the waiting
			// queue of the dstDomain
			else if (m.getSender().split(">")[0].split("@")[1].equals(domain)
					&& !requestedDomains.contains(dstDomain)) {
				if (!requestsByServer.containsKey(dstDomain)) {
					requestsByServer.put(dstDomain, new LinkedBlockingQueue<Request>());
					addThreadToDomain(dstDomain);
				}
				requestedDomains.add(dstDomain);
				requestsByServer.get(dstDomain).add(new Request(mid, false));
			}
		}
	}

	@Override
	public void removeFromUserInbox(String user, long mid, String pwd) {
		Log.info("Received request to delete the message with id: " + mid + ", from the inbox of the user: " + user
				+ ".");

		User u = getUser(user, pwd);
		if (u == null) {
			Log.info("Request was rejected due to invalid or inexistent password or user.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}
		Set<Long> msgs = null;
		synchronized (this) {
			msgs = userInboxs.get(user);
			if (msgs != null && msgs.contains(mid)) {
				msgs.remove(mid);
			} else
				throw new WebApplicationException(Status.NOT_FOUND);
		}

	}

	@Override
	public synchronized void postInbox(String user) {
		userInboxs.put(user, new HashSet<Long>());
	}

	@Override
	public synchronized void removeInbox(String user) {
		userInboxs.remove(user);
	}

	private void addThreadToDomain(String dstDomain) {
		new Thread(() -> {
			for (;;) {
				try {
					// Safe access the requestsByServer guaranteed by the synchronized method
					// processLocalPost and processDelete (places where this method is invoked)
					Request r = requestsByServer.get(dstDomain).take();
					String serverUrl = this.getServer(dstDomain);
					if (serverUrl != null) {
						FakeClient c = new FakeClient(serverUrl);
						if (r.isPost())
							this.processPostRequest(dstDomain, r, c);
						else
							c.deleteMessageOutsideClient(r.getMid());
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

	private void processPostRequest(String dstDomain, Request r, FakeClient c) {
		Message m = null;
		synchronized (this) {
			m = allMessages.get(r.getMid());
		}
		List<String> unavailable = c.postMessage(m);
		for (String user : unavailable)
			this.postErrorMessage(m, user);
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

	/**
	 * REST Request (Client Method)
	 */
	private User getUser(String name, String pwd) {
		// execute a rest request to ourselves
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(getServer(domain)).path(UserService.PATH);

		Response r = target.path(name).queryParam("pwd", pwd).request().accept(MediaType.APPLICATION_JSON).get();

		User u = null;
		if (r.getStatus() == Status.OK.getStatusCode() && r.hasEntity())
			u = r.readEntity(User.class);
		else
			throw new WebApplicationException(Status.FORBIDDEN);
		return u;
	}

}
