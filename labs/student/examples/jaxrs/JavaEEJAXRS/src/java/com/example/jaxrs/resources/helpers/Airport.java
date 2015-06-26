/*
 */

package com.example.jaxrs.resources.helpers;

import com.example.jaxrs.resources.BetterAirportRM;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author education.com
 */
@XmlRootElement(name="airport")
public class Airport {
  @XmlValue public String code;
  @XmlAttribute public String ref;
  @XmlTransient public com.example.traveller.model.Airport airport;
  public Airport() {}
  public 
  Airport(com.example.traveller.model.Airport a, UriBuilder ub) {
    code = a.getCode();
    ref = buildRef(a,ub);
    airport = a;
  }
  private String 
  buildRef(com.example.traveller.model.Airport a, UriBuilder ub) {
    URI result = 
      ub.path(BetterAirportRM.class,"getByCode").build(a.getCode());
    return result.toString();
  }
}
