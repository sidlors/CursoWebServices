package clients;

import java.io.PrintWriter;
import java.util.Map;
import javax.xml.ws.BindingProvider;

public class ItemManagerEJBClient {
  private static PrintWriter pw = new PrintWriter(System.out, true);

  public static void main(String[] args){

    ItemManagerEJBClient.addItem("Guitar");
    ItemManagerEJBClient.addItem("Drums");

    /* Find an Item and Print it Out */
    //labs.Item foundItem = new labs.Item();
    //foundItem = ItemManagerEJBClient.findItem(1);
    //System.out.println("Found: " + foundItem.getDescription());

    /* Update an Item */
    //ItemManagerEJBClient.updateItem(foundItem, "Matisse Painting");
    //foundItem = ItemManagerEJBClient.findItem(2);
    //System.out.println("Found: " + foundItem.getDescription());

  }

  public static void addItem(String description){

    try { // Call Web Service Operation
      labs.ItemManagerWS service = new labs.ItemManagerWS();
      labs.ItemManager port = service.getItemManagerPort();

      Map<String,Object> reqCtx =
          ((BindingProvider)port).getRequestContext();
      reqCtx.put(BindingProvider.USERNAME_PROPERTY, "tracy");
      reqCtx.put(BindingProvider.PASSWORD_PROPERTY,"password");


      // TODO process result here
      labs.Item item = port.addItem(description);
      pw.println("Item --- id: " + item.getId() + " Desc: " + item.getDescription());
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  public static labs.Item findItem(long itemId){
   labs.Item result = new labs.Item();

    try { // Call Web Service Operation
      labs.ItemManagerWS service = new labs.ItemManagerWS();
      labs.ItemManager port = service.getItemManagerPort();

      Map<String,Object> reqCtx =
          ((BindingProvider)port).getRequestContext();
      reqCtx.put(BindingProvider.USERNAME_PROPERTY, "tracy");
      reqCtx.put(BindingProvider.PASSWORD_PROPERTY,"password");


      // TODO process result here
      result = port.findItem(itemId);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return result;

  }

  public static void updateItem(labs.Item targetItem, String newDescription){

    try { // Call Web Service Operation
      labs.ItemManagerWS service = new labs.ItemManagerWS();
      labs.ItemManager port = service.getItemManagerPort();

      Map<String,Object> reqCtx =
          ((BindingProvider)port).getRequestContext();
      reqCtx.put(BindingProvider.USERNAME_PROPERTY, "tracy");
      reqCtx.put(BindingProvider.PASSWORD_PROPERTY,"password");

      // TODO initialize WS operation arguments here
      port.updateItem(targetItem, newDescription);
    } catch (Exception ex) {
      ex.printStackTrace();
    }


  }

}
