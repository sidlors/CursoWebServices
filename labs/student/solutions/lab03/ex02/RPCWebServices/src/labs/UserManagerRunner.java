package labs;

import javax.xml.ws.Endpoint;
import labs.generated.UserManager;

public class UserManagerRunner {

  public static void main( String[] args ) {
    UserManager service = new UserManagerImpl();
    Endpoint endpoint =
      Endpoint.publish("http://localhost:8081/rpcUserManager",service);
  }

}
