package sd1920.trab1.server.resources;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientConfig;

import sd1920.trab1.api.User;
import sd1920.trab1.api.rest.MessageService;
import sd1920.trab1.api.rest.UserService;
import sd1920.trab1.server.Discovery;

@Singleton
public class UserResource implements UserService {

	private final Map<String, User> users;
	private final String domain;
	private final Discovery d;

	private static Logger Log = Logger.getLogger(UserResource.class.getName());

	public UserResource(String domain, Discovery d) {
		users = new HashMap<String, User>();
		this.domain = domain;
		this.d = d;
	}

	@Override
	public String postUser(User user) {
		if (user.getDisplayName() == null || user.getDisplayName().isEmpty() || user.getName() == null
				|| user.getName().isEmpty() || user.getPwd() == null || user.getPwd().isEmpty()
				|| user.getDomain() == null || user.getDomain().isEmpty() || users.containsKey(user.getName())) {
			Log.info("Message was rejected due to lack of recepients.");
			throw new WebApplicationException(Status.CONFLICT);
		}

		if (!user.getDomain().equals(domain)) {
			Log.info("The domain in the user does not match the domain of the server.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}
		synchronized (this) {
			users.put(user.getName(), user);
		}
		createInbox(user.getName());
		return user.getName() + "@" + user.getDomain();
	}

	@Override
	public User getUser(String name, String pwd) {
		User u = null;
		synchronized (this) {
			u = users.get(name);
		}
		if (u == null || pwd == null || !u.getPwd().equals(pwd)) {
			Log.info("Message was rejected due to lack of recepients.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}
		return u;
	}

	@Override
	public User updateUser(String name, String pwd, User user) {
		User u = null;
		synchronized (this) {
			u = users.get(name);
		}
		if (u == null || pwd == null || !u.getPwd().equals(pwd)) {
			Log.info("Message was rejected due to lack of recepients.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}
		if (user.getDisplayName() != null)
			u.setDisplayName(user.getDisplayName());

		if (user.getPwd() != null)
			u.setPwd(user.getPwd());

		synchronized (this) {
			users.put(name, u);
		}
		return u;
	}

	@Override
	public User deleteUser(String user, String pwd) {
		User u = null;
		synchronized (this) {
			u = users.get(user);
		}
		if (u == null || pwd == null || !u.getPwd().equals(pwd)) {
			Log.info("Message was rejected due to lack of recepients.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}
		removeInbox(user);
		synchronized (this) {
			u = users.remove(user);
		}
		return u;
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

	private void createInbox(String user) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(getServer(domain)).path(MessageService.PATH).path("mbox");

		Response r = target.path(user).request().post(Entity.json(""));

		if (r.getStatus() == Status.NO_CONTENT.getStatusCode())
			Log.info("Success, inbox created.");
		else
			Log.info("Error, HTTP error status: " + r.getStatus());
	}

	private void removeInbox(String user) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(getServer(domain)).path(MessageService.PATH).path("mbox");

		Response r = target.path(user).request().delete();

		if (r.getStatus() == Status.NO_CONTENT.getStatusCode())
			Log.info("Success, inbox deleted.");
		else
			Log.info("Error, HTTP error status: " + r.getStatus());
	}

}
