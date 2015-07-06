package mx.com.certificatic.practica1.client;

import javax.xml.ws.WebServiceRef;

import mx.com.certificatic.practica1.service.SayHello;
import mx.com.certificatic.practica1.service.SayHelloService;

public class HelloClient {
	@WebServiceRef(wsdlLocation = "http://localhost:8080/helloservice/hello?wsdl")
	static SayHelloService service;

	public static void main(String[] args) {

		try {
			HelloClient client = new HelloClient();
			client.doTest(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doTest(String[] args) {
		try {
			System.out
					.println("Retrieving the port from the following service: "
							+ service);
			SayHello port = service.getSayHelloPort();
			System.out.println("Invoking the sayHello operation on the port.");
			String name;
			if (args.length > 0) {
				name = args[0];
			} else {
				name = "No Name";
			}
			String response = port.sayHello(name);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}