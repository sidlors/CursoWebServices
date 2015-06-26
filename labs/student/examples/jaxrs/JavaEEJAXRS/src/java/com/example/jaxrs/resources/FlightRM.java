/*
 */
package com.example.jaxrs.resources;

import com.example.traveller.dao.ejb.FlightDAO;
import com.example.traveller.model.Flight;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.spi.resource.Singleton;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author education.com
 */
@Singleton
@Path("/flights")
public class FlightRM {
  @GET @Path("/byNumber/{number}")
  @Produces({"application/xml","application/json"})
  public Flight getByNumber(
          @PathParam("number") String number) {
    return dao.findByNumber(number);
  }
  @Path("/byNumber/{number}/departs")
  public AirportResource getDepartsByNumber(
          @PathParam("number") String number) {
    Flight flight = dao.findByNumber(number);
    return new AirportResource(flight.getDeparts());
  }

  private FlightDAO dao = new FlightDAO();

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
  // ...
}
