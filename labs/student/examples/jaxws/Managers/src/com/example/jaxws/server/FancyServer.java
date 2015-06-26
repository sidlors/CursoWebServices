
package com.example.jaxws.server;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.xml.ws.Endpoint;

/**
 *
 * @author education.com
 */
public class FancyServer {
  public static 
  void main( String[] args ) throws Exception {
      HttpServer server =
        HttpServer.create(new InetSocketAddress(8080),10);
      Executor executor = Executors.newFixedThreadPool(10);
      server.setExecutor(executor);
      HttpContext context =
        server.createContext("/fancyServer");
      AirportManager manager = new AirportManager();
      Endpoint endpoint = Endpoint.create(manager);
      endpoint.publish(context);
      server.start();
  }
}
