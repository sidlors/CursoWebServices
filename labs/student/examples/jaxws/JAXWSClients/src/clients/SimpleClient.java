/*
 */

package clients;

/**
 *
 * @author education.com
 */
public class SimpleClient {
  public static void main(String[] args) {
      AirportManagerService service = 
        new AirportManagerService();
      AirportManager port =
        service.getAirportManagerPort();
      java.lang.String code = "LGA";
      java.lang.String name = "New York LaGuardia";
      long result = port.addAirport(code, name);
      System.out.println("Result = "+result);
  }
}
