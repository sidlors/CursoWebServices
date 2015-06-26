
package com.example.jaxws.server;

import com.example.traveller.dao.pojo.AirportDAO;
import com.example.traveller.dao.pojo.GenericDAO;
import javax.jws.WebMethod;
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
@WebService
public class BetterAirportManager {
  @WebMethod
  @RequestWrapper(className="com.example.jaxws.server.BetterAirportManagerAddAirportRequest")
  @ResponseWrapper(className="com.example.jaxws.server.BetterAirportManagerAddAirportResponse")
  public long
  addAirport(String code, String name) {
    return dao.add(null, code, name).getId();
  }
  @WebMethod(operationName="removeById")
  @RequestWrapper(className="com.example.jaxws.server.BetterAirportManagerRemoveByIdRequest")
  @ResponseWrapper(className="com.example.jaxws.server.BetterAirportManagerRemoveByIdResponse")
  public void removeAirport(long id) {
    dao.remove(null, id);
  }
  @WebMethod(operationName="removeByCode")
  @RequestWrapper(className="com.example.jaxws.server.BetterAirportManagerRemoveByCodeRequest")
  @ResponseWrapper(className="com.example.jaxws.server.BetterAirportManagerRemoveByCodeResponse")
  public void removeAirport(String code) {
    EntityManager em =
      GenericDAO.getEMF().createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    dao.remove(em, dao.findByCode(em, code));
    tx.commit();
    em.close();
  }
  private AirportDAO dao = new AirportDAO();
  // ...
  static public void
  main(String[] args) {
    String url =
      "http://localhost:8080/betterManager";
    if (args.length > 0)
      url = args[1];
    BetterAirportManager manager = new BetterAirportManager();
    Endpoint endpoint =
      Endpoint.publish(url, manager);
  }
}
