
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
@WebService(portName="AirportMgr",
             serviceName="ManagersRPCLit")
@SOAPBinding(style=Style.RPC,
    parameterStyle=ParameterStyle.WRAPPED,
    use=Use.LITERAL)
public class RPCLiteralAirportManager {
  @WebMethod
  @RequestWrapper(className="com.example.jaxws.server.RPCLiteralAirportManagerAddAirportRequest")
  @ResponseWrapper(className="com.example.jaxws.server.RPCLiteralAirportManagerAddAirportResponse")
  public long
  addAirport(@WebParam(name="code") String code,
              @WebParam(name="name") String name) {
    return dao.add(null, code, name).getId();
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
      "http://localhost:8080/rpcLiteralManager";
    if (args.length > 0)
      url = args[1];
    RPCLiteralAirportManager manager =
      new RPCLiteralAirportManager();
    Endpoint endpoint =
      Endpoint.publish(url, manager);
  }
}
