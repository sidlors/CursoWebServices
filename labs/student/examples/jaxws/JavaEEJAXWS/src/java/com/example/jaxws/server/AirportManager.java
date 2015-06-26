
package com.example.jaxws.server;

import com.example.traveller.dao.pojo.AirportDAO;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 *AirportManager Web Service, deployed to a web container.
 * @author education.com
 */
@WebService(serviceName="AirportManagerWS")
public class AirportManager {
  public long
  addAirport(String code, String name) {
    return dao.add(null, code, name).getId();
  }
  private AirportDAO dao = new AirportDAO();
}
