package sd1920.trab2.clients;

import sd1920.trab2.api.Message;

public interface MessagesEmailClientRep extends MessagesEmailClient {

	EmailResponse<Void> invokePostMessage(String pwd, Message msg, long version, String secret);

	EmailResponse<Void> invokePostMessageOutside(Message msg, long version, String secret);

	EmailResponse<Void> invokeDeleteMessage(String user, long mid, String pwd, long version, String secret);

	EmailResponse<Void> invokeProcessDelete(long mid, long version, String secret);

	EmailResponse<Void> invokeRemoveFromUserInbox(String user, long mid, String pwd, long version, String secret);
}
