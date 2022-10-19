package sd1920.trab2.dropbox.implementations;


public class Request {

	private long mid;
	private boolean isPost;

	public Request(long mid, boolean isPost) {
		this.mid = mid;
		this.isPost = isPost;
	}

	public long getMid() {
		return mid;
	}

	public boolean isPost() {
		return isPost;
	}

}
