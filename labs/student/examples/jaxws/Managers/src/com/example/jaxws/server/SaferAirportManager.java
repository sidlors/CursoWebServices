
package com.example.jaxws.server;

import com.example.traveller.dao.pojo.AirportDAO;
import com.example.traveller.model.Airport;
import java.util.Arrays;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author education.com
 */
@WebService
public class SaferAirportManager {

  public static class DuplicateObjectException
  extends Exception {
    DuplicateObjectException(String msg) {
      super(msg);
    }
  }

  @WebMethod
  @RequestWrapper(className="com.example.jaxws.server.SaferAirportManagerAddAirportRequest")
  @ResponseWrapper(className="com.example.jaxws.server.SaferAirportManagerAddAirportResponse")
  public long
  addAirport(String code, String name) 
  throws DuplicateObjectException {
    try { return dao.add(null, code, name).getId(); }
    catch( Exception ex ) {
      throw new DuplicateObjectException(code);
    }
  }
  private AirportDAO dao = new AirportDAO();
  // ...
  @WebMethod
  @RequestWrapper(className="com.example.jaxws.server.SaferAirportManagerFindNeighborsRequest")
  @ResponseWrapper(className="com.example.jaxws.server.SaferAirportManagerFindNeighborsResponse")
  public List<Airport>
  findNeighbors(String code) {
    Airport[] result = dao.findNeighbors(null, code);
    return Arrays.asList( result );
  }
  // ...
  static public void main(String[] args) {
    System.setProperty( "com.sun.xml.ws.transport.http.HttpAdapter.dump",
                        "true" );
    String url =
      "http://localhost:8081/airportManager";
    if (args.length > 0)
      url = args[1];
    SaferAirportManager manager = new SaferAirportManager();
    Endpoint endpoint =
      Endpoint.publish(url, manager);
  }
}
