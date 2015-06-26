/*
 */

package com.example.jaxrs.resources.providers;

import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;
import java.lang.reflect.Type;

import javax.ejb.EJB;

import javax.naming.Binding;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

/**
 *
 * @see http://blogs.sun.com/sandoz/entry/ejb_injection
 */
@Provider
public class EJBProvider implements InjectableProvider<EJB, Type> {

    @Context ServletContext appContext;

    public ComponentScope getScope() {
      return ComponentScope.Singleton;
    }

    public Injectable<Object>
    getInjectable( ComponentContext cc, EJB ejb, Type t ) {
      if (!(t instanceof Class)) return null;

      String contextPath = appContext.getContextPath();
      Class c = (Class) t;
      String fqClassName = c.getName();
      String className = c.getSimpleName();
      String mappedName = ejb.mappedName();
      String portableGlobalName =
              "java:global" + contextPath + "/" + className;
      String name =
             mappedName.equals("") ? portableGlobalName : mappedName;
      try {
        javax.naming.Context ic = new InitialContext();
        Object o = null;
        try { o = ic.lookup( name ); }  // EJB 3.1
        catch( NamingException e1 ) {
          o = ic.lookup( fqClassName ); //; EJB 3.0/Glassfish
        }
        if (o == null)
          return null;
        else {
          final Object injectable = o;
          return new Injectable<Object>() {
            public Object getValue() {
              return injectable;
            }
          };
        }
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
  }
}
