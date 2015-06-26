/*
 */
package com.example.jaxrs.resources;

import com.example.traveller.dao.pojo.AirportDAO;
import com.example.traveller.model.Airport;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.spi.resource.Singleton;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author education.com
 */
@Singleton
@Path("/airports")
public class AirportRM {
  // ...
  @GET
  @Path("/numAirports")
  public String getNumAirports() {
    List<String> codes = dao.getAllCodes( null );
    return
      (codes == null) ? "0" : "" + codes.size();
  }
  // ...
  @POST
  @Path("/add")
  public String addAirport( 
          @FormParam("code") String code,
          @FormParam("name") String name ) {
    Airport newAirport = null;
    try { newAirport = dao.add( null, code, name ); }
    catch( Exception ex ) {}
    return (newAirport != null) ? "ok" : "fail";
  }
  // ...
  @GET
  @Path("/nameByCode/{code}")
  public String getNameByCode(
          @PathParam("code") String code ) {
    Airport airport = null;
    try { airport = dao.findByCode( null, code ); }
    catch( Exception ex ) {
      System.out.println( "Code: " + code + " Ex: " + ex );
    }
    return
      (airport != null) ? airport.getName() : "(not found)";
  }

  @GET
  @Path("/codeByName")
  public String getCodeByName(
          @QueryParam("name") String name ) {
    Airport[] airports = dao.findByName( null, name );
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
    return dao.findByCode( null, code );
  }


  @GET
  @Path("/xmlByCode/{code}")
  @Produces(MediaType.APPLICATION_XML)
  public Airport getXmlByCode(
          @PathParam("code") String code ) {
    Airport airport = dao.findByCode( null, code );
    return airport;
  }

  @GET
  @Path("/jsonByCode/{code}")
  @Produces(MediaType.APPLICATION_JSON)
  public Airport getJsonByCode(
          @PathParam("code") String code ) {
    Airport airport = dao.findByCode( null, code );
    return airport;
  }

  private AirportDAO dao = new AirportDAO();

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
