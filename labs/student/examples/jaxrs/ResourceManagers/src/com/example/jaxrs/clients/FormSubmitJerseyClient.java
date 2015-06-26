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
public class FormSubmitJerseyClient {
  static public void main( String[] args ) {
    String url = "http://localhost:8080/jaxrs/airports/add";
    Client client = Client.create();
    WebResource resource = client.resource(url);
    MultivaluedMap<String,String> params =
      new MultivaluedMapImpl();
    params.add( "code", "JFK" );
    params.add( "name", "John F. Kennedy Airport" );
    String result = 
      resource
      .type( "application/x-www-form-urlencoded" )
      .post( String.class, params );
    System.out.println( "Result: " + result );
  }
}
