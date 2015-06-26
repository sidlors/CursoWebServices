
package com.example.jaxws.server;

import com.example.traveller.dao.pojo.AirportDAO;
import com.example.traveller.model.Airport;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author education.com
 */
@WebService(portName="AirportMgr",
            serviceName="ManagersRPCEnc")
@SOAPBinding(style=SOAPBinding.Style.RPC,
             use=SOAPBinding.Use.ENCODED)
public class RPCEncodedAirportManager {
  @WebMethod
  @RequestWrapper(className="com.example.jaxws.server.RPCEncodedAirportManagerAddAirportRequest")
  @ResponseWrapper(className="com.example.jaxws.server.RPCEncodedAirportManagerAddAirportResponse")
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
      "http://localhost:8080/rpcEncodedManager";
    if (args.length > 0)
      url = args[1];
    RPCEncodedAirportManager manager =
      new RPCEncodedAirportManager();
    Endpoint endpoint =
      Endpoint.publish(url, manager);
  }
}
