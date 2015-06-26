/*
 */

package clients;

import java.util.Map;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author education.com
 */
public class AuthSimpleClient {
  public static void main(String[] args) {
    AirportManagerService service =
      new AirportManagerService();
    AirportManager port = service.getAirportManagerPort();
    Map<String,Object> reqCtx =
      ((BindingProvider) port).getRequestContext();
    reqCtx.put(BindingProvider.USERNAME_PROPERTY,
               "tracy");
    reqCtx.put(BindingProvider.PASSWORD_PROPERTY,
               "password");
    java.lang.String code = "LGA";
    java.lang.String name = "New York LaGuardia";
    long result = port.addAirport(code, name);
    System.out.println("Result = "+result);
  }
}
