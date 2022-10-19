package sd1920.trab2.clients.rest;

import sd1920.trab2.api.Message;
import sd1920.trab2.api.rest.MessageService;
import sd1920.trab2.clients.EmailResponse;
import sd1920.trab2.clients.MessagesEmailClientRep;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

public class MessageClientRest extends EmailClientRest implements MessagesEmailClientRep {

	public MessageClientRest(URI serverUrl, int maxRetries, int retryPeriod) {
		super(serverUrl, maxRetries, retryPeriod, MessageService.PATH);
	}

	// These method work like in UserClientRest, using "tryMultiple" to retry the
	// operation until it is successful,
	// and then translating the result to an EmailResponse
	public EmailResponse<Void> deleteUserInfo(String user, String secret) {
		return tryMultiple(() -> {
			Response delete = target.path("mbox").path(user).queryParam("secret", secret).request().delete();
			return EmailResponse.create(delete.getStatus());
		});
	}

	public EmailResponse<Void> setupUserInfo(String user, String secret) {
		return tryMultiple(() -> {
			Response post = target.path("mbox").path(user).queryParam("secret", secret).request().post(Entity.json(""));
			return EmailResponse.create(post.getStatus());

		});
	}

	public EmailResponse<List<String>> forwardSendMessage(Message msg, String secret) {
		return tryMultiple(() -> {
			Response post = target.path("ext").queryParam("secret", secret).request()
					.post(Entity.entity(msg, MediaType.APPLICATION_JSON));
			return EmailResponse.create(post.getStatus(), post.readEntity(new GenericType<List<String>>() {
			}));
		});

	}

	public EmailResponse<Void> forwardDeleteSentMessage(long mid, String secret) {
		return tryMultiple(() -> {
			Response delete = target.path("ext").path("msg").path(String.valueOf(mid)).queryParam("secret", secret)
					.request().delete();
			return EmailResponse.create(delete.getStatus());

		});
	}

	public EmailResponse<Void> invokePostMessage(String pwd, Message msg, long version, String secret) {
		return tryMultiple(() -> {
			Response post = target.path("rep").path(String.valueOf(version)).queryParam("pwd", pwd)
					.queryParam("secret", secret).request().accept(MediaType.APPLICATION_JSON)
					.post(Entity.entity(msg, MediaType.APPLICATION_JSON));
			return EmailResponse.create(post.getStatus());

		});
	}

	@Override
	public EmailResponse<Void> invokePostMessageOutside(Message msg, long version, String secret) {
		return tryMultiple(() -> {
			Response post = target.path("rep/ext").path(String.valueOf(version)).queryParam("secret", secret).request()
					.accept(MediaType.APPLICATION_JSON).post(Entity.entity(msg, MediaType.APPLICATION_JSON));
			return EmailResponse.create(post.getStatus());

		});
	}

	@Override
	public EmailResponse<Void> invokeDeleteMessage(String user, long mid, String pwd, long version, String secret) {
		return tryMultiple(() -> {
			Response delete = target.path("rep/msg").path(String.valueOf(version)).path(user).path("" + mid)
					.queryParam("pwd", pwd).queryParam("secret", secret).request().delete();

			return EmailResponse.create(delete.getStatus());

		});
	}

	@Override
	public EmailResponse<Void> invokeProcessDelete(long mid, long version, String secret) {
		return tryMultiple(() -> {
			Response delete = target.path("rep/ext/msg").path(String.valueOf(version)).path("" + mid)
					.queryParam("secret", secret).request().delete();
			return EmailResponse.create(delete.getStatus());

		});
	}

	@Override
	public EmailResponse<Void> invokeRemoveFromUserInbox(String user, long mid, String pwd, long version,
			String secret) {
		return tryMultiple(() -> {
			Response delete = target.path("rep/mbox").path(String.valueOf(version)).path(user).path("" + mid)
					.queryParam("pwd", pwd).queryParam("secret", secret).request().delete();
			return EmailResponse.create(delete.getStatus());

		});
	}
}
