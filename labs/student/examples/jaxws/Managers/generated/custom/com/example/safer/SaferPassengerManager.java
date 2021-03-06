
package com.example.safer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2-b02-rc1
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SaferPassengerManager", targetNamespace = "urn://Traveller/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SaferPassengerManager {


    /**
     * 
     * @param lastName
     * @param firstName
     * @return
     *     returns long
     * @throws AddPassengerFault_Exception
     */
    @WebMethod
    @WebResult(name = "result", targetNamespace = "urn://Traveller/")
    @RequestWrapper(localName = "addPassenger", targetNamespace = "urn://Traveller/", className = "com.example.safer.AddPassenger")
    @ResponseWrapper(localName = "addPassengerResponse", targetNamespace = "urn://Traveller/", className = "com.example.safer.AddPassengerResponse")
    public long addPassenger(
        @WebParam(name = "firstName", targetNamespace = "urn://Traveller/")
        String firstName,
        @WebParam(name = "lastName", targetNamespace = "urn://Traveller/")
        String lastName)
        throws AddPassengerFault_Exception
    ;

}
