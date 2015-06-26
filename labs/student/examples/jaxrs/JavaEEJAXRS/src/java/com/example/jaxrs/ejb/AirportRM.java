/*
 */
package com.example.jaxrs.ejb;

import com.example.traveller.dao.ejb.AirportDAO;
import com.example.traveller.model.Airport;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author education.com
 */
@Path("/airportsSLSB")
@Stateless
public class AirportRM {
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
  public String addAirport( 
          @FormParam("code") String code,
          @FormParam("name") String name ) {
    Airport newAirport = null;
    try { newAirport = dao.add(code, name); }
    catch( Exception ex ) {}
    return (newAirport != null) ? "ok" : "fail";
  }
  // ...
  @GET
  @Path("/nameByCode/{code}")
  public String getNameByCode(
          @PathParam("code") String code ) {
    Airport airport = null;
    try { airport = dao.findByCode(code); }
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
    Airport[] airports = dao.findByName(name);
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
    Airport airport = dao.findByCode(code);
    return airport;
  }

  @GET
  @Path("/xmlByCode/{code}")
  @Produces({"application/xml"})
  public Airport getXmlByCode(
          @PathParam("code") String code ) {
    Airport airport = dao.findByCode(code);
    return airport;
  }

  @GET
  @Path("/jsonByCode/{code}")
  @Produces({"application/json"})
  public Airport getJsonByCode(
          @PathParam("code") String code ) {
    Airport airport = dao.findByCode(code);
    return airport;
  }

  @DELETE
  @Path("/removeByCode/{code}")
  public void removeByCode(
          @PathParam("code") String code ) {
    dao.remove(dao.findByCode(code));
  }

  @EJB private AirportDAO dao;
}
