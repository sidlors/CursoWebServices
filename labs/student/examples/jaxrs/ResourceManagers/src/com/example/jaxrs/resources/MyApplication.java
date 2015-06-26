
package com.example.jaxrs.resources;

import com.example.jaxrs.resources.providers.DAOExceptionMapper;
import com.example.jaxrs.resources.providers.MapMessageBodyWriter;
import com.example.jaxrs.resources.providers.TravellerJAXBResolver;
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
    s.add(PlannerRM.class);
    s.add(MapMessageBodyWriter.class);
    s.add(DAOExceptionMapper.class);
    s.add(TravellerJAXBResolver.class);
    return s;
  } 
}
