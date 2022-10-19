package sd1920.trab2.clients;

import sd1920.trab2.api.User;
import sd1920.trab2.zookeeper.Operation;

import java.util.List;

public interface UsersEmailClientRep extends UsersEmailClient {

	EmailResponse<String> invokePostUser(User user, long version, String secret);

	EmailResponse<User> invokeUpdateUser(String name, String pwd, User user, long version, String secret);

	EmailResponse<User> invokeDeleteUser(String user, String pwd, long version, String secret);

	EmailResponse<List<Operation>> getOperations(long min, long max, String secret);

	EmailResponse<Long> getVersion(String secret);

}
