package clients;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.PrintWriter;
import javax.ws.rs.core.MultivaluedMap;

public class AuctionManagerClient {
  private static PrintWriter pw = new PrintWriter(System.out, true);

  public static void main(String[] args){
    // AuctionManagerClient.createAuction(2, 1, 2, 10);
    AuctionManagerClient.findAuction(3);
    AuctionManagerClient.listAuctions();
    AuctionManagerClient.getHighBid(3);
    AuctionManagerClient.listBids(3);
  }

  public static void createAuction( long userId, long itemId, int nDays,
    double startPrice){

    Client client = Client.create(); // Add Jersey Client

    // Create Web Resource
    String url = "http://localhost:8080/RSInContainer/rest/auctions/create";
    WebResource resource = client.resource( url );

    // Add parameters
    MultivaluedMap<String, String> params = new MultivaluedMapImpl();
    params.add( "userId", String.valueOf(userId) );
    params.add( "itemId", String.valueOf(itemId) );
    params.add( "nDays", String.valueOf(nDays) );
    params.add( "startPrice", String.valueOf(startPrice) );

    String result =
        resource
        .type( "application/x-www-form-urlencoded" )
        .post( String.class, params );
      System.out.println( result );
  }


  public static void findAuction(long id){
    Client client = Client.create(); // Add Jersey Client

    // Create Web Resource
    String url = "http://localhost:8080/RSInContainer/rest/auctions/" + id;
    WebResource resource = client.resource( url );

    // Add parameters
    ClientResponse response = resource.get(ClientResponse.class);
    pw.println("----- Find ----");
    pw.println( response.getEntity(String.class) );
  }

  public static void listAuctions(){
    Client client = Client.create(); // Add Jersey Client

    // Create Web Resource
    String url = "http://localhost:8080/RSInContainer/rest/auctions/list";
    WebResource resource = client.resource( url );

    // Add parameters
    ClientResponse response = resource.get(ClientResponse.class);
    pw.println("----- List Auctions ----");
    pw.println( response.getEntity(String.class) );
  }

  public static void getHighBid(long id){
    Client client = Client.create(); // Add Jersey Client

    // Create Web Resource
    String url = "http://localhost:8080/RSInContainer/rest/auctions/" + id + "/highbid";
    WebResource resource = client.resource( url );

    // Add parameters
    ClientResponse response = resource.get(ClientResponse.class);
    pw.println("----- Get High Bid ----");
    pw.println( response.getEntity(String.class) );
  }

  public static void listBids(long id){
    Client client = Client.create(); // Add Jersey Client

    // Create Web Resource
    String url = "http://localhost:8080/RSInContainer/rest/auctions/" + id + "/listbids";
    WebResource resource = client.resource( url );

    // Add parameters
    ClientResponse response = resource.get(ClientResponse.class);
    pw.println("----- List Bids ----");
    pw.println( response.getEntity(String.class) );
  }

}
