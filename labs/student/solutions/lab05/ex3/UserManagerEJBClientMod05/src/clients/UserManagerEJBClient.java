package clients;

import java.util.Map;
import javax.xml.ws.BindingProvider;

public class UserManagerEJBClient {

  public static void main(String[] args){
    UserManagerEJBClient.addUser();

    //UserManagerEJBClient.findUser(1);
    //UserManagerEJBClient.updateUser(1, "New Name", "NewName@example.com");
    //UserManagerEJBClient.findUser(1);
  }

  public static void addUser(){

    try { // Call Web Service Operation
      auction.UserManagerSvc service = new auction.UserManagerSvc();
      auction.UserManager port = service.getUserManager();

     Map<String,Object> reqCtx =
          ((BindingProvider)port).getRequestContext();
      reqCtx.put(BindingProvider.USERNAME_PROPERTY, "tracy");
      reqCtx.put(BindingProvider.PASSWORD_PROPERTY,"password");


      // TODO initialize WS operation arguments here
      java.lang.String name = "ejb05";
      java.lang.String email = "ejb05@example.com";
      // TODO process result here
      long result = port.addUser(name, email);
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
