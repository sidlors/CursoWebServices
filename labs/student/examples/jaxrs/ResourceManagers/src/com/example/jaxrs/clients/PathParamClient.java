/*
 */

package com.example.jaxrs.clients;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * JAX-RS client app
 * @author education.com
 */
public class PathParamClient {
  static public void main( String[] args )
          throws Exception {
    String contextURL = "http://localhost:8080/jaxrs";
    String resourcePath = "/airports";
    String requestPath = "/nameByCode/";
    String param = "LGA";     // need URL-encoding
    String urlString =
      contextURL + resourcePath + requestPath + param;
    URL url = new URL( urlString );
    InputStream result = (InputStream) url.getContent();
    BufferedReader reader = 
      new BufferedReader(new InputStreamReader(result));
    System.out.println( "Result: " + reader.readLine() );
  }
}
