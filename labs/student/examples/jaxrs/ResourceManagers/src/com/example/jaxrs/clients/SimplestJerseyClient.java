/*
 */

package com.example.jaxrs.clients;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * Simplest Jersey Client
 * @author education.com
 */
public class SimplestJerseyClient {
  static public void main( String[] args ) {
    String contextURL = "http://localhost:8080/jaxrs";
    String resourcePath = "/airports";
    String requestPath = "/numAirports";
    String urlString =
           contextURL + resourcePath + requestPath;
    Client client = Client.create();
    WebResource resource =
      client.resource( urlString );
    String result = resource.get( String.class );
    System.out.println( "Result: " + result );
  }
}
