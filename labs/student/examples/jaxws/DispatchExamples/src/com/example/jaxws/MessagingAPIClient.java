/*
 */

package com.example.jaxws;

import javax.xml.bind.JAXBContext;
import javax.xml.namespace.QName;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Response;
import javax.xml.ws.Service;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.soap.SOAPBinding;

/**
 *
 * @author education.com
 */
public class MessagingAPIClient {
 public static void main(String[] as) throws Exception {
   QName serviceName =
     new QName("urn:examples", "Examples");
   QName portName = new QName("urn:examples", "Hello");
   Service service = Service.create( serviceName );
   String url = "http://127.0.0.1:8081/MessagingAPI";
   service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING,
                     url);
   Class msgClass = MessagingAPIMessage.class;
   JAXBContext jaxbCtx =
     JAXBContext.newInstance(msgClass);
   Dispatch<Object> port =
     service.createDispatch(portName,jaxbCtx,Mode.PAYLOAD);
   MessagingAPIMessage request =
       new MessagingAPIMessage("sayHello", "Tracy");
   // synchronous
   MessagingAPIMessage response =
       (MessagingAPIMessage) port.invoke(request);
   System.out.println("Response: " + response.getResult());
   // asynchronous
   AsyncHandler<Object> responseHandler =
       new AsyncHandler<Object>() {
         public void handleResponse(Response<Object> resp){
           try {
             MessagingAPIMessage result =
               (MessagingAPIMessage) resp.get();
             System.out.println(result.getResult());
           }
           catch( Exception e )
           {}
         }
       };
   port.invokeAsync( request, responseHandler );
 }
}