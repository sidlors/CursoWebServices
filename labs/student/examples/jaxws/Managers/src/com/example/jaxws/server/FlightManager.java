
package com.example.jaxws.server;

import com.example.traveller.dao.pojo.AirportDAO;
import com.example.traveller.dao.pojo.FlightDAO;
import com.example.traveller.model.Flight;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 *
 * @author education.com
 */
@WebService
public class FlightManager {
  @WebMethod
  public Flight
  addFlight(String airline, String number,
            String departsPort, Date departsTime,
            String arrivesPort, Date arrivesTime ) {
    Flight flight = new 
      Flight(airline,number,
             airportDAO.findByCode(null,departsPort),
             airportDAO.findByCode(null,arrivesPort),
             departsTime, arrivesTime, 150);
    return flightDao.add(null, flight);
  }
  @WebMethod(operationName="simpleAddFlight")
  public Flight
  addFlight(String airline, String number,
             String departsPort, String arrivesPort) {
    Date now = new Date();
    Flight flight = new
      Flight(airline,number,
             airportDAO.findByCode(null,departsPort),
             airportDAO.findByCode(null,arrivesPort),
             now, now, 150);
    return flightDao.add(null,flight);
  }
  @WebMethod
  public Flight getFlight(String airline, long id) {
    return flightDao.find(null, id);
  }
  private FlightDAO flightDao = new FlightDAO();
  private AirportDAO airportDAO = new AirportDAO();
  // ...
  static public void main(String[] args) {
    System.setProperty( "com.sun.xml.ws.transport.http.HttpAdapter.dump",
                        "true" );
    String url =
      "http://localhost:8080/flightManager";
    if (args.length > 0)
      url = args[1];
    FlightManager manager = new FlightManager();
    Endpoint endpoint =
      Endpoint.publish(url, manager);
  }
}
