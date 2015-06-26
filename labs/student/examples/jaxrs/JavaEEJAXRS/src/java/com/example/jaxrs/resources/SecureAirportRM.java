/*
 */
package com.example.jaxrs.resources;

import com.example.traveller.dao.pojo.AirportDAO;
import com.example.traveller.model.Airport;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author education.com
 */

@Path("/secureAirports")
@PermitAll
public class SecureAirportRM {
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
  @RolesAllowed("administrator")
  public String addAirport( 
          @FormParam("code") String code,
          @FormParam("name") String name ) {
    Airport newAirport = null;
    try { 
      newAirport = dao.add( null, code, name );
      webContext.log("add: " +
                       secContext.getUserPrincipal());
    }
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
    Airport airport = dao.findByCode( null, code );
    return airport;
  }

  @GET
  @Path("/xmlByCode/{code}")
  @Produces({"application/xml"})
  public Airport getXmlByCode(
          @PathParam("code") String code ) {
    Airport airport = dao.findByCode( null, code );
    return airport;
  }

  @GET
  @Path("/jsonByCode/{code}")
  @Produces({"application/json"})
  public Airport getJsonByCode(
          @PathParam("code") String code ) {
    Airport airport = dao.findByCode( null, code );
    return airport;
  }

  private AirportDAO dao = new AirportDAO();
  @Context SecurityContext secContext;
  @Context ServletContext webContext;
}
