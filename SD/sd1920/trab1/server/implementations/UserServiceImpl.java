package sd1920.trab1.server.implementations;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

import com.sun.xml.ws.client.BindingProviderProperties;

import sd1920.trab1.api.User;
import sd1920.trab1.api.soap.MessageServiceSoap;
import sd1920.trab1.api.soap.MessagesException;
import sd1920.trab1.api.soap.UserServiceSoap;
import sd1920.trab1.server.Discovery;

@WebService(serviceName = UserServiceSoap.NAME, targetNamespace = UserServiceSoap.NAMESPACE, endpointInterface = UserServiceSoap.INTERFACE)
public class UserServiceImpl implements UserServiceSoap {

	public final static int CONNECTION_TIMEOUT = 1000;
	public final static int REPLY_TIMEOUT = 600;

	private final Map<String, User> users;
	private final String domain;
	private final Discovery d;

	private static Logger Log = Logger.getLogger(UserServiceSoap.class.getName());

	public UserServiceImpl(String domain, Discovery d) {
		this.users = new HashMap<String, User>();
		this.domain = domain;
		this.d = d;
	}

	@Override
	public String postUser(User user) throws MessagesException {
		if (user.getDisplayName() == null || user.getDisplayName().isEmpty() || user.getName() == null
				|| user.getName().isEmpty() || user.getPwd() == null || user.getPwd().isEmpty()
				|| user.getDomain() == null || user.getDomain().isEmpty() || users.containsKey(user.getName())) {
			Log.info("Message was rejected due to lack of recepients.");
			throw new MessagesException("Message does not exists"); // if not send HTTP 404 back to client
		}

		if (!user.getDomain().equals(domain)) {
			Log.info("The domain in the user does not match the domain of the server.");
			throw new MessagesException("Message does not exists"); // if not send HTTP 404 back to client
		}
		synchronized (this) {
			users.put(user.getName(), user);
		}

		createInbox(user.getName());
		return user.getName() + "@" + user.getDomain();
	}

	@Override
	public User getUser(String name, String pwd) throws MessagesException {
		User u = null;
		synchronized (this) {
			u = users.get(name);
		}
		if (u == null || pwd == null || !u.getPwd().equals(pwd)) {
			Log.info("Message was rejected due to lack of recepients.");
			throw new MessagesException("Message does not exists"); // if not send HTTP 404 back to client
		}
		return u;
	}

	@Override
	public User updateUser(String name, String pwd, User user) throws MessagesException {
		User u = null;
		synchronized (this) {
			u = users.get(name);
		}
		if (u == null || pwd == null || !u.getPwd().equals(pwd)) {
			Log.info("Message was rejected due to lack of recepients.");
			throw new MessagesException("Message does not exists"); // if not send HTTP 404 back to client
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
	public User deleteUser(String user, String pwd) throws MessagesException {
		User u = null;
		synchronized (this) {
			u = users.get(user);
		}
		if (u == null || pwd == null || !u.getPwd().equals(pwd)) {
			Log.info("Message was rejected due to lack of recepients.");
			throw new MessagesException("Message does not exists"); // if not send HTTP 404 back to client
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
		MessageServiceSoap messages = null;

		String serverUrl = getServer(domain);

		try {
			QName QNAME = new QName(MessageServiceSoap.NAMESPACE, MessageServiceSoap.NAME);
			Service service = Service.create(new URL(serverUrl + MessageServiceSoap.MESSAGES_WSDL), QNAME);
			messages = service.getPort(sd1920.trab1.api.soap.MessageServiceSoap.class);
		} catch (WebServiceException wse) {
			System.err.println("Could not conntact the server: " + wse.getMessage());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		// Set timeouts
		((BindingProvider) messages).getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT,
				CONNECTION_TIMEOUT);
		((BindingProvider) messages).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, REPLY_TIMEOUT);

		try {
			messages.postInbox(user);
			System.out.println("Success, inbox created.");
		} catch (WebServiceException wse) { // timeout
			System.out.println("Communication error");
			wse.printStackTrace(); // could be removed.
		}
	}

	private void removeInbox(String user) {
		MessageServiceSoap messages = null;

		String serverUrl = getServer(domain);

		try {
			QName QNAME = new QName(MessageServiceSoap.NAMESPACE, MessageServiceSoap.NAME);
			Service service = Service.create(new URL(serverUrl + MessageServiceSoap.MESSAGES_WSDL), QNAME);
			messages = service.getPort(sd1920.trab1.api.soap.MessageServiceSoap.class);
		} catch (WebServiceException wse) {
			System.err.println("Could not conntact the server: " + wse.getMessage());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		// Set timeouts
		((BindingProvider) messages).getRequestContext().put(BindingProviderProperties.CONNECT_TIMEOUT,
				CONNECTION_TIMEOUT);
		((BindingProvider) messages).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, REPLY_TIMEOUT);
		
		try {
			messages.removeInbox(user);
			System.out.println("Success, inbox deleted.");
		} catch (WebServiceException wse) { // timeout
			System.out.println("Communication error");
			wse.printStackTrace(); // could be removed.
		}

	}

}