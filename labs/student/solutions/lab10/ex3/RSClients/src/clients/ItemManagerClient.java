package clients;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;

public class ItemManagerClient {

    public static void main(String[] args) {
      Client client = Client.create(); // Add Jersey Client

      // Create Web Resource
      String url = "http://localhost:8081/jaxrs/items/add";
      WebResource resource = client.resource( url );

      // Add parameters
      MultivaluedMap<String, String> params = new MultivaluedMapImpl();
      params.add( "description", "Guitar" );
      
      String result =
        resource
        .type( "application/x-www-form-urlencoded" )
        .post( String.class, params );
      System.out.println( result );
    }

}
