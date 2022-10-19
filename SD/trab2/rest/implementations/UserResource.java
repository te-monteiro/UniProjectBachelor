package sd1920.trab2.rest.implementations;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import sd1920.trab2.api.User;
import sd1920.trab2.api.rest.UserService;
import sd1920.trab2.clients.ClientFactory;
import sd1920.trab2.clients.MessagesEmailClient;

@Singleton
public class UserResource implements UserService {

	public final static int MAX_RETRIES = 3;
	public final static int RETRY_PERIOD = 1000;

	private final Map<String, User> users;
	private final String domain;
	private final String internalSecret;
	private final MessagesEmailClient msgClient;

	private static Logger Log = Logger.getLogger(UserResource.class.getName());

	public UserResource(String domain, URI self, String secret) {
		users = new HashMap<String, User>();
		this.domain = domain;
		msgClient = ClientFactory.getMessagesClient(self, MAX_RETRIES, RETRY_PERIOD);
		this.internalSecret = secret;
	}

	@Override
	public String postUser(User user) {
		checkPostUser(user);
		return postUserOp(user);
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
		User u = checkUpdateUser(name, pwd, user);
		return updateUserOp(name, u);
	}
	
	@Override
	public User deleteUser(String user, String pwd) {
		checkDeleteUser(user, pwd);
		return deleteUserOp(user);
	}

	public String postUserOp(User user) {
		synchronized (this) {
			users.put(user.getName(), user);
		}
		msgClient.setupUserInfo(user.getName(), internalSecret);
		return user.getName() + "@" + user.getDomain();
	}

	public void checkPostUser(User user) {
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
	}

	public User updateUserOp(String name, User u) {
		synchronized (this) {
			users.put(name, u);
		}
		return u;
	}

	public User checkUpdateUser(String name, String pwd, User user) {
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
		return u;
	}

	public User deleteUserOp(String user) {
		User u;
		synchronized (this) {
			u = users.remove(user);
		}
		msgClient.deleteUserInfo(user, internalSecret);
		return u;
	}

	public void checkDeleteUser(String user, String pwd) {
		User u = null;
		synchronized (this) {
			u = users.get(user);
		}
		if (u == null || pwd == null || !u.getPwd().equals(pwd)) {
			Log.info("Message was rejected due to lack of recepients.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}
	}

}
