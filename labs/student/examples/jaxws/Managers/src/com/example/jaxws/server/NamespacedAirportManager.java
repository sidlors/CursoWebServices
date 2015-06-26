
package com.example.jaxws.server;

import com.example.traveller.dao.pojo.AirportDAO;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author education.com
 */
@WebService(
  targetNamespace="urn://com.example.managerNS")
public class NamespacedAirportManager {
  @WebMethod
  @RequestWrapper(className="com.example.jaxws.server.NamespacedAirportManagerAddAirportRequest")
  @ResponseWrapper(className="com.example.jaxws.server.NamespacedAirportManagerAddAirportResponse")
  public long
  addAirport(String code, String name) {
    return dao.add(null, code, name).getId();
  }
  private AirportDAO dao = new AirportDAO();
  // ...
  static public void
  main(String[] args) {
    String url =
      "http://localhost:8080/namespacedManager";
    if (args.length > 0)
      url = args[1];
    NamespacedAirportManager manager = new NamespacedAirportManager();
    Endpoint endpoint =
      Endpoint.publish(url, manager);
  }
}
