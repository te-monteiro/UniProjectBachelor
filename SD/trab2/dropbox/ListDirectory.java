package sd1920.trab2.dropbox;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.Gson;

import sd1920.trab2.dropbox.arguments.ListFolderArgs;
import sd1920.trab2.dropbox.arguments.ListFolderContinueArgs;
import sd1920.trab2.dropbox.replies.ListFolderReturn;
import sd1920.trab2.dropbox.replies.ListFolderReturn.FolderEntry;

public class ListDirectory {

	private static final int RETRY_PERIOD = 1000;

	private static final String HEADER_RETRY_AFTER = "Retry-After";

	private static final String HEADER_CONTENT_TYPE = "Content-Type";

	protected static final String JSON_CONTENT_TYPE = "application/json; charset=utf-8";

	private static final String LIST_FOLDER_URL = "https://api.dropboxapi.com/2/files/list_folder";
	private static final String LIST_FOLDER_CONTINUE_URL = "https://api.dropboxapi.com/2/files/list_folder/continue";

	private OAuth20Service service;
	private OAuth2AccessToken accessToken;

	private Gson json;

	public ListDirectory(OAuth20Service service, OAuth2AccessToken accessToken) {
		this.service = service;
		this.accessToken = accessToken;
		json = new Gson();
	}

	public List<Long> execute(String name, String directoryName) {
		List<Long> directoryContents = new ArrayList<Long>();

		OAuthRequest listDirectory = new OAuthRequest(Verb.POST, LIST_FOLDER_URL);
		listDirectory.addHeader(HEADER_CONTENT_TYPE, JSON_CONTENT_TYPE);
		listDirectory.setPayload(json.toJson(new ListFolderArgs(directoryName, false)));

		service.signRequest(accessToken, listDirectory);

		Response r = null;

		try {
			while (true) {
				r = service.execute(listDirectory);

				if (r.getCode() == Status.TOO_MANY_REQUESTS.getStatusCode()) {
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
					if (r.getCode() != Status.OK.getStatusCode()) {
						System.err.println("Failed to list directory '" + directoryName + "'. Status " + r.getCode()
								+ ": " + r.getMessage());
						System.err.println(r.getBody());
						return null;
					}

					ListFolderReturn reply = json.fromJson(r.getBody(), ListFolderReturn.class);

					for (FolderEntry e : reply.getEntries()) {
						if (!e.toString().equals(name + ".txt"))
							directoryContents.add(Long.parseLong(e.toString().split(".txt")[0]));
					}

					if (reply.has_more()) {
						// There are more elements to read, prepare a new request (now a continuation)
						listDirectory = new OAuthRequest(Verb.POST, LIST_FOLDER_CONTINUE_URL);
						listDirectory.addHeader(HEADER_CONTENT_TYPE, JSON_CONTENT_TYPE);
						// In this case the arguments is just an object containing the cursor that was
						// returned in the previous reply.
						listDirectory.setPayload(json.toJson(new ListFolderContinueArgs(reply.getCursor())));
						service.signRequest(accessToken, listDirectory);
					} else {
						break; // There are no more elements to read. Operation can terminate.
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return directoryContents;
	}

}
