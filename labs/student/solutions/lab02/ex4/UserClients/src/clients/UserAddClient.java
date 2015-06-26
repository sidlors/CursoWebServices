package clients;

public class UserAddClient {

  public static void main(String[] args){

    try { // Call Web Service Operation
      labs.UserManagerImplService service = new labs.UserManagerImplService();
      labs.UserManager port = service.getUserManagerImplPort();
      // TODO initialize WS operation arguments here
      java.lang.String name = "Phil";
      java.lang.String email = "Phil@example.com";
      // TODO process result here
      long result = port.addUser(name, email);
      System.out.println("Result = "+result);
    } catch (Exception ex) {
      // TODO handle custom exceptions here
    }

  }

}
