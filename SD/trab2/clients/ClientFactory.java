package sd1920.trab2.clients;

import sd1920.trab2.clients.rest.MessageClientRest;
import sd1920.trab2.clients.rest.UserClientRest;
import sd1920.trab2.clients.soap.MessageClientSoap;
import sd1920.trab2.clients.soap.UserClientSoap;

import java.net.URI;

/**
 * Class responsible for creating the correct client from a given server URL
 * Simply checks if the URL end with "SOAP" or "REST" and returns a new client
 * of the corresponding type.
 */
public class ClientFactory {

	private static final String REST = "rest";
	private static final String SOAP = "soap";

	public static MessagesEmailClient getMessagesClient(URI url, int maxRetries, int retryPeriod) {
		String[] split = url.toString().split("/");
		String type = split[split.length - 1];
		if (type.equals(SOAP)) {
			return new MessageClientSoap(url, maxRetries, retryPeriod);
		} else if (type.equals(REST)) {
			return new MessageClientRest(url, maxRetries, retryPeriod);
		} else {
			throw new AssertionError("Unknown url: " + url + " - " + type);
		}
	}

	public static UsersEmailClient getUsersClient(URI url, int maxRetries, int retryPeriod) {
		String[] split = url.toString().split("/");
		String type = split[split.length - 1];
		if (type.equals(SOAP)) {
			return new UserClientSoap(url, maxRetries, retryPeriod);
		} else if (type.equals(REST)) {
			return new UserClientRest(url, maxRetries, retryPeriod);
		} else {
			throw new AssertionError("Unknown url: " + url + " - " + type);
		}
	}
}
