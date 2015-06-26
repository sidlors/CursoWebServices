/*
 */

package com.example.jaxws.server;

import com.example.traveller.dao.pojo.PassengerDAO;
import com.example.traveller.model.Passenger;
import com.sun.xml.ws.developer.SchemaValidation;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 *
 * @author education.com
 */
@SchemaValidation
@WebService(
  endpointInterface=
  "com.example.generated.PassengerManager")
public class PassengerManager
implements com.example.generated.PassengerManager {
  public long
  addPassenger(String firstName, String lastName) {
    Passenger newPassenger =
      new Passenger(firstName, lastName, null, null);
    return dao.add(null, newPassenger ).getId();
  }
  private PassengerDAO dao = new PassengerDAO();
// ...
  static public void main(String[] args) {
    System.setProperty( "com.sun.xml.ws.transport.http.HttpAdapter.dump",
                        "true" );
    String url =
      "http://localhost:8080/passengerManager";
    if (args.length > 0)
      url = args[1];
    PassengerManager manager = new PassengerManager();
    Endpoint endpoint =
      Endpoint.publish(url, manager);
  }
}
