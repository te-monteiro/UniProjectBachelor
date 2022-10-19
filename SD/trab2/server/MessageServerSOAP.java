package sd1920.trab2.server;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.xml.ws.Endpoint;

import com.sun.net.httpserver.HttpsServer;
import com.sun.net.httpserver.HttpsConfigurator;

import sd1920.trab2.clients.utils.InsecureHostnameVerifier;
import sd1920.trab2.soap.implementations.MessageServiceImpl;
import sd1920.trab2.soap.implementations.UserServiceImpl;

public class MessageServerSOAP {

	private static Logger Log = Logger.getLogger(MessageServerSOAP.class.getName());

	static {
		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s\n");
	}

	static final InetSocketAddress DISCOVERY_ADDR = new InetSocketAddress("226.226.226.226", 2266);
	public static final int PORT = 8080;
	public static final String SERVICE = "MessageService";
	public static final String SOAP_MESSAGES_PATH = "/soap/messages";
	public static final String SOAP_USERS_PATH = "/soap/users";

	public static void main(String[] args) throws Exception {
		String ip = InetAddress.getLocalHost().getHostAddress();
		String serverURI = String.format("https://%s:%s/soap", ip, PORT);

		HttpsURLConnection.setDefaultHostnameVerifier(new InsecureHostnameVerifier());

		HttpsConfigurator configurator = new HttpsConfigurator(SSLContext.getDefault());

		// Create an HTTP server, accepting requests at PORT (from all local interfaces)
		HttpsServer server = HttpsServer.create(new InetSocketAddress(ip, PORT), 0);

		server.setHttpsConfigurator(configurator);

		// Provide an executor to create threads as needed...
		server.setExecutor(Executors.newCachedThreadPool());

		Discovery discovery = new Discovery(DISCOVERY_ADDR, InetAddress.getLocalHost().getHostName(), serverURI);
		discovery.start();

		URI self = URI.create(serverURI);

		// Create a SOAP Endpoint (you need one for each service)
		Endpoint soapMessagesEndpoint = Endpoint
				.create(new MessageServiceImpl(InetAddress.getLocalHost().getHostName(), self, discovery, args[0]));

		Endpoint soapUsersEndpoint = Endpoint
				.create(new UserServiceImpl(InetAddress.getLocalHost().getHostName(), self, args[0]));

		// Publish a SOAP webservice, under the "http://<ip>:<port>/soap"
		soapMessagesEndpoint.publish(server.createContext(SOAP_MESSAGES_PATH));
		soapUsersEndpoint.publish(server.createContext(SOAP_USERS_PATH));

		server.start();

		Log.info(String.format("\n%s Server ready @ %s\n", SERVICE, serverURI));

	}

}