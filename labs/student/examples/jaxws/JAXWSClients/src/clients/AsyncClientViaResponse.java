/*
 */

package clients;

import clients.async.AsyncAirportManager;
import javax.xml.ws.Response;

/**
 *
 * @author education.com
 */
public class AsyncClientViaResponse {
  public static void main(String[] args) 
  throws Exception {
      clients.async.AirportManagerService service =
        new clients.async.AirportManagerService();
      AsyncAirportManager port =
        service.getAirportManagerPort();
      java.lang.String code = "LGA";
      java.lang.String name = "New York LaGuardia";
      Response<clients.async.AddAirportResponse> holder =
        port.addAirportAsync(code, name);
      // some other work goes here...
      long result = holder.get().getReturn();
      System.out.println("Result = "+result);
  }
}
