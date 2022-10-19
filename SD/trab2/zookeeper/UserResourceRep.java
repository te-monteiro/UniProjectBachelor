package sd1920.trab2.zookeeper;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import sd1920.trab2.api.User;
import sd1920.trab2.api.restRep.MessageService;
import sd1920.trab2.api.restRep.UserService;
import sd1920.trab2.rest.implementations.UserResource;

@Singleton
public class UserResourceRep implements UserService {

	private static final String REDIRECT_UPDATE_USER = "%s%s/%s/?pwd=%s";
	private static final String REDIRECT_DELETE_USER = "%s%s/%s/?pwd=%s";
	private final String internalSecret;
	private final Sync sync;
	private final UserResource resource;

	public UserResourceRep(String domain, Sync sync, String secret) {
		resource = new UserResource(domain, URI.create(sync.getServerURI()), secret);
		this.sync = sync;
		this.internalSecret = secret;
	}

	@Override
	public String postUser(Long version, User user) {
		if (sync.isLeader()) {

			resource.checkPostUser(user);

			sync.replicate(new Operation(internalSecret).postUser(user));
			return this.makePostUser(sync.getVersion(), user, internalSecret);
		} else
			throw new WebApplicationException(
					Response.temporaryRedirect(URI.create(sync.getLeader() + UserService.PATH)).build());
	}

	public String makePostUser(long version, User user, String secret) {

		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		sync.waitForVersion(version - 1);

		resource.postUserOp(user);

		sync.addHistory(new Operation(internalSecret).postUser(user));
		sync.done();

		throw new WebApplicationException(
				Response.status(Status.OK.getStatusCode()).header(MessageService.HEADER_VERSION, sync.getVersion())
						.entity(user.getName() + "@" + user.getDomain()).build());

	}

	@Override
	public User getUser(Long version, String name, String pwd) {
		if (version != null) {
			sync.waitForVersion(version);
		}
		return this.makeGetUser(name, pwd, internalSecret);

	}

	@Override
	public User makeGetUser(String name, String pwd, String secret) {

		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		return resource.getUser(name, pwd);
	}

	@Override
	public User updateUser(Long version, String name, String pwd, User user) {
		if (sync.isLeader()) {

			User u = resource.checkUpdateUser(name, pwd, user);

			sync.replicate((new Operation(internalSecret)).updateUser(name, pwd, u));
			return this.makeUpdateUser(sync.getVersion(), name, pwd, u, internalSecret);
		} else {
			throw new WebApplicationException(Response
					.temporaryRedirect(URI
							.create(String.format(REDIRECT_UPDATE_USER, sync.getLeader(), UserService.PATH, name, pwd)))
					.build());
		}
	}

	@Override
	public User makeUpdateUser(long version, String name, String pwd, User user, String secret) {

		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		sync.waitForVersion(version - 1);

		resource.updateUserOp(name, user);

		sync.addHistory((new Operation(internalSecret)).updateUser(name, pwd, user));
		sync.done();

		throw new WebApplicationException(Response.status(Status.OK.getStatusCode())
				.header(MessageService.HEADER_VERSION, sync.getVersion()).entity(user).build());
	}

	@Override
	public User deleteUser(Long version, String name, String pwd) {
		if (sync.isLeader()) {

			resource.checkDeleteUser(name, pwd);

			sync.replicate((new Operation(internalSecret)).deleteUser(name, pwd));
			return this.makeDeleteUser(sync.getVersion(), name, pwd, internalSecret);
		} else
			throw new WebApplicationException(Response
					.temporaryRedirect(URI
							.create(String.format(REDIRECT_DELETE_USER, sync.getLeader(), UserService.PATH, name, pwd)))
					.build());
	}

	@Override
	public User makeDeleteUser(long version, String user, String pwd, String secret) {

		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		sync.waitForVersion(version);

		User u = resource.deleteUserOp(user);

		sync.addHistory((new Operation(internalSecret)).deleteUser(user, pwd));
		sync.done();

		throw new WebApplicationException(Response.status(Status.OK.getStatusCode())
				.header(MessageService.HEADER_VERSION, sync.getVersion()).entity(u).build());
	}

	@Override
	public List<Operation> getOperations(long min, long max, String secret) {

		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		List<Operation> aux = new LinkedList<>();
		List<Operation> ops = sync.getHistory();
		for (int i = (int) min + 1; i <= max; i++)
			aux.add(ops.get(i));
		return aux;
	}

	@Override
	public long getVersion(String secret) {
		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);
		return sync.getVersion();
	}

}
