package labs;

import Model.User;
import Model.dao.pojo.UserDAO;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@Path("/users")
@DeclareRoles({"user","administrator"})
public class UserManagerRS {

  @POST @Path("/add")
  @RolesAllowed({"administrator"})
  public String addUser( @FormParam("name") String name,
                       @FormParam("email") String email ) {
    User newUser = dao.add(null, name, email);
    return "<h4>Added: " + newUser.getId() + " -- " + newUser.getUsername()
            + " -- " + newUser.getEmail() + "</h4>";
  }

  @RolesAllowed({"user"})
  @GET @Path("/{id}")
  public User findUser(@PathParam("id") long id) {
    return dao.find(null, id);
  }
  

  @RolesAllowed({"administrator"})
  @POST @Path("/update")
  public String updateUser( @FormParam("id") long id,
                          @FormParam("name") String name,
                          @FormParam("email") String email ) {
    User user = dao.find(null, id);
    user.setUsername(name);
    user.setEmail(email);
    User updated = dao.update(null, user);
    return "<h4>Updated: " + updated.getId() + " -- " + updated.getUsername()
            + " -- " + updated.getEmail() + "</h4>";

  }

  private UserDAO dao = new UserDAO();
}
