package com.unla.utilizandoSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class UtilizandoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtilizandoSpringApplication.class, args);
	}

}
