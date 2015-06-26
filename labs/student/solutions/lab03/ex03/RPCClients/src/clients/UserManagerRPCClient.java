package clients;

public class UserManagerRPCClient {

  public static void main(String[] args){


    try { // Call Web Service Operation
      labs.UserManagerImplService service = new labs.UserManagerImplService();
      labs.UserManager port = service.getUserManagerImplPort();
      // TODO initialize WS operation arguments here
      java.lang.String name = "bob";
      java.lang.String email = "bob@example.com";
      // TODO process result here
      long result = port.addUser(name, email);
      System.out.println("Result = "+result);
    } catch (Exception ex) {
      // TODO handle custom exceptions here
    }
    
  }

}
