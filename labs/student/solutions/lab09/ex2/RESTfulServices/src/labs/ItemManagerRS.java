package labs;

import Model.Item;
import Model.dao.pojo.ItemDAO;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/items")
public class ItemManagerRS {

  @POST @Path("/{description}")
  public String addItem(@PathParam("description") String description) {
    String message = "<h4>Add Item failed...</h4>";

    Item newItem = dao.add( null, description );
    
    if (newItem != null) {
      message = "<h4>Item Added: " + newItem.getId() + " -- "
              + newItem.getDescription() + "</h4>";
    }

    return message;
  }


  @POST @Path("/add")
  public String formAdd(@FormParam("description") String description) {
    Item newItem = dao.add( null, description );
    return "<h4>Added: " + newItem.getId() + " -- " + newItem.getDescription()
            + "</h4>";
  }


  @DELETE @Path("/{id}")
  public String removeItem(@PathParam("id") long id) {
    dao.remove(null, id);
    return "<h4>Removed id: " + id + "</h4>";
  }

  @GET @Path("/{id}")
  public Item findItem(@PathParam("id") long id) {
    return dao.find(null, id);
  }

  private ItemDAO dao = new ItemDAO();
}
