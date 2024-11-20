package com.unla.utilizandoSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class UtilizandoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtilizandoSpringApplication.class, args);
	}

}
