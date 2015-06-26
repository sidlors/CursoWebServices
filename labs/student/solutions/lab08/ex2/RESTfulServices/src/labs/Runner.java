package labs;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;

public class Runner {

  public static void main(String[] args) throws IOException {
    String url = "http://localhost:8081/jaxrs";
    HttpServer server = HttpServerFactory.create( url );
    server.start();
  }

}
