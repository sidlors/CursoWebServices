/*
 */

package com.example.jaxws.ejb;

import com.example.generated.Airport;
import com.example.generated.SaferAirportManager;
import com.example.generated.SaferAirportManagerService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.ws.rs.QueryParam;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author education.com
 */
@WebService @Stateless
public class Planner {
  @WebServiceRef(wsdlLocation =
                  "http://localhost:8081/airportManager.wsdl")
  private SaferAirportManagerService service;
  public List<String> getNeighbors(String code) {
    SaferAirportManager airportWS =
      service.getSaferAirportManagerPort();
    List<Airport> candidates = airportWS.findNeighbors(code);
    List<String> result = new ArrayList<String>();
    for (Airport a : candidates)
      result.add(a.getCode());
    if (result.size() == 0) {
      result.add("LAX");
      result.add("JFK");
    }
    return result;
  }
}
