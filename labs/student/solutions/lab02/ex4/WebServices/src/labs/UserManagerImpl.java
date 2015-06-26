package labs;

import Model.User;
import Model.dao.pojo.UserDAO;
import javax.jws.WebService;
import labs.generated.UserManager;

@WebService(endpointInterface="labs.generated.UserManager")
public class UserManagerImpl implements UserManager{
  public long addUser( String name, String email ) {
    User newUser = dao.add(null, name, email);
    return newUser.getId();
  }

  private UserDAO dao = new UserDAO();
}
