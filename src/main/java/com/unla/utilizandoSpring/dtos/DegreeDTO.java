package com.unla.utilizandoSpring.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class DegreeDTO {
	
	private int id;
	
	private String name;

	@Size(min=3, max=30)
	private String institucion;

	@Min(3)
	private int year;

	public DegreeDTO() {
	}

	public DegreeDTO(String name, String institucion, int year) {

		this.name = name;
		this.institucion = institucion;
		this.year = year;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	

}
