package sd1920.trab2.clients.rest;

import sd1920.trab2.api.User;
import sd1920.trab2.api.rest.UserService;
import sd1920.trab2.clients.EmailResponse;
import sd1920.trab2.clients.UsersEmailClientRep;
import sd1920.trab2.zookeeper.Operation;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.net.URI;
import java.util.List;

public class UserClientRest extends EmailClientRest implements UsersEmailClientRep {

	public UserClientRest(URI serverUrl, int maxRetries, int retryPeriod) {
		super(serverUrl, maxRetries, retryPeriod, UserService.PATH);
	}

	// Method to check if a user exists and the password is valid
	public EmailResponse<User> getUser(String name, String pwd) {
		// Calls the tryMultiple method of EmailClientRest to repeat the operation until
		// it is successful,
		// Also translates the response to an EmailResponse in order to unify SOAP and
		// REST responses
		return tryMultiple(() -> {
			Response response = target.path(name).queryParam("pwd", pwd).request().accept(MediaType.APPLICATION_JSON)
					.get();
			return EmailResponse.create(response.getStatus(), response.readEntity(User.class));
		});
	}

	@Override
	public EmailResponse<String> invokePostUser(User user, long version, String secret) {
		return tryMultiple(() -> {
			Response response = target.path("rep").path(String.valueOf(version)).queryParam("secret", secret).request()
					.accept(MediaType.APPLICATION_JSON).post(Entity.entity(user, MediaType.APPLICATION_JSON));
			return EmailResponse.create(response.getStatus(), response.readEntity(String.class));
		});
	}

	@Override
	public EmailResponse<User> invokeUpdateUser(String name, String pwd, User user, long version, String secret) {
		return tryMultiple(() -> {
			Response response = target.path("rep").path(String.valueOf(version)).path(name).queryParam("pwd", pwd)
					.queryParam("secret", secret).request().accept(MediaType.APPLICATION_JSON)
					.put(Entity.entity(user, MediaType.APPLICATION_JSON));
			return EmailResponse.create(response.getStatus(), response.readEntity(User.class));
		});
	}

	@Override
	public EmailResponse<User> invokeDeleteUser(String user, String pwd, long version, String secret) {
		return tryMultiple(() -> {
			Response response = target.path("rep").path(String.valueOf(version)).path(user).queryParam("pwd", pwd)
					.queryParam("secret", secret).request().accept(MediaType.APPLICATION_JSON).delete();
			return EmailResponse.create(response.getStatus(), response.readEntity(User.class));
		});
	}

	@Override
	public EmailResponse<List<Operation>> getOperations(long min, long max, String secret) {
		return tryMultiple(() -> {
			Response response = target.path("ops").path(String.valueOf(min)).path(String.valueOf(max))
					.queryParam("secret", secret).request().accept(MediaType.APPLICATION_JSON).get();
			return EmailResponse.create(response.getStatus(), response.readEntity(new GenericType<List<Operation>>() {
			}));
		});
	}

	@Override
	public EmailResponse<Long> getVersion(String secret) {
		return tryMultiple(() -> {
			Response response = target.path("version").queryParam("secret", secret).request()
					.accept(MediaType.APPLICATION_JSON).get();
			return EmailResponse.create(response.getStatus(), response.readEntity(Long.class));
		});
	}
}
