package sd1920.trab2.server;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import sd1920.trab2.clients.utils.InsecureHostnameVerifier;
import sd1920.trab2.rest.implementations.MessageResource;
import sd1920.trab2.rest.implementations.UserResource;
import sd1920.trab2.server.Discovery;

public class MessageServerRest {

	static final InetSocketAddress DISCOVERY_ADDR = new InetSocketAddress("226.226.226.226", 2266);

	private static Logger Log = Logger.getLogger(MessageServerRest.class.getName());

	static {
		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s\n");
	}

	public static final int PORT = 8080;
	public static final String SERVICE = "MessageService";

	public static void main(String[] args) throws UnknownHostException {
		String ip = InetAddress.getLocalHost().getHostAddress();

		HttpsURLConnection.setDefaultHostnameVerifier(new InsecureHostnameVerifier());

		String serverURI = String.format("https://%s:%s/rest", ip, PORT);

		Discovery discovery = new Discovery(DISCOVERY_ADDR, InetAddress.getLocalHost().getHostName(), serverURI);
		discovery.start();

		URI self = URI.create(serverURI);

		ResourceConfig config = new ResourceConfig();
		config.register(new MessageResource(InetAddress.getLocalHost().getHostName(), self, discovery, args[0]));
		config.register(new UserResource(InetAddress.getLocalHost().getHostName(), self, args[0]));

		try {
			JdkHttpServerFactory.createHttpServer(URI.create(serverURI), config, SSLContext.getDefault());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Invalid SSL/TLS configuration.");
			e.printStackTrace();
			System.exit(1);
		}

		Log.info(String.format("%s Server ready @ %s\n", SERVICE, serverURI));

	}

}