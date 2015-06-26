package labs;

import javax.jws.WebService;
import Model.User;
import Model.dao.pojo.UserDAO;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;



@WebService(serviceName = "UserManagerSvc", portName = "UserManager", endpointInterface = "auction.UserManager", targetNamespace = "urn://auction/", wsdlLocation = "WEB-INF/wsdl/UserManagerImpl/UserManagerSvc.wsdl")
public class UserManagerImpl {

  public long addUser(java.lang.String name, java.lang.String email) {

    String caller = context.getUserPrincipal().getName();
    MessageContext msgCtx = context.getMessageContext();
    ServletContext appCtx =
            (ServletContext) msgCtx.get(MessageContext.SERVLET_CONTEXT);
    appCtx.log("add: caller: " + caller);


    User newUser = dao.add(null, name, email);
    return newUser.getId();

  }
  
  private UserDAO dao = new UserDAO();
  @Resource WebServiceContext context;

}
