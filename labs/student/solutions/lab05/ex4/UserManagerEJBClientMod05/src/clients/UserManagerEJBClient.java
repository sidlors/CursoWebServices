package clients;

import java.util.Map;
import javax.xml.ws.BindingProvider;

public class UserManagerEJBClient {

  public static void main(String[] args){
    UserManagerEJBClient.addUser("Jill Bobb", "jill@example.com");
    UserManagerEJBClient.addUser("Jim Bobb", "jim@example.com");

    //UserManagerEJBClient.findUser(1);
    //UserManagerEJBClient.updateUser(1, "New Name", "NewName@example.com");
    //UserManagerEJBClient.findUser(1);
  }

  public static void addUser(String username, String email){

    try { // Call Web Service Operation
      auction.UserManagerSvc service = new auction.UserManagerSvc();
      auction.UserManager port = service.getUserManager();

     Map<String,Object> reqCtx =
          ((BindingProvider)port).getRequestContext();
      reqCtx.put(BindingProvider.USERNAME_PROPERTY, "tracy");
      reqCtx.put(BindingProvider.PASSWORD_PROPERTY,"password");


      // TODO process result here
      long result = port.addUser(username, email);
      System.out.println("Result = "+result);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  public static void findUser(long id){

    try { // Call Web Service Operation
      auction.UserManagerSvc service = new auction.UserManagerSvc();
      auction.UserManager port = service.getUserManager();

      Map<String,Object> reqCtx =
          ((BindingProvider)port).getRequestContext();
      reqCtx.put(BindingProvider.USERNAME_PROPERTY, "tracy");
      reqCtx.put(BindingProvider.PASSWORD_PROPERTY,"password");

      // TODO process result here
      auction.User result = port.findUser(id);
      System.out.println("Found User: " + result.getUsername() +
              " Email:" + result.getEmail());
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    
  }

  public static void updateUser(long id, String name, String email){

    try { // Call Web Service Operation
      auction.UserManagerSvc service = new auction.UserManagerSvc();
      auction.UserManager port = service.getUserManager();

      Map<String,Object> reqCtx =
          ((BindingProvider)port).getRequestContext();
      reqCtx.put(BindingProvider.USERNAME_PROPERTY, "tracy");
      reqCtx.put(BindingProvider.PASSWORD_PROPERTY,"password");

      // TODO process result here
      auction.User result = port.updateUser(id, name, email);
      System.out.println("Updated User: " + result.getUsername() +
              " Email:" + result.getEmail());
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

}
