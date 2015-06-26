package labs;

import Model.User;
import Model.dao.pojo.UserDAO;
import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserManagerRS {

  @POST @Path("/add")
  public Response addUser( @FormParam("name") String name,
    @FormParam("email") String email ) throws URISyntaxException{
    User user = dao.add(null, name, email);
    URI location = new URI("http://localhost:8080/RSInContainer/rest/users/" + user.getId());

    if (user == null) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }else {
      return Response.created(location).build();
    }
  }

  @GET @Path("/{id}")
  public Response findUser(@PathParam("id") long id) {
    User user = dao.find(null, id);

    if (user == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    } else {
      return Response.ok(user).build();
    }
  }
  

  @POST @Path("/update")
  public Response updateUser( @FormParam("id") long id,
    @FormParam("name") String name, @FormParam("email") String email )
    throws URISyntaxException{
    
    User user = dao.find(null, id);
    URI location = null;

    if (user == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    } else {
      user.setUsername(name);
      user.setEmail(email);
      User updated = dao.update(null, user);

      if (updated == null) {
        return Response.status(Response.Status.NOT_MODIFIED).build();
      } else {
        location = new URI("http://localhost:8080/RSInContainer/rest/users/" + updated.getId());    
        return Response.seeOther(location).build();
      }
    }

  }

  private UserDAO dao = new UserDAO();
}
