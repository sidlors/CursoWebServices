
package com.example.jaxws.ejb;

import com.example.traveller.dao.ejb.AirportDAO;
import com.example.traveller.dao.ejb.GenericDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author education.com
 */
@WebService(serviceName="BetterManagerEJBWS")
@Stateless
public class BetterAirportManager {
  @WebMethod
  @RequestWrapper(className="com.example.jaxws.server.BetterAirportManagerAddAirportRequest")
  @ResponseWrapper(className="com.example.jaxws.server.BetterAirportManagerAddAirportResponse")
  public long
  addAirport(String code, String name) {
    return dao.add(code, name).getId();
  }
  @WebMethod(operationName="removeById")
  @RequestWrapper(className="com.example.jaxws.server.BetterAirportManagerRemoveByIdRequest")
  @ResponseWrapper(className="com.example.jaxws.server.BetterAirportManagerRemoveByIdResponse")
  public void removeAirport(long id) {
    dao.remove(id);
  }
  @WebMethod(operationName="removeByCode")
  @RequestWrapper(className="com.example.jaxws.server.BetterAirportManagerRemoveByCodeRequest")
  @ResponseWrapper(className="com.example.jaxws.server.BetterAirportManagerRemoveByCodeResponse")
  public void removeAirport(String code) {
    dao.remove(dao.findByCode(code));
  }
  @EJB private AirportDAO dao;  
  // ...
}
