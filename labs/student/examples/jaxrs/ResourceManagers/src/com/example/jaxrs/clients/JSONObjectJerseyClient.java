/*
 */

package com.example.jaxrs.clients;

import com.example.traveller.model.Airport;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * Simplest Jersey Client
 * @author education.com
 */
public class JSONObjectJerseyClient {
  static public void main( String[] args ) {
    String urlString =
      "http://localhost:8080/jaxrs/airports/byCode/LGA";
    Client client = Client.create();
    WebResource resource =
      client.resource(urlString);
    Airport result =
      resource
      .accept( "application/json" )
      .get( Airport.class );
    System.out.println( "Result: " + result );
  }
}
