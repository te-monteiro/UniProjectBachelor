package sd1920.trab2.dropbox.implementations;

import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

import sd1920.trab2.api.User;
import sd1920.trab2.api.rest.UserService;
import sd1920.trab2.dropbox.CreateDirectory;
import sd1920.trab2.dropbox.DeleteFile;
import sd1920.trab2.dropbox.GetFile;
import sd1920.trab2.dropbox.UploadFile;

@Singleton
public class UserResource implements UserService {

	private final String domain;
	private final OAuth20Service service;
	private final OAuth2AccessToken accessToken;

	private static Logger Log = Logger.getLogger(UserResource.class.getName());

	public UserResource(String domain, OAuth20Service service, OAuth2AccessToken accessToken) {
		this.domain = domain;
		this.service = service;
		this.accessToken = accessToken;
	}

	@Override
	public String postUser(User user) {
		if (user.getDisplayName() == null || user.getDisplayName().isEmpty() || user.getName() == null
				|| user.getName().isEmpty() || user.getPwd() == null || user.getPwd().isEmpty()
				|| user.getDomain() == null || user.getDomain().isEmpty()) {
			Log.info("Message was rejected due to lack of recepients.");
			throw new WebApplicationException(Status.CONFLICT);
		}

		if (!user.getDomain().equals(domain)) {
			Log.info("The domain in the user does not match the domain of the server.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}
		CreateDirectory cd = new CreateDirectory(service, accessToken);
		if (cd.execute("/" + domain + "/" + user.getName())) {
			UploadFile up = new UploadFile(service, accessToken);
			if (up.execute("/" + domain + "/" + user.getName() + "/" + user.getName() + ".txt", user))
				return user.getName() + "@" + user.getDomain();
		}
		throw new WebApplicationException(Status.CONFLICT);
	}

	@Override
	public User getUser(String name, String pwd) {
		GetFile gf = new GetFile(service, accessToken);
		User u = gf.execute("/" + domain + "/" + name + "/" + name + ".txt");

		if (u == null || pwd == null || !u.getPwd().equals(pwd)) {
			Log.info("Message was rejected due to lack of recepients.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}
		return u;
	}

	@Override
	public User updateUser(String name, String pwd, User user) {
		User u = this.getUser(name, pwd);
		if (u == null || pwd == null || !u.getPwd().equals(pwd)) {
			Log.info("Message was rejected due to lack of recepients.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}
		if (user.getDisplayName() != null)
			u.setDisplayName(user.getDisplayName());

		if (user.getPwd() != null)
			u.setPwd(user.getPwd());

		UploadFile up = new UploadFile(service, accessToken);
		up.execute("/" + domain + "/" + name + "/" + name + ".txt", u);
		return u;
	}

	@Override
	public User deleteUser(String name, String pwd) {
		User u = getUser(name, pwd);

		if (u == null || pwd == null || !u.getPwd().equals(pwd)) {
			Log.info("Message was rejected due to lack of recepients.");
			throw new WebApplicationException(Status.FORBIDDEN);
		}

		DeleteFile du = new DeleteFile(service, accessToken);

		if (du.execute(("/" + domain + "/" + name)))
			return u;
		return null;
	}

}
