package mx.com.certificatic.practica1.service;

import javax.jws.WebService;


@WebService
public class SayHello {

	public String sayHello(String name){
		
		return "Hola "+name;
	}
	
}
