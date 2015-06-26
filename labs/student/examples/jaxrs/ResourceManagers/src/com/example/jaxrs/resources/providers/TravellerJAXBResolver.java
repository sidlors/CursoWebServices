/*
 */

package com.example.jaxrs.resources.providers;

import com.example.traveller.model.Airport;
import com.example.traveller.model.Flight;
import com.example.traveller.model.Passenger;
import com.example.traveller.model.Payment;
import com.example.traveller.model.Ticket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

/**
 *
 * @author education.com
 */
@Provider
public class TravellerJAXBResolver
implements ContextResolver<JAXBContext> {
  public JAXBContext getContext(Class<?> type) {
    return relevantTypes.contains(type) ? context : null;
  }
  private Class<?>[] typesInPackage = {
    Airport.class, Flight.class, Passenger.class,
    Ticket.class, Payment.class
  };
  private Set<Class<?>> relevantTypes =
    new HashSet<Class<?>>(typesInPackage.length);
  { relevantTypes.addAll(Arrays.asList(typesInPackage)); }
  private Logger log = 
    Logger.getLogger(TravellerJAXBResolver.class.getName());
  private JAXBContext context = null;
  {
    try { JAXBContext.newInstance(typesInPackage); }
    catch( Exception ex ) { log.log(Level.OFF, "", ex);};
  }
}
