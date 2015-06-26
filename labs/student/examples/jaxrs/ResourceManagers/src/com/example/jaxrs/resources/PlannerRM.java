/*
 */

package com.example.jaxrs.resources;

import com.example.traveller.dao.pojo.AirportDAO;
import com.example.traveller.dao.pojo.FlightDAO;
import com.example.traveller.model.Airport;
import com.example.traveller.model.Flight;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author education.com
 */
@Path("/planner")
public class PlannerRM {
  @Path("/neighborsSummary") @GET
  @Produces({"application/json"})
  public List<String>
  getNeighborsSummary(@QueryParam("code") String code) {
    Airport[] candidates =
      airportDAO.findNeighbors(null, code);
    List<String> result = new ArrayList<String>();
    for (Airport a : candidates)
      result.add(a.getCode());
    if (result.size() == 0) {
      result.add("LAX");
      result.add("JFK");
    }
    return result;
  }

  @Path("/neighborsWithLinks") @GET
  @Produces({"application/json"})
  public List<com.example.jaxrs.resources.helpers.Airport>
  getNeighborsWithLinks(@QueryParam("code") String code) {
    UriBuilder ub = uriInfo.getBaseUriBuilder();
    Airport[] candidates =
      airportDAO.findNeighbors(null, code);
    List<com.example.jaxrs.resources.helpers.Airport> result =
      new ArrayList<com.example.jaxrs.resources.helpers.Airport>();
    for (Airport a : candidates) {
      com.example.jaxrs.resources.helpers.Airport proxy =
        new com.example.jaxrs.resources.helpers.Airport(a, ub);
      result.add(proxy);
    }
    return result;
  }

  @Path("routesSummary") @GET
  @Produces({"application/xml","application/json"})
  public Map<String,String>
  getRoutesFromSummary(@QueryParam("start") String code) {
    Airport start = airportDAO.findByCode(null, code);
    Map<Flight,Airport> candidates =
      flightDAO.findRoutesFrom(null,start);
    Map<String,String> result = new HashMap<String,String>();
    for(Flight f : candidates.keySet()) {
      Airport dest = candidates.get(f);
      result.put(f.getNumber(),dest.getCode());
    }
    if (result.size() == 0) {
      result.put("AA 001", "LAX");
      result.put("AA 002", "JFK");
    }
    return result;
  }

  @Path("routesWithLinks") @GET
  @Produces({"application/xml","application/json"})
  public Response
  getRoutesFromWithLinks(@QueryParam("start") String code) {
    UriBuilder ub = uriInfo.getBaseUriBuilder();
    Airport start = airportDAO.findByCode(null, code);
    Map<Flight,Airport> candidates =
      flightDAO.findRoutesFrom(null,start);
    Map<com.example.jaxrs.resources.helpers.Flight,
        com.example.jaxrs.resources.helpers.Airport> result =
      new HashMap<com.example.jaxrs.resources.helpers.Flight,
                  com.example.jaxrs.resources.helpers.Airport>();
    for(Flight f : candidates.keySet()) {
      Airport dest = candidates.get(f);
      result.put(new com.example.jaxrs.resources.helpers.Flight(f,ub),
                 new com.example.jaxrs.resources.helpers.Airport(dest,ub));
    }
    return Response.ok(result).build();
  }

  private AirportDAO airportDAO = new AirportDAO();
  private FlightDAO flightDAO = new FlightDAO();
  @Context UriInfo uriInfo;

  static public void
  main(String[] args) throws IOException {
   String contextUrl =
      "http://localhost:8080/jaxrs";
    if (args.length > 0)
      contextUrl = args[1];
    HttpServer server =
      HttpServerFactory.create( contextUrl );
    server.start();
  }
}
