package sd1920.trab2.zookeeper;

import java.net.URI;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import sd1920.trab2.api.Message;
import sd1920.trab2.api.restRep.MessageService;
import sd1920.trab2.rest.implementations.MessageResource;
import sd1920.trab2.server.Discovery;

@Singleton
public class MessageResourceRep implements MessageService {
	private static final String REDIRECT_REMOVE_FROM_INBOX = "%s%s/mbox/%s/%s/?pwd=%s";
	private static final String REDIRECT_DELETE_MSG_OUTSIDE = "%s%s/ext/msg/%s/?secret=%s";
	private static final String REDIRECT_DELETE_MSG = "%s%s/msg/%s/%s/?pwd=%s";
	private static final String REDIRECT_POST_MSG_OUTSIDE = "%s%s/ext/?secret=%s";
	private static final String REDIRECT_POST_MSG = "%s%s/?pwd=%s";
	public final static int MAX_RETRIES = 3;
	public final static int RETRY_PERIOD = 1000;

	private final Sync sync;
	private final String internalSecret;
	private MessageResource resource;

	public MessageResourceRep(String domain, Discovery d, Sync sync, String secret) {
		resource = new MessageResource(domain, URI.create(sync.getServerURI()), d, secret);
		this.sync = sync;
		internalSecret = secret;
	}

	@Override
	public long postMessage(Long version, String pwd, Message msg) {
		if (sync.isLeader()) {

			long id = resource.checkPostMessage(pwd, msg);
			msg.setId(id);

			sync.replicate((new Operation(internalSecret)).postMessage(pwd, msg));

			return this.makePostMessage(sync.getVersion(), pwd, msg, internalSecret);
		} else
			throw new WebApplicationException(Response
					.temporaryRedirect(
							URI.create(String.format(REDIRECT_POST_MSG, sync.getLeader(), MessageService.PATH, pwd)))
					.build());
	}

	@Override
	public long makePostMessage(long version, String pwd, Message msg, String secret) {

		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		sync.waitForVersion(version - 1);

		resource.processLocalPost(msg);

		sync.addHistory((new Operation(internalSecret)).postMessage(pwd, msg));
		sync.done();

		throw new WebApplicationException(Response.status(Status.OK.getStatusCode())
				.header(MessageService.HEADER_VERSION, sync.getVersion()).entity(msg.getId()).build());
	}

	@Override
	public List<String> postMessageOutside(Message msg, String secret) {
		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		if (sync.isLeader()) {
			sync.replicate((new Operation(internalSecret)).postMessageOutside(msg));

			return this.makePostMessageOutside(sync.getVersion(), msg, internalSecret);
		} else
			throw new WebApplicationException(Response
					.temporaryRedirect(URI.create(
							String.format(REDIRECT_POST_MSG_OUTSIDE, sync.getLeader(), MessageService.PATH, secret)))
					.build());
	}

	public synchronized List<String> makePostMessageOutside(long version, Message msg, String secret) {
		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		sync.waitForVersion(version - 1);

		List<String> unavailable = resource.postMessageOutside(msg, secret);

		sync.addHistory((new Operation(internalSecret)).postMessageOutside(msg));
		sync.done();

		return unavailable;

	}

	@Override
	public Message getMessage(Long version, String user, long mid, String pwd) {
		if (version != null)
			sync.waitForVersion(version);
		return this.makeGetMessage(user, mid, pwd, internalSecret);
	}

	@Override
	public Message makeGetMessage(String user, long mid, String pwd, String secret) {

		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		Message msg = resource.getMessage(user, mid, pwd);
		throw new WebApplicationException(Response.status(Status.OK.getStatusCode())
				.header(MessageService.HEADER_VERSION, sync.getVersion()).entity(msg).build());
	}

	@Override
	public List<Long> getMessages(Long version, String user, String pwd) {
		if (version != null)
			sync.waitForVersion(version);
		return this.makeGetMessages(user, pwd, internalSecret);
	}

	@Override
	public List<Long> makeGetMessages(String user, String pwd, String secret) {

		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		List<Long> msgs = resource.getMessages(user, pwd);

		throw new WebApplicationException(Response.status(Status.OK.getStatusCode())
				.header(MessageService.HEADER_VERSION, sync.getVersion()).entity(msgs).build());
	}

	@Override
	public void deleteMessage(Long version, String user, long mid, String pwd) {
		if (sync.isLeader()) {

			resource.checkUser(user, pwd);

			sync.replicate((new Operation(internalSecret)).deleteMessage(user, mid, pwd));
			this.makeDeleteMessage(sync.getVersion(), user, mid, pwd, internalSecret);
		} else {
			throw new WebApplicationException(Response
					.temporaryRedirect(URI.create(
							String.format(REDIRECT_DELETE_MSG, sync.getLeader(), MessageService.PATH, user, mid, pwd)))
					.build());
		}
	}

	@Override
	public void makeDeleteMessage(long version, String user, long mid, String pwd, String secret) {

		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		sync.waitForVersion(version - 1);

		resource.deleteMessageOp(user, mid);

		sync.addHistory((new Operation(internalSecret)).deleteMessage(user, mid, pwd));
		sync.done();

		throw new WebApplicationException(Response.status(Status.NO_CONTENT.getStatusCode())
				.header(MessageService.HEADER_VERSION, sync.getVersion()).build());
	}

	public void deleteOutside(long mid, String secret) {
		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		if (sync.isLeader()) {
			sync.replicate((new Operation(internalSecret)).deleteMessageOutside(mid));
			this.makeDeleteOutside(sync.getVersion(), mid, internalSecret);
		} else {
			throw new WebApplicationException(Response.temporaryRedirect(URI.create(
					String.format(REDIRECT_DELETE_MSG_OUTSIDE, sync.getLeader(), MessageService.PATH, mid, secret)))
					.build());
		}
	}

	public synchronized void makeDeleteOutside(long version, long mid, String secret) {

		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		sync.waitForVersion(version - 1);

		resource.processDelete(mid, secret);

		sync.addHistory((new Operation(internalSecret)).deleteMessageOutside(mid));
		sync.done();
	}

	@Override
	public void removeFromUserInbox(Long version, String user, long mid, String pwd) {
		if (sync.isLeader()) {
			resource.checkUser(user, pwd);

			sync.replicate((new Operation(internalSecret)).removeFromUserInbox(user, mid, pwd));
			this.makeRemoveFromUserInbox(sync.getVersion(), user, mid, pwd, internalSecret);
		} else {
			throw new WebApplicationException(Response.temporaryRedirect(URI.create(
					String.format(REDIRECT_REMOVE_FROM_INBOX, sync.getLeader(), MessageService.PATH, user, mid, pwd)))
					.build());
		}

	}

	@Override
	public void makeRemoveFromUserInbox(long version, String user, long mid, String pwd, String secret) {
		if (secret == null || !secret.equals(internalSecret))
			throw new WebApplicationException(Response.Status.FORBIDDEN);

		sync.waitForVersion(version - 1);

		resource.removeFromUserInbox(user, mid, pwd);

		sync.addHistory((new Operation(internalSecret)).removeFromUserInbox(user, mid, pwd));
		sync.done();

		throw new WebApplicationException(Response.status(Status.NO_CONTENT.getStatusCode())
				.header(MessageService.HEADER_VERSION, sync.getVersion()).build());

	}

	@Override
	public synchronized void postInbox(String user, String secret) {
		resource.postInbox(user, secret);
	}

	@Override
	public synchronized void removeInbox(String user, String secret) {
		resource.removeInbox(user, secret);
	}

}
