
package com.example.jaxws.server;

import com.example.traveller.dao.pojo.AirportDAO;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

/**
 *AirportManager Web Service, deployed to a web container.
 * @author education.com
 */
@WebService(serviceName="SecureManagerWS")
@DeclareRoles({"client","administrator"})
public class SecureAirportManager {
  @WebMethod @RolesAllowed("administrator")
  public long addAirport(String code, String name) {
    String user = context.getUserPrincipal().getName();
    MessageContext msgContext = context.getMessageContext();
    ServletContext webContext = (ServletContext)
      msgContext.get(MessageContext.SERVLET_CONTEXT);
    webContext.log("add requested by: " + user);
    return dao.add(null, code, name).getId();
  }
  @WebMethod @PermitAll
  public String getNameByCode(String code) {
    return dao.findByCode(null, code).getName();
  }
  // ...
  private AirportDAO dao = new AirportDAO();
  @Resource WebServiceContext context;
}
