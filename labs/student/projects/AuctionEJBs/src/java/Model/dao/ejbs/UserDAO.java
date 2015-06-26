/*
 */

package Model.dao.ejbs;

import Model.Item;
import Model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * Data Access Object implementation for persistent
 * class {@see Model.Item}.
 * @author education@oracle.com
 */
@Stateless
public class UserDAO extends GenericDAO<User> {

  /**
   * Adds a new User to the persistent store.
   * If <code>em</code> is not <code>null</code>, the function
   * assumes that the caller has already begun a transaction, and that the
   * caller will commit/rollback as appropriate.
   * @param em EntityManager associated to the persistence context to use
   * or null if this is an atomic operation.
   * @param username username for new User
   * @param email email for new User
   * @return newly created persistent User.
   * If the function had to create its own persistence context, the
   * return instance will be detached, as its persistence context will
   * have disappeared by the time the function returns.
   */
  public
  User add(String username, String email) {
    return add(new User( username, email ) );
  }

  /**
   * Returns a list of all users currently in the persistent store.
   * @return list of Users in the persistent store.
   */
  public List<User> getAll() {
    String sqlQuery = "SELECT u from User u";
    TypedQuery<User> query =
      em.createQuery(sqlQuery, User.class);
    List<User> results = query.getResultList();
    return results;
  }
}
