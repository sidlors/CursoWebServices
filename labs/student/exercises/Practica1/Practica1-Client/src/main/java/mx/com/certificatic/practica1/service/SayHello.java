
package mx.com.certificatic.practica1.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6b21 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SayHello", targetNamespace = "http://service.practica1.certificatic.com.mx/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SayHello {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://service.practica1.certificatic.com.mx/", className = "mx.com.certificatic.practica1.service.SayHello_Type")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://service.practica1.certificatic.com.mx/", className = "mx.com.certificatic.practica1.service.SayHelloResponse")
    @Action(input = "http://service.practica1.certificatic.com.mx/SayHello/sayHelloRequest", output = "http://service.practica1.certificatic.com.mx/SayHello/sayHelloResponse")
    public String sayHello(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}