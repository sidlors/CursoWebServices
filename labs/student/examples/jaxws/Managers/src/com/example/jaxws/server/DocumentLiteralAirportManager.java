
package com.example.jaxws.server;

import com.example.traveller.dao.pojo.AirportDAO;
import com.example.traveller.model.Airport;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.Endpoint;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author education.com
 */
@WebService(portName="DocLiteralAirportMgr",
             serviceName="ManagersDoc")
@SOAPBinding(style=Style.DOCUMENT,
    parameterStyle=ParameterStyle.BARE,
    use=Use.LITERAL)
public class DocumentLiteralAirportManager {
  @WebMethod
  @RequestWrapper(className="com.example.jaxws.server.DocumentLiteralAirportManagerAddAirportRequest")
  @ResponseWrapper(className="com.example.jaxws.server.DocumentLiteralAirportManagerAddAirportResponse")
  public long
  addAirport(@WebParam(name="airport")
               Airport airport ) {
    return dao.add(null, airport.getCode(), airport.getName()).getId();
  }
  @WebMethod
  public String[]
  findNeighbors(
      @WebParam(name="code") String code ) {
    Airport[] neighbors =
      dao.findNeighbors( null, code );
    if (neighbors.length == 0)
      return new String[]{ "JFK", "SFO" };
    List<String> result = new ArrayList<String>();
    for ( Airport neighbor : neighbors )
      result.add( neighbor.getCode() );
    return 
      result.toArray( new String[0] );
  }
  private AirportDAO dao = new AirportDAO();
  // ...
  static public void
  main(String[] args) {
    String url =
      "http://localhost:8080/docLiteralManager";
    if (args.length > 0)
      url = args[1];
    DocumentLiteralAirportManager manager =
      new DocumentLiteralAirportManager();
    Endpoint endpoint =
      Endpoint.publish(url, manager);
  }
}
