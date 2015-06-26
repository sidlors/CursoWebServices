package labs;

import Model.User;
import Model.dao.pojo.UserDAO;
import javax.jws.WebService;

@WebService(endpointInterface="labs.generated.UserManager")
public class UserManagerImpl implements labs.generated.UserManager {

  public long addUser( String name, String email ) {
    User newUser = dao.add(null, name, email);
    return (long) newUser.getId();
  }

  private UserDAO dao = new UserDAO();
}
