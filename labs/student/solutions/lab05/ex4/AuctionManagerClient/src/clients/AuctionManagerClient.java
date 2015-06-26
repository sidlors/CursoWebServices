package clients;

import java.io.PrintWriter;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class AuctionManagerClient {
  private static PrintWriter pw = new PrintWriter(System.out, true);

  public static void main(String[] args){
    AuctionManagerClient.createAuction(3, 1, 7, 100.00);

    // Auctions
    //AuctionManagerClient.findAuction(5);
    //AuctionManagerClient.listAuctions();

    // Bids
    //AuctionManagerClient.placeBid(3, 5, 1200.00);
    //AuctionManagerClient.placeBid(3, 5, 1300.00);
    //AuctionManagerClient.placeBid(3, 5, 1400.00);

    //AuctionManagerClient.getAllBids(5);
    //AuctionManagerClient.getHighBid(5);
    
  }

  public static void createAuction(long userId, long itemId, int nDays,
                                double startPrice ){

    try { // Call Web Service Operation
      labs.AuctionManagerService service = new labs.AuctionManagerService();
      labs.AuctionManager port = service.getAuctionManagerPort();

      Map<String,Object> reqCtx =
          ((BindingProvider)port).getRequestContext();
      reqCtx.put(BindingProvider.USERNAME_PROPERTY, "tracy");
      reqCtx.put(BindingProvider.PASSWORD_PROPERTY,"password");

      // TODO process result here
      labs.Auction auction = port.createAuction(userId, itemId, nDays, startPrice);
      pw.println("Auction --- Id: " + auction.getId() + " Price:" + auction.getStartPrice());
      
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  public static void findAuction(long id){

    try { // Call Web Service Operation
      labs.AuctionManagerService service = new labs.AuctionManagerService();
      labs.AuctionManager port = service.getAuctionManagerPort();

      Map<String,Object> reqCtx =
          ((BindingProvider)port).getRequestContext();
      reqCtx.put(BindingProvider.USERNAME_PROPERTY, "tracy");
      reqCtx.put(BindingProvider.PASSWORD_PROPERTY,"password");

      // TODO process result here
      labs.Auction auction = port.findAuction(id);
      pw.println("Auction --- Id: " + auction.getId()+ " Start: " + auction.getStartPrice());
      
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  public static void listAuctions(){

    try { // Call Web Service Operation
      labs.AuctionManagerService service = new labs.AuctionManagerService();
      labs.AuctionManager port = service.getAuctionManagerPort();

      Map<String,Object> reqCtx =
          ((BindingProvider)port).getRequestContext();
      reqCtx.put(BindingProvider.USERNAME_PROPERTY, "tracy");
      reqCtx.put(BindingProvider.PASSWORD_PROPERTY,"password");

      // TODO process result here
      java.lang.String result = port.listAuctions();
      System.out.println(result);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  public static void placeBid(long userId, long auctionId, double bidAmount){

    try { // Call Web Service Operation
      labs.AuctionManagerService service = new labs.AuctionManagerService();
      labs.AuctionManager port = service.getAuctionManagerPort();

      Map<String,Object> reqCtx =
          ((BindingProvider)port).getRequestContext();
      reqCtx.put(BindingProvider.USERNAME_PROPERTY, "tracy");
      reqCtx.put(BindingProvider.PASSWORD_PROPERTY,"password");

      // TODO process result here
      labs.Bid bid = port.placeBid(userId, auctionId, bidAmount);
      pw.println("Bid --- Id:" + bid.getId() + " Value:" + bid.getCurrentValue());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void getAllBids(long auctionId){

    try { // Call Web Service Operation
      labs.AuctionManagerService service = new labs.AuctionManagerService();
      labs.AuctionManager port = service.getAuctionManagerPort();

      Map<String,Object> reqCtx =
          ((BindingProvider)port).getRequestContext();
      reqCtx.put(BindingProvider.USERNAME_PROPERTY, "tracy");
      reqCtx.put(BindingProvider.PASSWORD_PROPERTY,"password");

      // TODO process result here
      java.lang.String result = port.listBids(auctionId);
      System.out.println(result);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  public static void getHighBid(long auctionId){

    try { // Call Web Service Operation
      labs.AuctionManagerService service = new labs.AuctionManagerService();
      labs.AuctionManager port = service.getAuctionManagerPort();

      Map<String,Object> reqCtx =
          ((BindingProvider)port).getRequestContext();
      reqCtx.put(BindingProvider.USERNAME_PROPERTY, "tracy");
      reqCtx.put(BindingProvider.PASSWORD_PROPERTY,"password");

      // TODO process result here
      labs.Bid bid = port.getHighBid(auctionId);
      pw.println("Bid --- Id:" + bid.getId() + " Value:" + bid.getCurrentValue());
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }
}
