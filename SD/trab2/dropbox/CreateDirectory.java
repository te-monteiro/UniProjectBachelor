package sd1920.trab2.dropbox;

import java.io.IOException;

import javax.ws.rs.core.Response.Status;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.Gson;

import sd1920.trab2.dropbox.arguments.CreateFolderV2Args;

public class CreateDirectory {

	private static final int RETRY_TIMEOUT = 1000;

	private static final String HEADER_RETRY_AFTER = "Retry-After";

	private static final String HEADER_CONTENT_TYPE = "Content-Type";

	protected static final String JSON_CONTENT_TYPE = "application/json; charset=utf-8";

	private static final String CREATE_FOLDER_V2_URL = "https://api.dropboxapi.com/2/files/create_folder_v2";

	private OAuth20Service service;
	private OAuth2AccessToken accessToken;

	private Gson json;

	public CreateDirectory(OAuth20Service service, OAuth2AccessToken accessToken) {
		this.service = service;
		this.accessToken = accessToken;
		json = new Gson();
	}

	public boolean execute(String directoryName) {
		OAuthRequest createFolder = new OAuthRequest(Verb.POST, CREATE_FOLDER_V2_URL);
		createFolder.addHeader(HEADER_CONTENT_TYPE, JSON_CONTENT_TYPE);

		createFolder.setPayload(json.toJson(new CreateFolderV2Args(directoryName, false)));

		service.signRequest(accessToken, createFolder);

		Response r = null;

		while (true) {
			try {
				r = service.execute(createFolder);
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
					Thread.sleep(RETRY_TIMEOUT);
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
