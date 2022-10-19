package sd1920.trab2.soap.implementations;

import java.net.URI;
import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.WebApplicationException;

import sd1920.trab2.api.Message;
import sd1920.trab2.api.soap.MessageServiceSoap;
import sd1920.trab2.api.soap.MessagesException;
import sd1920.trab2.rest.implementations.MessageResource;
import sd1920.trab2.server.Discovery;

@WebService(serviceName = MessageServiceSoap.NAME, targetNamespace = MessageServiceSoap.NAMESPACE, endpointInterface = MessageServiceSoap.INTERFACE)
public class MessageServiceImpl implements MessageServiceSoap {

	private final MessageResource resource;

	public MessageServiceImpl(String domain, URI self, Discovery d, String secret) {
		resource = new MessageResource(domain, self, d, secret);
	}

	@Override
	public synchronized void postInbox(String user, String secret) throws MessagesException {
		try {
			resource.postInbox(user, secret);
		} catch (WebApplicationException e) {
			throw new MessagesException(e.getResponse().getStatus());
		}
	}

	@Override
	public synchronized void removeInbox(String user, String secret) throws MessagesException {
		try {
			resource.removeInbox(user, secret);
		} catch (WebApplicationException e) {
			throw new MessagesException(e.getResponse().getStatus());
		}
	}

	@Override
	public long postMessage(String pwd, Message msg) throws MessagesException {
		try {
			return resource.postMessage(pwd, msg);
		} catch (WebApplicationException e) {
			throw new MessagesException(e.getResponse().getStatus());
		}
	}

	@Override
	public synchronized List<String> postMessageOutside(Message msg, String secret) throws MessagesException {
		try {
			return resource.postMessageOutside(msg, secret);
		} catch (WebApplicationException e) {
			throw new MessagesException(e.getResponse().getStatus());
		}
	}

	@Override
	public Message getMessage(String user, String pwd, long mid) throws MessagesException {
		try {
			return resource.getMessage(user, mid, pwd);
		} catch (WebApplicationException e) {
			throw new MessagesException(e.getResponse().getStatus());
		}
	}

	@Override
	public List<Long> getMessages(String user, String pwd) throws MessagesException {
		try {
			return resource.getMessages(user, pwd);
		} catch (WebApplicationException e) {
			throw new MessagesException(e.getResponse().getStatus());
		}
	}

	@Override
	public void removeFromUserInbox(String user, String pwd, long mid) throws MessagesException {
		try {
			resource.removeFromUserInbox(user, mid, pwd);
		} catch (WebApplicationException e) {
			throw new MessagesException(e.getResponse().getStatus());
		}
	}

	@Override
	public void deleteMessage(String user, String pwd, long mid) throws MessagesException {
		try {
			resource.deleteMessage(user, mid, pwd);
		} catch (WebApplicationException e) {
			throw new MessagesException(e.getResponse().getStatus());
		}
	}

	@Override
	public synchronized void processDelete(long mid, String secret) throws MessagesException {
		try {
			resource.processDelete(mid, secret);
		} catch (WebApplicationException e) {
			throw new MessagesException(e.getResponse().getStatus());
		}
	}

}
