
package labs;

import javax.xml.ws.Endpoint;

public class ItemManagerRunner {

  public static void main(String[] args){
    ItemManager service = new ItemManager();
    Endpoint endpoint = Endpoint.publish("http://localhost:8081/itemManager",service);
  }

}
