/*
 */

package com.example.jaxrs.clients;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Simplest Jersey Client
 * @author education.com
 */
public class CustomReturnJerseyClient {
  static public void main( String[] args ) {
    String urlString =
      "http://localhost:8080/jaxrs/airports/byCode/LGA";
    Client client = Client.create();
    WebResource resource =
      client.resource(urlString);
    String result = 
      resource
      .accept( "application/json" )
      .get( String.class );
    System.out.println( "Result: " + result );
  }
}
