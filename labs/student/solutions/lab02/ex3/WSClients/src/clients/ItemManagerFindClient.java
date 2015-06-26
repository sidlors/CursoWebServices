package clients;

public class ItemManagerFindClient {
  public static void main(String[] args){

    try { // Call Web Service Operation
      labs.ItemManagerService service = new labs.ItemManagerService();
      labs.ItemManager port = service.getItemManagerPort();
      // TODO initialize WS operation arguments here
      long arg0 = 1L;
      // TODO process result here
      labs.Item result = port.findItem(arg0);
      System.out.println("ID = " + result.getId() + " Description = " + result.getDescription());
    } catch (Exception ex) {
      // TODO handle custom exceptions here
    }

  }
}
