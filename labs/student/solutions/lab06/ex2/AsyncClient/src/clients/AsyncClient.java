package clients;

public class AsyncClient {

  public static void main(String[] args){

    try { // Call Web Service Operation(async. polling)
      labs.AuctionManagerService service = new labs.AuctionManagerService();
      labs.AuctionManager port = service.getAuctionManagerPort();
      // TODO initialize WS operation arguments here
      long arg0 = 8L;
      // TODO process asynchronous response here
      javax.xml.ws.Response<labs.GetHighBidResponse> resp = port.getHighBidAsync(arg0);
      while(!resp.isDone()) {
        // do something
        System.out.println("Doing .... Somethin...");
        Thread.sleep(100);
      }
      System.out.println("Result = "+resp.get().getReturn().getMaxValue());
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }
}
