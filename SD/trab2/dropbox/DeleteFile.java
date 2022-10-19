package sd1920.trab2.dropbox;

import java.io.IOException;

import javax.ws.rs.core.Response.Status;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.Gson;

import sd1920.trab2.dropbox.arguments.DeleteV2Args;

public class DeleteFile {

	private static final int RETRY_PERIOD = 1000;

	private static final String HEADER_RETRY_AFTER = "Retry-After";

	private static final String HEADER_CONTENT_TYPE = "Content-Type";

	protected static final String JSON_CONTENT_TYPE = "application/json; charset=utf-8";

	private static final String DELETE_V2_URL = "https://api.dropboxapi.com/2/files/delete";

	private OAuth20Service service;
	private OAuth2AccessToken accessToken;

	private Gson json;

	public DeleteFile(OAuth20Service service, OAuth2AccessToken accessToken) {
		this.service = service;
		this.accessToken = accessToken;
		json = new Gson();
	}

	public boolean execute(String directoryName) {
		OAuthRequest deleteUser = new OAuthRequest(Verb.POST, DELETE_V2_URL);

		deleteUser.addHeader(HEADER_CONTENT_TYPE, JSON_CONTENT_TYPE);

		deleteUser.setPayload(json.toJson(new DeleteV2Args(directoryName)));

		service.signRequest(accessToken, deleteUser);

		Response r = null;

		while (true) {
			try {
				r = service.execute(deleteUser);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			if (r.getCode() == Status.OK.getStatusCode()) {
				return true;
			} else if (r.getCode() == Status.TOO_MANY_REQUESTS.getStatusCode()) {
				try {
					Thread.sleep((long) Math.ceil(Double.parseDouble(r.getHeader(HEADER_RETRY_AFTER))));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (r.getCode() == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
				try {
					Thread.sleep(RETRY_PERIOD);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				System.err.println("HTTP Error Code: " + r.getCode() + ": " + r.getMessage());
				try {
					System.err.println(r.getBody());
				} catch (IOException e) {
					System.err.println("No body in the response");
				}
				return false;
			}
		}

	}

}
