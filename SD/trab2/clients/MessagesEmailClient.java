package sd1920.trab2.clients;

import java.util.List;

import sd1920.trab2.api.Message;

/*
 * Interface used by both Message clients
 */
public interface MessagesEmailClient {

	EmailResponse<Void> deleteUserInfo(String user, String secret);

	EmailResponse<Void> setupUserInfo(String user, String secret);

	EmailResponse<List<String>> forwardSendMessage(Message msg, String secret);

	EmailResponse<Void> forwardDeleteSentMessage(long mid, String secret);
}
