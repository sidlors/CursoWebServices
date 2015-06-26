
package com.example.jaxws.ejb;

import com.example.traveller.dao.ejb.AirportDAO;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author education.com
 */
@WebService(serviceName="SingletonManagerEJBWS")
@Singleton
@Lock(LockType.WRITE)
public class SingletonAirportManager {
  @WebMethod
  @RequestWrapper(className="com.example.jaxws.server.SingletonAirportManagerAddAirportRequest")
  @ResponseWrapper(className="com.example.jaxws.server.SingletonAirportManagerAddAirportResponse")
  public long addAirport(String code, String name) {
    return dao.add(code, name).getId();
  }
  @WebMethod(operationName="removeById")
  @RequestWrapper(className="com.example.jaxws.server.SingletonAirportManagerRemoveByIdRequest")
  @ResponseWrapper(className="com.example.jaxws.server.SingletonAirportManagerRemoveByIdResponse")
  public void removeAirport(long id) {
    dao.remove(id);
  }
  @WebMethod(operationName="removeByCode")
  @RequestWrapper(className="com.example.jaxws.server.SingletonAirportManagerRemoveByCodeRequest")
  @ResponseWrapper(className="com.example.jaxws.server.SingletonAirportManagerRemoveByCodeResponse")
  public void removeAirport(String code) {
    dao.remove(dao.findByCode(code));
  }
  @WebMethod
  @Lock(LockType.READ)
  public String getNameByCode(String code) {
    return dao.findByCode(code).getName();
  }
  @EJB private AirportDAO dao;  
  // ...
}
