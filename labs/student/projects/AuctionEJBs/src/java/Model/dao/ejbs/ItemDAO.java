/*
 */

package Model.dao.ejbs;

import Model.Item;
import javax.ejb.Stateless;

/**
 * Data Access Object implementation for persistent
 * class {@see Model.Item}.
 * @author education@oracle.com
 */
@Stateless
public class ItemDAO extends GenericDAO<Item> {

  /**
   * Adds a new Item to the persistent store.
   * If <code>em</code> is not <code>null</code>, the function
   * assumes that the caller has already begun a transaction, and that the
   * caller will commit/rollback as appropriate.
   * @param em EntityManager associated to the persistence context to use,
   * or null if this is an atomic operation.
   * @param description description for new Item
   * @return newly created persistent Item.
   * If the function had to create its own persistence context, the
   * return instance will be detached, as its persistence context will
   * have disappeared by the time the function returns.
   */
  public
  Item add(String description) {
    return add(new Item(description));
  }
}
