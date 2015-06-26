/*
 */
package com.example.jaxrs.resources;

import com.example.traveller.dao.ejb.AirportDAO;
import com.example.traveller.model.Airport;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List; 
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author education.com
 */
@Path("/betterAirports")
public class BetterAirportRM {
  // ...
  @GET
  @Path("/numAirports")
  public String getNumAirports() {
    List<String> codes = dao.getAllCodes();
    return
      (codes == null) ? "0" : "" + codes.size();
  }
  // ...
  @POST
  @Path("/add")
  public Response addAirport(
          @FormParam("code") String code,
          @FormParam("name") String name ) {
    Airport newAirport = dao.add( code, name);
    UriBuilder ub = uriInfo.getBaseUriBuilder();
    URI uri = 
      ub.path(AirportRM.class,"getByCode").build(code);
    return Response.created(uri).entity(newAirport).build();
  }
  // ...
  @GET
  @Path("/nameByCode/{code}")
  public String getNameByCode(
          @PathParam("code") String code ) {
    Airport airport = dao.findByCode( code); 
    return
      (airport != null) ? airport.getName() : "(not found)";
  }

  @GET
  @Path("/codeByName")
  public String getCodeByName(
          @QueryParam("name") String name ) {
    Airport[] airports = dao.findByName(  name );
    if ((airports == null) || (airports.length == 0))
      return "(no such airport)";
    else if (airports.length == 1)
      return airports[0].getCode();
    else
      return "(too many candidates)";
  }

  @GET
  @Path("/byCode/{code}")
  @Produces({"application/xml","application/json"})
  public Airport getByCode(
          @PathParam("code") String code ) {
    return dao.findByCode(  code );
  }


  @GET
  @Path("/xmlByCode/{code}")
  @Produces(MediaType.APPLICATION_XML)
  public Airport getXmlByCode(
          @PathParam("code") String code ) {
    Airport airport = dao.findByCode(  code );
    return airport;
  }

  @GET
  @Path("/jsonByCode/{code}")
  @Produces(MediaType.APPLICATION_JSON)
  public Airport getJsonByCode(
          @PathParam("code") String code ) {
    Airport airport = dao.findByCode(  code );
    return airport;
  }

  @Path("/list")
  @GET
  @Produces({"application/xml","application/json"})
  public List<com.example.jaxrs.resources.helpers.Airport>
  listWithLinks() {
    UriBuilder ub = uriInfo.getBaseUriBuilder();
    List<Airport> candidates = dao.list();
    List<com.example.jaxrs.resources.helpers.Airport> result =
      new ArrayList<com.example.jaxrs.resources.helpers.Airport>();
    for (Airport a : candidates) {
      com.example.jaxrs.resources.helpers.Airport proxy =
        new com.example.jaxrs.resources.helpers.Airport(a, ub);
      result.add(proxy);
    }
    return result;
  }

  @Path("/listComplete")
  @GET
  @Produces({"application/xml"})
  public List<Airport>
  listComplete() {
    List<Airport> result = dao.list();
    return result;
  }

  @GET
  @Produces({"application/xml","application/json"})
  public List<com.example.jaxrs.resources.helpers.Airport>
  getDefault() {
    return listWithLinks();
  }

  private AirportDAO dao = new AirportDAO();
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
  // ...
}
