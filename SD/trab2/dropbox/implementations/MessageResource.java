package sd1920.trab2.dropbox.implementations;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

import sd1920.trab2.api.Message;
import sd1920.trab2.api.User;
import sd1920.trab2.api.rest.MessageService;
import sd1920.trab2.clients.ClientFactory;
import sd1920.trab2.clients.MessagesEmailClient;
import sd1920.trab2.dropbox.CheckUser;
import sd1920.trab2.dropbox.DeleteFile;
import sd1920.trab2.dropbox.GetFile;
import sd1920.trab2.dropbox.ListDirectory;
import sd1920.trab2.dropbox.UploadFile;
import sd1920.trab2.server.Discovery;
import sd1920.trab2.server.Request;

@Singleton
public class MessageResource implements MessageService {

	public final static int MAX_RETRIES = 3;
	public final static int RETRY_PERIOD = 1000;

	private Random randomNumberGenerator;

	private final Map<String, BlockingQueue<Request>> requestsByServer;
	private final String domain;
	private final String internalSecret;
	private final Discovery d;
	private final Map<String, MessagesEmailClient> clients;
	private OAuth20Service service;
	private OAuth2AccessToken accessToken;

	private static Logger Log = Logger.getLogger(MessageResource.class.getName());

	public MessageResource(String domain, Discovery d, String secret, OAuth20Service service,
			OAuth2AccessToken accessToken) {
		this.requestsByServer = new HashMap<String, BlockingQueue<Request>>();
		this.randomNumberGenerator = new Random(System.currentTimeMillis());
		this.clients = new HashMap<String, MessagesEmailClient>();
		this.domain = domain;
		this.d = d;
		this.internalSecret = secret;
		this.service = service;
		this.accessToken = accessToken;
	}

	@Override
	public long postMessage(String pwd, Message msg) {
		Log.info("Received request to register a new message (Sender: " + msg.getSender() + "; Subject: "
				+ msg.getSubject() + ")");

		String senderName = msg.getSender().split("@")[0];
		User u = getUser(senderName, pwd);

		// Check if message is valid, if not return HTTP FORBIDDEN (403)
		synchronized (this) {
			if (u == null) {
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

		UploadFile up = new UploadFile(service, accessToken);
		up.execute("/" + domain + "/" + newID + ".txt", msg);
		Log.info("Created new message with id: " + newID);

		// Add the message (identifier) to the inbox of each recipient
		processLocalPost(msg);
		// Return the id of the registered message to the client (in the body of a HTTP
		// Response with 200)
		Log.info("Recorded message with identifier: " + newID);
		return newID;
	}

	private void processLocalPost(Message msg) {
		List<String> requestedDomain = new ArrayList<String>();
		for (String recipient : msg.getDestination()) {
			String dstDomain = recipient.split("@")[1];
			String dstName = recipient.split("@")[0];
			if (dstDomain.equals(domain)) {
				CheckUser check = new CheckUser(service, accessToken);
				if (check.execute("/" + domain + "/" + dstName)) {
					UploadFile up = new UploadFile(service, accessToken);
					up.execute("/" + domain + "/" + dstName + "/" + msg.getId() + ".txt", msg);
				} else
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

	@Override
	public List<String> postMessageOutside(Message msg, String secret) {
		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		List<String> unavailable = new ArrayList<>();
		UploadFile up = new UploadFile(service, accessToken);
		up.execute("/" + domain + "/" + msg.getId() + ".txt", msg);
		Log.info("Created new message with id: " + msg.getId());
		for (String recipient : msg.getDestination()) {
			String dstDomain = recipient.split("@")[1];
			String dstName = recipient.split("@")[0];
			if (dstDomain.equals(domain)) {
				CheckUser check = new CheckUser(service, accessToken);
				if (check.execute("/" + domain + "/" + dstName))
					up.execute("/" + domain + "/" + dstName + "/" + msg.getId() + ".txt", msg);
				else
					unavailable.add(recipient);
			}
		}
		return unavailable;

	}

	@Override
	public Message getMessage(String name, long mid, String pwd) {
		Log.info("Received request for message with id: " + mid + ".");

		User u = getUser(name, pwd);
		if (u == null) {
			Log.info("Request was rejected due to invalid or inexistent password or user.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}

		Message m = null;
		GetFile gf = new GetFile(service, accessToken);

		if ((m = gf.executeMsg("/" + domain + "/" + name + "/" + mid + ".txt")) != null) {
			Log.info("Returning requested message to user.");
			return m;
		}
		Log.info("Requested message does not exists.");
		throw new WebApplicationException(Status.NOT_FOUND);
	}

	@Override
	public List<Long> getMessages(String name, String pwd) {
		Log.info("Received request for messages with optional user parameter set to: '" + name + "'");
		List<Long> messages = new ArrayList<Long>();

		User u = getUser(name, pwd);
		if (u == null) {
			Log.info("Request was rejected due to invalid or inexistent password or user.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}
		Log.info("Collecting all messages in server for user " + name);
		ListDirectory ld = new ListDirectory(service, accessToken);
		Log.info("Returning message list to user with " + messages.size() + " messages.");
		return ld.execute(name, "/" + domain + "/" + name);
	}

	@Override
	public void deleteMessage(String user, long mid, String pwd) {
		Log.info("Received request to delete the message with id: " + mid + ".");

		User u = getUser(user, pwd);
		if (u == null) {
			Log.info("Request was rejected due to invalid or inexistent password or user.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}

		GetFile gf = new GetFile(service, accessToken);
		Message m = gf.executeMsg("/" + domain + "/" + mid + ".txt");
		if (m != null) {
			if (m.getSender().split("<")[1].split("@")[0].equals(user))
				processDelete(mid, internalSecret);
		}
	}

	@Override
	public void processDelete(long mid, String secret) {

		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		GetFile gf = new GetFile(service, accessToken);
		Message m = gf.executeMsg("/" + domain + "/" + mid + ".txt");
		DeleteFile df = new DeleteFile(service, accessToken);
		if (m != null)
			df.execute("/" + domain + "/" + mid + ".txt");
		List<String> requestedDomains = new ArrayList<>();
		for (String recipient : m.getDestination()) {
			String dstDomain = recipient.split("@")[1];
			String dstName = recipient.split("@")[0];
			if (dstDomain.equals(domain))
				df.execute("/" + domain + "/" + dstName + "/" + mid + ".txt");
			// dst is outside, sender is local and we want to add the request to the waiting
			// queue of the dstDomain
			else if (m.getSender().split(">")[0].split("@")[1].equals(domain)
					&& !requestedDomains.contains(dstDomain)) {
				if (!requestsByServer.containsKey(dstDomain)) {
					requestsByServer.put(dstDomain, new LinkedBlockingQueue<Request>());
					addThreadToDomain(dstDomain);
				}
				requestedDomains.add(dstDomain);
				requestsByServer.get(dstDomain).add(new Request(m, false));
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
		GetFile gf = new GetFile(service, accessToken);
		Message m = gf.executeMsg("/" + domain + "/" + user + "/" + mid + ".txt");
		if (m != null) {
			DeleteFile df = new DeleteFile(service, accessToken);
			df.execute("/" + domain + "/" + user + "/" + mid + ".txt");
		} else
			throw new WebApplicationException(Status.NOT_FOUND);

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
	private long getNewID() {
		long newID;
		newID = Math.abs(randomNumberGenerator.nextLong());
		GetFile gf = new GetFile(service, accessToken);
		Message m = gf.executeMsg("/" + domain + "/" + newID + ".txt");
		while (m != null) {
			newID = Math.abs(randomNumberGenerator.nextLong());
			m = gf.executeMsg("/" + domain + "/" + newID + ".txt");
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
		CheckUser check = new CheckUser(service, accessToken);
		if (check.execute("/" + domain + "/" + senderName)) {
			UploadFile up = new UploadFile(service, accessToken);
			up.execute("/" + domain + "/" + newID + ".txt", errorMessage);
			up.execute("/" + domain + "/" + senderName + "/" + newID + ".txt", errorMessage);
		}

	}

	private User getUser(String name, String pwd) {
		GetFile gf = new GetFile(service, accessToken);
		User u = gf.execute("/" + domain + "/" + name + "/" + name + ".txt");

		if (u == null || pwd == null || !u.getPwd().equals(pwd)) {
			Log.info("Message was rejected due to lack of recepients.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}
		return u;
	}

	@Override
	public void postInbox(String user, String secret) {
		// never called in proxy server
		throw new WebApplicationException(Status.NOT_IMPLEMENTED);
	}

	@Override
	public void removeInbox(String user, String secret) {
		// never called in proxy server
		throw new WebApplicationException(Status.NOT_IMPLEMENTED);
	}

}
