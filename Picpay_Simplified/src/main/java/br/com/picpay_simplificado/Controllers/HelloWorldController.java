package br.com.picpay_simplificado.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping
	@RequestMapping("/hello")
	public String helloWorld() {
		return "Hello world endpoint";
	}

}
