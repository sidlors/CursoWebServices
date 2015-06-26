/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package labs;

import javax.jws.WebService;
import Model.User;
import Model.dao.pojo.UserDAO;

/**
 *
 * @author Administrator
 */
@WebService(serviceName = "UserManagerSvc", portName = "UserManager", endpointInterface = "auction.UserManager", targetNamespace = "urn://auction/", wsdlLocation = "WEB-INF/wsdl/UserManagerImpl/UserManagerSvc.wsdl")
public class UserManagerImpl {

  public long addUser(java.lang.String name, java.lang.String email) {
    User newUser = dao.add(null, name, email);
    return newUser.getId();
  }
  
  private UserDAO dao = new UserDAO();
}
