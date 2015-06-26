package labs;

import javax.jws.WebService;
import Model.User;
import Model.dao.ejbs.UserDAO;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;



@WebService(serviceName = "UserManagerSvc", portName = "UserManager", endpointInterface = "auction.UserManager", targetNamespace = "urn://auction/", wsdlLocation = "WEB-INF/wsdl/UserManagerImpl/UserManagerSvc.wsdl")
@DeclareRoles({"user","administrator"})
@RolesAllowed({"user","administrator"})
@Stateless
public class UserManagerImpl {

  @RolesAllowed({"administrator"})
  public long addUser(java.lang.String name, java.lang.String email) {

    String caller = context.getUserPrincipal().getName();
    MessageContext msgCtx = context.getMessageContext();
    ServletContext appCtx =
            (ServletContext) msgCtx.get(MessageContext.SERVLET_CONTEXT);
    appCtx.log("add: caller: " + caller);


    User newUser = dao.add(name, email);
    return newUser.getId();

  }
  
  public auction.User findUser(long id) {
    auction.User returnUser = new auction.User();
    User foundUser = new User();
    foundUser =  dao.find(id);
    returnUser.setId(foundUser.getId());
    returnUser.setUsername(foundUser.getUsername());
    returnUser.setEmail(foundUser.getEmail());
    return returnUser;
  }

  public auction.User updateUser(long id, String username, String email){
    auction.User returnUser = new auction.User();
    User tempUser = new User();
    User updatedUser = new User();

    tempUser = dao.find(id);
    tempUser.setUsername(username);
    tempUser.setEmail(email);
    updatedUser = dao.update(tempUser);

    returnUser.setId(updatedUser.getId());
    returnUser.setUsername(updatedUser.getUsername());
    returnUser.setEmail(updatedUser.getEmail());
    return returnUser;
  }

  
  @EJB private UserDAO dao;
  @Resource WebServiceContext context;

}
