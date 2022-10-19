package sd1920.trab2.dropbox.arguments;

public class UploadFileV2Args {
	final String path;
	final boolean autorename;
	final String mode;
	final boolean mute;
	final boolean strict_conflict;
	
	public UploadFileV2Args(String path, boolean autorename) {
		this.path = path;
		this.autorename = autorename;
		mode = "overwrite";
		mute = false;
		strict_conflict = false;
	}
}
