package com.unla.utilizandoSpring.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter @Setter @NoArgsConstructor
public class PersonDTO {
	
	private int id;
	
	private String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthdate;
	
	private Set<DegreeDTO> degrees = new HashSet<>();
	
	public PersonDTO(int id,String name, LocalDate birthdate) {
	
	this.setId(id);
	this.name = name;
	this.birthdate = birthdate;

	}

}
