package mx.com.certificatic.practica1.client;

import javax.xml.ws.WebServiceRef;

import mx.com.certificatic.practica1.service.SayHello;
import mx.com.certificatic.practica1.service.SayHelloService;

public class HelloClient {
	
	

	public static void main(String[] args) {

		try {
			SayHelloService client = new SayHelloService();
			SayHello port = client.getSayHelloPort();
			String name;
			if (args.length > 0) {
				name = args[0];
			} else {
				name = "Anonimo";
			}
			String response = port.sayHello(name);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}