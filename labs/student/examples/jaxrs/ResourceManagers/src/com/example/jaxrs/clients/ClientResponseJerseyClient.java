/*
 */

package com.example.jaxrs.clients;

import com.example.traveller.model.Airport;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Simplest Jersey Client
 * @author education.com
 */
public class ClientResponseJerseyClient {
  static public void main( String[] args ) {
    String urlString =
      "http://localhost:8080/jaxrs/airports/byCode/LGA";
    Client client = Client.create();
    WebResource resource = client.resource(urlString);
    ClientResponse response =
      resource.accept( "application/json" )
      .get( ClientResponse.class );
    System.out.println("Code:   " +
                  response.getStatus());
    System.out.println("Result: " +
                  response.getEntity(Airport.class));
  }
}
