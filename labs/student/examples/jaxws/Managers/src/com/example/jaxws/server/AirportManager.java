
package com.example.jaxws.server;

import com.example.traveller.dao.pojo.AirportDAO;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 *
 * @author education.com
 */
@WebService
public class AirportManager {
  public long
  addAirport(String code, String name) {
    return dao.add(null, code, name).getId();
  }
  private AirportDAO dao = new AirportDAO();
  // ...
  static public void main(String[] args) {
    System.setProperty( "com.sun.xml.ws.transport.http.HttpAdapter.dump",
                        "true" );
    String url =
      "http://localhost:8080/airportManager";
    if (args.length > 0)
      url = args[1];
    AirportManager manager = new AirportManager();
    Endpoint endpoint =
      Endpoint.publish(url, manager);
  }
}
