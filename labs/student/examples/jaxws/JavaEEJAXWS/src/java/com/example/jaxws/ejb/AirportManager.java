
package com.example.jaxws.ejb;

import com.example.traveller.dao.ejb.AirportDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *AirportManager Web Service, deployed to a web container.
 * @author education.com
 */
@WebService(serviceName="AirportManagerEJBWS")
@Stateless
public class AirportManager {
  public long
  addAirport(String code, String name) {
    return dao.add(code, name).getId();
  }
  @EJB private AirportDAO dao;
}
