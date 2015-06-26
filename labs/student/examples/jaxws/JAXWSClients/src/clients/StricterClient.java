/*
 */

package clients;

import com.example.jaxws.server.StricterAirportManager;
import com.example.jaxws.server.StricterAirportManagerService;
import com.sun.xml.ws.developer.SchemaValidationFeature;
import javax.xml.ws.WebServiceFeature;

/**
 *
 * @author education.com
 */
public class StricterClient {
  public static void main(String[] args) {
    StricterAirportManagerService service =
      new StricterAirportManagerService();
    WebServiceFeature validation =
      new SchemaValidationFeature();
    StricterAirportManager port =
      service.getAirportManagerPort(validation);
    java.lang.String code =
      (args.length >= 1) ? args[0] : "NYLGA";
    java.lang.String name =
      (args.length >= 2) ? args[1] : "New York LaGuardia";
    long result = port.addAirport(code, name);
    System.out.println("Result = " + result);
  }
}
