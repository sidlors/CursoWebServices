package clients;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;

public class AuthItemManagerClient {

  public static void main(String[] args) {
    // AuthItemManagerClient.addItem("Acoustic Guitar");
    AuthItemManagerClient.findItem(1);
  }

  public static void addItem(String itemDescription){

      Client client = Client.create(); // Add Jersey Client

      // Create Web Resource
      String url = "http://localhost:8080/RSInContainer/rest/items/add";

      String username = "tracy";
      String password = "password";

      ClientFilter authFilter =
            new HTTPBasicAuthFilter(username,password);
      client.addFilter(authFilter);
      WebResource resource = client.resource( url );

      // Add parameters
      MultivaluedMap<String, String> params = new MultivaluedMapImpl();
      params.add( "description", itemDescription );

      String result =
        resource
        .type( "application/x-www-form-urlencoded" )
        .post( String.class, params );
      System.out.println( result );
  }

  public static void findItem(long id){

      Client client = Client.create(); // Add Jersey Client

      // Create Web Resource
      String url = "http://localhost:8080/RSInContainer/rest/items/" + id;

      String username = "tracy";
      String password = "password";

      ClientFilter authFilter =
            new HTTPBasicAuthFilter(username,password);
      client.addFilter(authFilter);
      WebResource resource = client.resource( url );

      // Find Item
      ClientResponse response = resource.get(ClientResponse.class);
      System.out.println( response.getEntity(String.class) );
  }

}
