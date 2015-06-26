/*
 */

package com.example.jaxws;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Source;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Provider;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceProvider;

/**
 *
 * @author education.com
 */
@ServiceMode(Mode.PAYLOAD)
@WebServiceProvider(portName="Hello",serviceName="Examples",
                    targetNamespace="urn:examples")
public class MessagingAPIServer implements Provider<Source> {
  MessagingAPIServer() throws JAXBException {
    Class msgClass = MessagingAPIMessage.class;
    jaxbContext = JAXBContext.newInstance( msgClass );
  }
  // ...
  public Source invoke(Source payload) {
    try {
      Unmarshaller u = jaxbContext.createUnmarshaller();
      MessagingAPIMessage message =
          (MessagingAPIMessage) u.unmarshal( payload );
      message.setResult("Hello, " + message.getArgument());
      return new JAXBSource( jaxbContext, message );
    }
    catch( Exception ex )
    { throw new WebServiceException( ex ); }
  }
  private JAXBContext jaxbContext;
  public static void main(String[] args) throws Exception {
    String url = "http://127.0.0.1:8081/MessagingAPI";
    MessagingAPIServer server = new MessagingAPIServer();
    Endpoint endpoint = Endpoint.publish( url, server );
  }
}
