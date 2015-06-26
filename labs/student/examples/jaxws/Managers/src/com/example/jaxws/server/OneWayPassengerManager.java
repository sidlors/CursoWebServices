/*
 */

package com.example.jaxws.server;

import com.example.traveller.dao.pojo.PassengerDAO;
import com.example.traveller.model.Passenger;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author education.com
 */
@WebService
public class OneWayPassengerManager {
  @RequestWrapper(className="com.example.jaxws.server.OneWayPassengerManagerAddPassengerRequest")
  @ResponseWrapper(className="com.example.jaxws.server.OneWayPassengerManagerAddPassengerResponse")
  public long
  addPassenger(String firstName, String lastName) {
    Passenger newPassenger =
      new Passenger(firstName, lastName, null, null);
    return dao.add(null, newPassenger ).getId();
  }
  @Oneway
  public void removePassenger(long id) {
    dao.remove(null, id);
  }
  private PassengerDAO dao = new PassengerDAO();
// ...
  static public void main(String[] args) {
    System.setProperty( "com.sun.xml.ws.transport.http.HttpAdapter.dump",
                        "true" );
    String url =
      "http://localhost:8081/oneWayPassengerManager";
    if (args.length > 0)
      url = args[1];
    OneWayPassengerManager manager = new OneWayPassengerManager();
    Endpoint endpoint =
      Endpoint.publish(url, manager);
  }
}
