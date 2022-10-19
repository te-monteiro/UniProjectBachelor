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
import org.pac4j.scribe.builder.api.DropboxApi20;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

import sd1920.trab2.clients.utils.InsecureHostnameVerifier;
import sd1920.trab2.dropbox.CreateDirectory;
import sd1920.trab2.dropbox.DeleteFile;
import sd1920.trab2.dropbox.implementations.MessageResource;
import sd1920.trab2.dropbox.implementations.UserResource;

public class MessageServerProxy {
	private static final String TRUE = "true";

	static final InetSocketAddress DISCOVERY_ADDR = new InetSocketAddress("226.226.226.226", 2266);

	private static Logger Log = Logger.getLogger(MessageServerProxy.class.getName());

	static {
		System.setProperty("java.net.preferIPv4Stack", TRUE);
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

		OAuth20Service service = new ServiceBuilder(args[2]).apiSecret(args[3]).build(DropboxApi20.INSTANCE);
		OAuth2AccessToken accessToken = new OAuth2AccessToken(args[4]);

		if (args[0].equals(TRUE)) {
			DeleteFile df = new DeleteFile(service, accessToken);
			df.execute("/" + InetAddress.getLocalHost().getHostName());
			CreateDirectory cd = new CreateDirectory(service, accessToken);
			cd.execute("/" + InetAddress.getLocalHost().getHostName());
		}

		ResourceConfig config = new ResourceConfig();
		config.register(new MessageResource(InetAddress.getLocalHost().getHostName(), discovery, args[1], service,
				accessToken));
		config.register(new UserResource(InetAddress.getLocalHost().getHostName(), service, accessToken));

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
