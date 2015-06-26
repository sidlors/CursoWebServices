package labs;

import Model.Item;
import Model.dao.pojo.ItemDAO;
import javax.jws.WebService;

@WebService(serviceName="ItemManagerWS")
public class ItemManager {

  public long addItem(String description) {
    Item newItem = dao.add( null, description );
    return newItem.getId();
  }

  public void removeItem(long id) {
    dao.remove(null, id);
  }

  public Item findItem(long id) {
    return dao.find(null, id);
  }

  private ItemDAO dao = new ItemDAO();
}
