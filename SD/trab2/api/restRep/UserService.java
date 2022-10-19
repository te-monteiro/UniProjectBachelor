package sd1920.trab2.api.restRep;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import sd1920.trab2.api.User;
import sd1920.trab2.zookeeper.Operation;

@Path(UserService.PATH)
public interface UserService {
	public static final String HEADER_VERSION = "Msgserver-version";
	String PATH = "/users";

	/**
	 * Creates a new user in the local domain.
	 * 
	 * @param user User to be created
	 * @return 200 the address of the user (name@domain). 403 if the domain in the
	 *         user does not match the domain of the server 409 otherwise
	 */
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String postUser(@HeaderParam(UserService.HEADER_VERSION) Long version, User user);

	@POST
	@Path("/rep/{version}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String makePostUser(@PathParam("version") long version, User user, @QueryParam("secret") String secret);

	/**
	 * Obtains the information on the user identified by name
	 * 
	 * @param name the name of the user
	 * @param pwd  password of the user
	 * @return 200 the user object, if the name exists and pwd matches the existing
	 *         password 403 if the password is incorrect or the user does not exist
	 *         409 otherwise
	 */
	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	User getUser(@HeaderParam(UserService.HEADER_VERSION) Long version, @PathParam("name") String name,
			@QueryParam("pwd") String pwd);

	@GET
	@Path("/rep/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	User makeGetUser(@PathParam("name") String name, @QueryParam("pwd") String pwd,
			@QueryParam("secret") String secret);

	/**
	 * Modifies the information of a user. Values of null in any field of the user
	 * will be considered as if the the fields is not to be modified (the name
	 * cannot be modified).
	 * 
	 * @param name the name of the user
	 * @param pwd  password of the user
	 * @param user Updated information
	 * @return 200 the updated user object, if the name exists and pwd matches the
	 *         existing password 403 if the password is incorrect or the user does
	 *         not exist 409 otherwise
	 */
	@PUT
	@Path("/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	User updateUser(@HeaderParam(UserService.HEADER_VERSION) Long version, @PathParam("name") String name,
			@QueryParam("pwd") String pwd, User user);

	@PUT
	@Path("/rep/{version}/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	User makeUpdateUser(@PathParam("version") long version, @PathParam("name") String name,
			@QueryParam("pwd") String pwd, User user, @QueryParam("secret") String secret);

	/**
	 * Deletes the user identified by name
	 * 
	 * @param name the name of the user
	 * @param pwd  password of the user
	 * @return 200 the deleted user object, if the name exists and pwd matches the
	 *         existing password 403 if the password is incorrect or the user does
	 *         not exist 409 otherwise
	 */
	@DELETE
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	User deleteUser(@HeaderParam(UserService.HEADER_VERSION) Long version, @PathParam("name") String name,
			@QueryParam("pwd") String pwd);

	@DELETE
	@Path("/rep/{version}/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	User makeDeleteUser(@PathParam("version") long version, @PathParam("name") String name,
			@QueryParam("pwd") String pwd, @QueryParam("secret") String secret);

	@GET
	@Path("/ops/{min}/{max}")
	@Produces(MediaType.APPLICATION_JSON)
	List<Operation> getOperations(@PathParam("min") long min, @PathParam("max") long max,
			@QueryParam("secret") String secret);

	@GET
	@Path("/version")
	@Produces(MediaType.APPLICATION_JSON)
	long getVersion(@QueryParam("secret") String secret);

}