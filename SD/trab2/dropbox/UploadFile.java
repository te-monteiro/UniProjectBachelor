package sd1920.trab2.dropbox;

import java.io.IOException;

import javax.ws.rs.core.Response.Status;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.Gson;

import sd1920.trab2.api.Message;
import sd1920.trab2.api.User;
import sd1920.trab2.dropbox.arguments.UploadFileV2Args;

public class UploadFile {

	private static final int RETRY_PERIOD = 1000;

	private static final String HEADER_RETRY_AFTER = "Retry-After";

	private static final String HEADER_CONTENT_TYPE = "Content-Type";

	private static final String HEADER_DROPBOX_API_ARG = "Dropbox-API-Arg";

	protected static final String OCTET_STREAM_CONTENT_TYPE = "application/octet-stream";

	private static final String UPLOAD_FILE_V2_URL = "https://content.dropboxapi.com/2/files/upload";

	private OAuth20Service service;
	private OAuth2AccessToken accessToken;

	private Gson json;

	public UploadFile(OAuth20Service service, OAuth2AccessToken accessToken) {
		this.service = service;
		this.accessToken = accessToken;
		json = new Gson();
	}

	public boolean execute(String directoryName, User user) {
		OAuthRequest uploadFile = new OAuthRequest(Verb.POST, UPLOAD_FILE_V2_URL);
		uploadFile.addHeader(HEADER_DROPBOX_API_ARG, json.toJson(new UploadFileV2Args(directoryName, false)));
		uploadFile.addHeader(HEADER_CONTENT_TYPE, OCTET_STREAM_CONTENT_TYPE);

		uploadFile.setPayload(json.toJson(user).getBytes());

		service.signRequest(accessToken, uploadFile);

		Response r = null;

		while (true) {
			try {
				r = service.execute(uploadFile);
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

	public boolean execute(String directoryName, Message msg) {
		OAuthRequest uploadFile = new OAuthRequest(Verb.POST, UPLOAD_FILE_V2_URL);
		uploadFile.addHeader(HEADER_DROPBOX_API_ARG, json.toJson(new UploadFileV2Args(directoryName, false)));
		uploadFile.addHeader(HEADER_CONTENT_TYPE, OCTET_STREAM_CONTENT_TYPE);

		uploadFile.setPayload(json.toJson(msg).getBytes());

		service.signRequest(accessToken, uploadFile);

		Response r = null;

		while (true) {
			try {
				r = service.execute(uploadFile);
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
