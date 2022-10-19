package sd1920.trab2.soap.implementations;

import java.net.URI;

import javax.jws.WebService;
import javax.ws.rs.WebApplicationException;

import sd1920.trab2.api.User;
import sd1920.trab2.api.soap.MessagesException;
import sd1920.trab2.api.soap.UserServiceSoap;
import sd1920.trab2.rest.implementations.UserResource;

@WebService(serviceName = UserServiceSoap.NAME, targetNamespace = UserServiceSoap.NAMESPACE, endpointInterface = UserServiceSoap.INTERFACE)
public class UserServiceImpl implements UserServiceSoap {

	private final UserResource resource;

	public UserServiceImpl(String domain, URI self, String secret) {
		resource = new UserResource(domain, self, secret);
	}

	@Override
	public String postUser(User user) throws MessagesException {
		try {
			return resource.postUser(user);
		} catch (WebApplicationException e) {
			throw new MessagesException(e.getResponse().getStatus());
		}
	}

	@Override
	public User getUser(String name, String pwd) throws MessagesException {
		try {
			return resource.getUser(name, pwd);
		} catch (WebApplicationException e) {
			throw new MessagesException(e.getResponse().getStatus());
		}
	}

	@Override
	public User updateUser(String name, String pwd, User user) throws MessagesException {
		try {
			return resource.updateUser(name, pwd, user);
		} catch (WebApplicationException e) {
			throw new MessagesException(e.getResponse().getStatus());
		}
	}

	@Override
	public User deleteUser(String user, String pwd) throws MessagesException {
		try {
			return resource.deleteUser(user, pwd);
		} catch (WebApplicationException e) {
			throw new MessagesException(e.getResponse().getStatus());
		}
	}

}