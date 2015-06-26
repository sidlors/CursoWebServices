
package com.example.jaxrs.resources;

import com.example.jaxrs.resources.providers.EJBProvider;
import com.example.jaxrs.resources.providers.MapMessageBodyWriter;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * Class used to indicate types that represent JAX-RS root resources.
 * @author education.com
 */
public class MyApplication
        extends Application {
  public Set<Class<?>> getClasses() {
    Set<Class<?>> s = new HashSet<Class<?>>();
    s.add(AirportRM.class);
    s.add(BetterAirportRM.class);
    s.add(SecureAirportRM.class);
    s.add(PlannerRM.class);
    s.add(FlightRM.class);
    s.add(EJBProvider.class);
    s.add(MapMessageBodyWriter.class);
    return s;
  }
}
