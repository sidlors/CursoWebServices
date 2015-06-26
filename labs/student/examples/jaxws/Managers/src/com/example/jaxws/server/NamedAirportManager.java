
package com.example.jaxws.server;

import com.example.traveller.dao.pojo.AirportDAO;
import com.example.traveller.dao.pojo.GenericDAO;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.ws.Endpoint;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author education.com
 */
@WebService(portName="AirportMgr",
             serviceName="Managers")
public class NamedAirportManager {
  @WebMethod(operationName="add")
  @RequestWrapper(className="com.example.jaxws.server.NamedAirportManagerAddAirportRequest")
  @ResponseWrapper(className="com.example.jaxws.server.NamedAirportManagerAddAirportResponse")
  public long
  addAirport(@WebParam(name="code") String code,
              @WebParam(name="name") String name) {
    return dao.add(null, code, name).getId();
  }
  private AirportDAO dao = new AirportDAO();
  // ...
  static public void
  main(String[] args) {
    String url =
      "http://localhost:8080/namedManager";
    if (args.length > 0)
      url = args[1];
    NamedAirportManager manager = new NamedAirportManager();
    Endpoint endpoint =
      Endpoint.publish(url, manager);
  }
}
