package sd1920.trab2.server;

import sd1920.trab2.api.Message;

public class Request {

	private Message msg;
	private boolean isPost;

	public Request(Message msg, boolean isPost) {
		this.msg = msg;
		this.isPost = isPost;
	}

	public Message getMessage() {
		return msg;
	}

	public boolean isPost() {
		return isPost;
	}

}
