/*
 */

package com.example.jaxws.common;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author education.com
 */
public class AuthenticationHandler
        implements SOAPHandler<SOAPMessageContext> {
  public boolean
  handleMessage(SOAPMessageContext smc) {
    Boolean outboundProperty =
      (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
    SOAPMessage msg = smc.getMessage();
    if (outboundProperty) {
      try { embedAuthenticationData(msg); }
      catch(Exception ex)
      {}
    } else {
      try { validateAuthenticationData(msg); }
      catch(Exception ex )
      {}
    }
    return true;
  }
  private void 
  embedAuthenticationData(SOAPMessage msg)
          throws SOAPException {
    SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
    Name authName = envelope.createName("auth");
    Name userName = envelope.createName("user");
    Name passwordName = envelope.createName("password");
    SOAPHeader header = msg.getSOAPHeader();
    SOAPHeaderElement newHeader =
      header.addHeaderElement(authName);
    newHeader.addAttribute(userName, "tracy");
    newHeader.addAttribute(passwordName, "password");
  }
  private void 
  validateAuthenticationData(SOAPMessage msg)
          throws SOAPException {
    SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
    Name authName = envelope.createName("auth");
    Name userName = envelope.createName("user");
    Name passwordName = envelope.createName("password");
    SOAPHeader header = msg.getSOAPHeader();
    for ( Iterator authNodes = header.getChildElements(authName);
            authNodes.hasNext(); ) {
      SOAPHeaderElement authHeader = (SOAPHeaderElement) authNodes.next();
      String user = authHeader.getAttributeValue(userName);
      String password = authHeader.getAttributeValue(passwordName);
      validate(user, password);
    }
  }
  private void
  validate(String userName, String password) {

  }

  public Set<QName> getHeaders() {
    return Collections.EMPTY_SET;
  }

  public boolean handleFault(SOAPMessageContext messageContext) {
    return true;
  }

  public void close(MessageContext context) {
  }

}
