/*
 */

package com.example.jaxrs.resources;

import com.example.traveller.model.Airport;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author education.com
 */
public class AirportResource {
  AirportResource(Airport airport) {
    this.airport = airport;
  }
  @GET @Produces({"application/xml","application/json"})
  public com.example.jaxrs.resources.helpers.Airport getDefault() {
    UriBuilder ub = uriInfo.getBaseUriBuilder();
    return
      new com.example.jaxrs.resources.helpers.Airport(airport,ub);
  }
  @GET @Path("/code") public String getCode() {
    return airport.getCode();
  }
  @GET @Path("name") public String getName() {
    return airport.getName();
  }
  private Airport airport;
  @Context UriInfo uriInfo;
}
