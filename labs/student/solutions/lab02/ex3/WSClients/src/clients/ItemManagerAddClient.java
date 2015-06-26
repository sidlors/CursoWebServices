package clients;

public class ItemManagerAddClient {
  public static void main(String[] args){

    try { // Call Web Service Operation
      labs.ItemManagerService service = new labs.ItemManagerService();
      labs.ItemManager port = service.getItemManagerPort();
      // TODO initialize WS operation arguments here
      java.lang.String arg0 = "Monet Painting";
      // TODO process result here
      long result = port.addItem(arg0);
      System.out.println("Result = "+result);
    } catch (Exception ex) {
      // TODO handle custom exceptions here
    }

  }
}
