package labs;

import Model.Item;
import Model.dao.ejbs.ItemDAO;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(serviceName="ItemManagerWS")
@DeclareRoles({"user","administrator"})
@RolesAllowed({"user","administrator"})
@Stateless
public class ItemManager {

  public long addItem(String description) {
    Item newItem = dao.add( description );
    return newItem.getId();
  }

  public void removeItem(long id) {
    dao.remove(id);
  }

  public Item findItem(long id) {
    return dao.find(id);
  }

  @EJB private ItemDAO dao;
}
