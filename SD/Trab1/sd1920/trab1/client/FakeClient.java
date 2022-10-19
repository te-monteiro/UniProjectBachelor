package sd1920.trab1.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import com.sun.xml.ws.client.BindingProviderProperties;

import sd1920.trab1.api.Message;
import sd1920.trab1.api.rest.MessageService;
import sd1920.trab1.api.soap.MessageServiceSoap;

public class FakeClient {

	private static final String SOAP = "/soap";
	public final static int MAX_RETRIES = 3;
	public final static long RETRY_PERIOD = 1000;
	public final static int CONNECTION_TIMEOUT = 1000;
	public final static int REPLY_TIMEOUT = 600;

	private String serverUrl;

	public FakeClient(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public List<String> postMessage(Message msg) {
		if (serverUrl.contains(SOAP))
			return postMessageSoap(msg);
		return postMessageRest(msg);
	}

	public long deleteMessageOutsideClient(long mid) {
		if (serverUrl.contains(SOAP))
			return deleteMessageSoap(mid);
		return deleteMessageRest(mid);
	}

	private long deleteMessageRest(long mid) {
		ClientConfig config = new ClientConfig();
		// How much time until timeout on opening the TCP connection to the server
		config.property(ClientProperties.CONNECT_TIMEOUT, CONNECTION_TIMEOUT);
		// How much time to wait for the reply of the server after sending the request
		config.property(ClientProperties.READ_TIMEOUT, REPLY_TIMEOUT);
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(serverUrl).path(MessageService.PATH).path("ext/msg");
		for (;;) {
			try {
				Response r = target.path("" + mid + "").request().delete();

				if (r.getStatus() == Status.NO_CONTENT.getStatusCode())
					return 0;
				else
					return -1;
			} catch (ProcessingException pe) { // Error in communication with server
				System.out.println("Timeout occurred.");
				pe.printStackTrace(); // Could be removed
				try {
					Thread.sleep(RETRY_PERIOD); // wait until attempting again.
				} catch (InterruptedException e) {
					// Nothing to be done here, if this happens we will just retry sooner.
				}
				System.out.println("Retrying to execute request.");
			}
		}
	}

	private long deleteMessageSoap(long mid) {
		MessageServiceSoap messages = null;
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

		for (;;) {
			try {
				messages.processDelete(mid);
				System.out.println("Success, message deleted outside.");
				return 0;
			} catch (WebServiceException wse) { // timeout
				System.out.println("Communication error");
				wse.printStackTrace(); // could be removed.
				try {
					Thread.sleep(RETRY_PERIOD); // wait until attempting again.
				} catch (InterruptedException e) {
					// Nothing to be done here, if this happens we will just retry sooner.
				}
				System.out.println("Retrying to execute request.");
			}
		}
	}

	private List<String> postMessageRest(Message msg) {
		ClientConfig config = new ClientConfig();
		// How much time until timeout on opening the TCP connection to the server
		config.property(ClientProperties.CONNECT_TIMEOUT, CONNECTION_TIMEOUT);
		// How much time to wait for the reply of the server after sending the request
		config.property(ClientProperties.READ_TIMEOUT, REPLY_TIMEOUT);
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(serverUrl).path(MessageService.PATH).path("ext");

		for (;;) {
			try {
				Response r = target.request().accept(MediaType.APPLICATION_JSON)
						.post(Entity.entity(msg, MediaType.APPLICATION_JSON));

				if (r.getStatus() == Status.OK.getStatusCode() && r.hasEntity())
					return r.readEntity(new GenericType<List<String>>() {
					});
				else
					return null;
			} catch (ProcessingException pe) { // Error in communication with server
				System.out.println("Timeout occurred.");
				pe.printStackTrace(); // Could be removed
				try {
					Thread.sleep(RETRY_PERIOD); // wait until attempting again.
				} catch (InterruptedException e) {
					// Nothing to be done here, if this happens we will just retry sooner.
				}
				System.out.println("Retrying to execute request.");
			}
		}
	}

	private List<String> postMessageSoap(Message msg) {
		MessageServiceSoap messages = null;
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

		for (;;) {
			try {
				List<String> unavailable = messages.postMessageOutside(msg);
				System.out.println("Success, message posted outside.");
				return unavailable;
			} catch (WebServiceException wse) { // timeout
				System.out.println("Communication error");
				wse.printStackTrace(); // could be removed.
				try {
					Thread.sleep(RETRY_PERIOD); // wait until attempting again.
				} catch (InterruptedException e) {
					// Nothing to be done here, if this happens we will just retry sooner.
				}
				System.out.println("Retrying to execute request.");
			}
		}
	}

}
