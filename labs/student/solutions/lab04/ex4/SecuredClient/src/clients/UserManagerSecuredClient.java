package clients;

import java.util.Map;
import javax.xml.ws.BindingProvider;

public class UserManagerSecuredClient {

  public static void main(String[] args){

    try { // Call Web Service Operation
      auction.UserManagerSvc service = new auction.UserManagerSvc();
      auction.UserManager port = service.getUserManager();

      // provide authentication data
      Map<String,Object> reqCtx =
          ((BindingProvider)port).getRequestContext();
      reqCtx.put(BindingProvider.USERNAME_PROPERTY, "tracy");
      reqCtx.put(BindingProvider.PASSWORD_PROPERTY,"password");

      // TODO initialize WS operation arguments here
      java.lang.String name = "user";
      java.lang.String email = "user@example.com";
      // TODO process result here
      long result = port.addUser(name, email);
      System.out.println("Result = "+result);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

}
