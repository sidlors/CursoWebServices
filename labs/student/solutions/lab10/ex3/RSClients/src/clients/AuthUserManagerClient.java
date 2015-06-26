package clients;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;

public class AuthUserManagerClient {
  public static void main(String[] args){
    AuthUserManagerClient.addUser();
    // AuthUserManagerClient.findUser(3);
    // AuthUserManagerClient.updateUser(1, "Joe Bob", "Joe@example.com");
  }

  public static void addUser(){
    Client client = Client.create(); // Add Jersey Client

    // Create Web Resource
    String url = "http://localhost:8080/RSInContainer/rest/users/add";

    String username = "tracy";
    String password = "password";

    ClientFilter authFilter =
            new HTTPBasicAuthFilter(username,password);
    client.addFilter(authFilter);
    WebResource resource = client.resource( url );

    // Add parameters
    MultivaluedMap<String, String> params = new MultivaluedMapImpl();
    params.add( "name", "John Doe" );
    params.add( "email", "john@example.com");

      String result =
        resource
        .type( "application/x-www-form-urlencoded" )
        .post( String.class, params );
      System.out.println( result );
  }


  public static void findUser(long id){
    Client client = Client.create(); // Add Jersey Client

    // Create Web Resource
    String url = "http://localhost:8080/RSInContainer/rest/users/" + id;

    String username = "tracy";
    String password = "password";

    ClientFilter authFilter =
            new HTTPBasicAuthFilter(username,password);
    client.addFilter(authFilter);
    WebResource resource = client.resource( url );

    // Add parameters
    ClientResponse response = resource.get(ClientResponse.class);
    System.out.println( response.getEntity(String.class) );
  }

  public static void updateUser(long id, String name, String email){
    Client client = Client.create(); // Add Jersey Client

    // Create Web Resource
    String url = "http://localhost:8080/RSInContainer/rest/users/update";

    String username = "tracy";
    String password = "password";

    ClientFilter authFilter =
            new HTTPBasicAuthFilter(username,password);
    client.addFilter(authFilter);
    WebResource resource = client.resource( url );

    // Add parameters
    MultivaluedMap<String, String> params = new MultivaluedMapImpl();
    params.add( "id", "1" );
    params.add( "name", name );
    params.add( "email", email);

      String result =
        resource
        .type( "application/x-www-form-urlencoded" )
        .put ( String.class, params );
      System.out.println( result );

  }
}
