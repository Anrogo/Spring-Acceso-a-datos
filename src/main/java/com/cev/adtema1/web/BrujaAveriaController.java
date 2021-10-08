package com.cev.adtema1.web;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrujaAveriaController {
	
	@GetMapping("/brujaAveria")
	//filtrado extra de peticiones. Hay que añadir: @EnableGlobalMethodSecurity(prePostEnabled = true) en el SecurityConfig para que funcione
	//@PreAuthorize("hasRole('ROLE_ADMIN')") 
	String dimeNavegador(@RequestHeader(name = "user-agent") String userAgent) {
		
		return "Estas navegando en: " + userAgent + " Y LO SABES";
	}
	
}
