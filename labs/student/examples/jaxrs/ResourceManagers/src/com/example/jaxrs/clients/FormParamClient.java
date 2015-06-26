/*
 */
package com.example.jaxrs.clients;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * JAX-RS client app
 * @author education.com
 */
public class FormParamClient {
  static public void main( String[] args )
          throws Exception {
    String contextURL = "http://localhost:8080/jaxrs";
    String resourcePath = "/airports";
    String requestPath = "/add";
    String code = "LGA";           // need URL-encoding
    String name = "LaGuardia"; // need URL-encoding
    String urlString =
           contextURL + resourcePath + requestPath;
    URL url = new URL( urlString );
    HttpURLConnection connection =
           (HttpURLConnection) url.openConnection();
    connection.setRequestMethod( "POST" );
    connection.setAllowUserInteraction( true );
    connection.setDoOutput( true );
    connection.setDoInput( true );
    connection.connect();
    OutputStream os = connection.getOutputStream();
    PrintWriter writer = new PrintWriter( os );
    writer.print( "code=" + code + "&name=" + name );
    writer.close();
    InputStream result = connection.getInputStream();
    BufferedReader reader =
      new BufferedReader( new InputStreamReader(result) );
    System.out.println( "Result: " + reader.readLine() );
  }
}
