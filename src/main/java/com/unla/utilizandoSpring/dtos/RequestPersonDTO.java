package com.unla.utilizandoSpring.dtos;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class RequestPersonDTO {

	  private String name;

	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private LocalDate birthdate;

	    public RequestPersonDTO(String name, LocalDate birthdate) {
	        this.name = name;
	        this.birthdate = birthdate;
	    }
}
