/*
 */

package com.example.jaxrs.resources;

import com.example.traveller.dao.ejb.AirportDAO;
import com.example.traveller.dao.ejb.FlightDAO;
import com.example.traveller.model.Airport;
import com.example.traveller.model.Flight;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author education.com
 */
@Path("/planner")
@Stateless
public class PlannerRM {
  @Path("/neighbors") @GET
  @Produces({"application/xml","application/json"})
  public List<String>
  getNeighbors(@QueryParam("code") String code) {
    Airport[] candidates = airportDAO.findNeighbors(code);
    List<String> result = new ArrayList<String>();
    for (Airport a : candidates)
      result.add(a.getCode());
    if (result.size() == 0) {
      result.add("LAX");
      result.add("JFK");
    }
    return result;
  }

  @Path("routes") @GET
  @Produces({"application/xml","application/json"})
  public Map<String,String>
  getRoutesFrom(@QueryParam("start") String code) {
    Map<Flight,Airport> candidates =
      flightDAO.findRoutesFrom(airportDAO.findByCode(code));
    Map<String,String> data = new HashMap<String,String>();
    for(Flight f : candidates.keySet()) {
      Airport dest = candidates.get(f);
      data.put(f.getNumber(),dest.getCode());
    }
    if (data.size() == 0) {
      data.put("AA 001", "LAX");
      data.put("AA 002", "JFK");
    }
    return data;
  }

  @EJB private AirportDAO airportDAO;
  @EJB private FlightDAO flightDAO;
}
