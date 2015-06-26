/*
 */

package clients;

import clients.async.AsyncAirportManager;
import java.util.concurrent.Future;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

/**
 *
 * @author education.com
 */

class AsyncClientHandler implements AsyncHandler {
  public void handleResponse( Response res ) {
    Response<clients.async.AddAirportResponse> response =
      (Response<clients.async.AddAirportResponse>) res;
    try { System.out.println(response.get().getReturn());}
    catch( Exception ex ) {
      System.out.println( ex.getMessage() );
    }
  }
}

public class AsyncClientViaHandler {
  public static void main(String[] args) 
  throws Exception {
      clients.async.AirportManagerService service =
        new clients.async.AirportManagerService();
      AsyncAirportManager port =
        service.getAirportManagerPort();
      java.lang.String code = "BOS";
      java.lang.String name = "Boston Logan";
      AsyncHandler handler = new AsyncClientHandler();
      Future<?> holder =
        port.addAirportAsync(code, name, handler);
      // now we can go do anything else we need...
      Thread.sleep(30 * 1000);
  }
}
