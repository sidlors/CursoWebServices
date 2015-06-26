package labs;

import Model.Item;
import Model.dao.pojo.ItemDAO;
import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/items")
public class ItemManagerRS {

  @POST @Path("/{description}")
  public Response addItem(@PathParam("description") String description)
    throws URISyntaxException{

    Item item = dao.add( null, description );
    URI location = new URI("http://localhost:8080/RSInContainer/rest/items/" + item.getId());
    
    if (item == null) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }else {
      return Response.created(location).build();
    }

  }


  @POST @Path("/add")
  public Response formAdd(@FormParam("description") String description)
    throws URISyntaxException{

    Item item = dao.add( null, description );
    URI location = new URI("http://localhost:8080/RSInContainer/rest/items/" + item.getId());

    if (item == null) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    } else {
      return Response.seeOther(location).build();
    }
  }


  @DELETE @Path("/{id}") @Produces("application/xml")
  public String removeItem(@PathParam("id") long id) {
    dao.remove(null, id);
    return "<message>Removed id: " + id + "</message>";
  }

  @GET @Path("/{id}")
  public Response findItem(@PathParam("id") long id) {
    Item item = dao.find(null, id);

    if (item == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    } else {
      return Response.ok(item).build();
    }
  }

  private ItemDAO dao = new ItemDAO();
}
