package sd1920.trab2.zookeeper;

import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import sd1920.trab2.api.Message;
import sd1920.trab2.api.User;
import sd1920.trab2.api.restRep.MessageService;
import sd1920.trab2.api.restRep.UserService;
import sd1920.trab2.clients.MessagesEmailClientRep;
import sd1920.trab2.clients.UsersEmailClientRep;

public class Operation {

	private final static int POST_USER_CODE = 1;
	private final static int UPDATE_USER_CODE = 2;
	private final static int DELETE_USER_CODE = 3;
	private final static int POST_MSG_CODE = 4;
	private final static int DELETE_MSG_CODE = 5;
	private final static int REMOVE_FROM_USER_INBOX_CODE = 6;
	private final static int POST_MSG_OUTSIDE_CODE = 7;
	private final static int DELETE_MSG_OUTSIDE_CODE = 8;

	public int code;
	public String[] params;
	public String internalSecret;

	public Operation() {
		this.code = -1;
		this.params = null;
		this.internalSecret = "";
	}

	public Operation(String internalSecret) {
		this.code = -1;
		this.params = null;
		this.internalSecret = internalSecret;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public String getInternalSecret() {
		return internalSecret;
	}

	public void setInternalSecret(String internalSecret) {
		this.internalSecret = internalSecret;
	}

	public Operation postUser(User u) {
		code = POST_USER_CODE;
		Gson json = new Gson();
		params = new String[2];
		params[0] = json.toJson(u);
		params[1] = internalSecret;
		return this;
	}

	public Operation updateUser(String name, String pwd, User u) {
		code = UPDATE_USER_CODE;
		params = new String[4];
		params[0] = name;
		params[1] = pwd;
		Gson json = new Gson();
		params[2] = json.toJson(u);
		params[3] = internalSecret;
		return this;
	}

	public Operation deleteUser(String name, String pwd) {
		code = DELETE_USER_CODE;
		params = new String[3];
		params[0] = name;
		params[1] = pwd;
		params[2] = internalSecret;
		return this;
	}

	public Operation postMessage(String pwd, Message m) {
		code = POST_MSG_CODE;
		params = new String[3];
		params[0] = pwd;
		Gson json = new Gson();
		params[1] = json.toJson(m);
		params[2] = internalSecret;
		return this;
	}

	public Operation postMessageOutside(Message m) {
		code = POST_MSG_OUTSIDE_CODE;
		params = new String[2];
		Gson json = new Gson();
		params[0] = json.toJson(m);
		params[1] = internalSecret;
		return this;
	}

	public Operation deleteMessage(String name, long mid, String pwd) {
		code = DELETE_MSG_CODE;
		params = new String[4];
		params[0] = name;
		params[1] = String.valueOf(mid);
		params[2] = pwd;
		params[3] = internalSecret;
		return this;
	}

	public Operation deleteMessageOutside(long mid) {
		code = DELETE_MSG_OUTSIDE_CODE;
		params = new String[2];
		params[0] = String.valueOf(mid);
		params[1] = internalSecret;
		return this;
	}

	public Operation removeFromUserInbox(String name, long mid, String pwd) {
		code = REMOVE_FROM_USER_INBOX_CODE;
		params = new String[4];
		params[0] = name;
		params[1] = String.valueOf(mid);
		params[2] = pwd;
		params[3] = internalSecret;
		return this;
	}

	public void execute(MessageService messages, UserService users, long version) {
		Gson json = new Gson();
		switch (code) {
		case POST_USER_CODE:
			users.makePostUser(version, json.fromJson(params[0], User.class), params[1]);
			break;
		case UPDATE_USER_CODE:
			users.makeUpdateUser(version, params[0], params[1], json.fromJson(params[2], User.class), params[3]);
			break;
		case DELETE_USER_CODE:
			users.makeDeleteUser(version, params[0], params[1], params[2]);
			break;
		case POST_MSG_CODE:
			messages.makePostMessage(version, params[0], json.fromJson(params[1], Message.class), params[2]);
			break;
		case DELETE_MSG_CODE:
			messages.makeDeleteMessage(version, params[0], Long.parseLong(params[1]), params[2], params[3]);
			break;
		case REMOVE_FROM_USER_INBOX_CODE:
			messages.makeRemoveFromUserInbox(version, params[0], Long.parseLong(params[1]), params[2], params[3]);
			break;
		case POST_MSG_OUTSIDE_CODE:
			messages.makePostMessageOutside(version, json.fromJson(params[0], Message.class), params[1]);
			break;
		case DELETE_MSG_OUTSIDE_CODE:
			messages.makeDeleteOutside(version, Long.parseLong(params[0]), params[1]);
			break;
		}
	}

	public boolean replicate(MessagesEmailClientRep messages, UsersEmailClientRep users, long version) {
		Gson json = new Gson();
		switch (code) {
		case POST_USER_CODE:
			return users.invokePostUser(json.fromJson(params[0], User.class), version, internalSecret)
					.getStatusCode() != Status.SERVICE_UNAVAILABLE.getStatusCode();
		case UPDATE_USER_CODE:
			return users.invokeUpdateUser(params[0], params[1], json.fromJson(params[2], User.class), version,
					internalSecret).getStatusCode() != Status.SERVICE_UNAVAILABLE.getStatusCode();
		case DELETE_USER_CODE:
			return users.invokeDeleteUser(params[0], params[1], version, internalSecret)
					.getStatusCode() != Status.SERVICE_UNAVAILABLE.getStatusCode();
		case POST_MSG_CODE:
			return messages
					.invokePostMessage(params[0], json.fromJson(params[1], Message.class), version, internalSecret)
					.getStatusCode() != Status.SERVICE_UNAVAILABLE.getStatusCode();
		case DELETE_MSG_CODE:
			return messages
					.invokeDeleteMessage(params[0], Long.parseLong(params[1]), params[2], version, internalSecret)
					.getStatusCode() != Status.SERVICE_UNAVAILABLE.getStatusCode();
		case REMOVE_FROM_USER_INBOX_CODE:
			return messages
					.invokeRemoveFromUserInbox(params[0], Long.parseLong(params[1]), params[2], version, internalSecret)
					.getStatusCode() != Status.SERVICE_UNAVAILABLE.getStatusCode();

		case POST_MSG_OUTSIDE_CODE:
			return messages.invokePostMessageOutside(json.fromJson(params[0], Message.class), version, internalSecret)
					.getStatusCode() != Status.SERVICE_UNAVAILABLE.getStatusCode();

		case DELETE_MSG_OUTSIDE_CODE:
			return messages.invokeProcessDelete(Long.parseLong(params[0]), version, internalSecret)
					.getStatusCode() != Status.SERVICE_UNAVAILABLE.getStatusCode();

		default:
			return false;
		}
	}

}
