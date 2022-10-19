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

import sd1920.trab2.api.restRep.MessageService;
import sd1920.trab2.api.restRep.UserService;
import sd1920.trab2.clients.utils.InsecureHostnameVerifier;
import sd1920.trab2.zookeeper.MessageResourceRep;
import sd1920.trab2.zookeeper.Sync;
import sd1920.trab2.zookeeper.UserResourceRep;
import sd1920.trab2.zookeeper.ZookeeperProcessor;

public class MessageServerRep {

	static final InetSocketAddress DISCOVERY_ADDR = new InetSocketAddress("226.226.226.226", 2266);

	private static Logger Log = Logger.getLogger(MessageServerRep.class.getName());

	static {
		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s\n");
	}

	public static final int PORT = 8080;
	public static final String SERVICE = "MessageService";

	public static void main(String[] args) throws UnknownHostException, Exception {
		String ip = InetAddress.getLocalHost().getHostAddress();

		HttpsURLConnection.setDefaultHostnameVerifier(new InsecureHostnameVerifier());

		String serverURI = String.format("https://%s:%s/rest", ip, PORT);

		Discovery discovery = new Discovery(DISCOVERY_ADDR, InetAddress.getLocalHost().getHostName(), serverURI);
		discovery.start();

		ZookeeperProcessor zk = new ZookeeperProcessor("kafka:2181");
		Sync sync = new Sync(zk, InetAddress.getLocalHost().getHostName(), serverURI, args[0]);

		ResourceConfig config = new ResourceConfig();
		MessageService messages = new MessageResourceRep(InetAddress.getLocalHost().getHostName(), discovery, sync,
				args[0]);
		config.register(messages);
		UserService users = new UserResourceRep(InetAddress.getLocalHost().getHostName(), sync, args[0]);
		config.register(users);
		sync.setResources(users, messages);

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