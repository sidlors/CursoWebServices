/*
 */

package com.example.jaxrs.resources.helpers;

import com.example.jaxrs.resources.FlightRM;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author education.com
 */
@XmlRootElement(name="airport")
public class Flight {
  private String code;
  @XmlAttribute public String ref;
  @XmlTransient 
  public com.example.traveller.model.Flight flight;
  public Flight() {}
  public 
  Flight(com.example.traveller.model.Flight f, UriBuilder ub) {
    code = f.getNumber();
    ref = buildRef(f,ub);
    flight = f;
  }
  private String 
  buildRef(com.example.traveller.model.Flight f, UriBuilder ub) {
    URI result = ub.fromResource(FlightRM.class).build();
    return result.toString();
  }
}
