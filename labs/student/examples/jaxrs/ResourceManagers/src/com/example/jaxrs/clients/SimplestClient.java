/*
 */

package com.example.jaxrs.clients;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * Simplest JAX-RS client app
 * @author education.com
 */
public class SimplestClient {
  static public void main( String[] args )
          throws Exception {
    String contextURL = "http://localhost:8080/jaxrs";
    String resourcePath = "/airports";
    String requestPath = "/numAirports";
    String urlString =
      contextURL + resourcePath + requestPath;
    URL url = new URL( urlString );
    InputStream result = (InputStream) url.getContent();
    Scanner scanner = new Scanner( result );
    System.out.println( "Result: " + scanner.next() );
  }
}
