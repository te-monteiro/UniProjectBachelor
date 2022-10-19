package sd1920.trab2.dropbox.arguments;


public class CheckUserV2Args{

	final String path;
	final boolean include_media_info, include_deleted, include_has_explicit_shared_members;
	
	public CheckUserV2Args(String path) {
		this.path = path;
		this.include_media_info = false;
		this.include_deleted = false;
		this.include_has_explicit_shared_members = false;
		
	}

	

}
