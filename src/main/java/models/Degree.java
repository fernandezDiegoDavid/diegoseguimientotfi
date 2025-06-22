package models;

public class Degree {

	private String name;

	private String institucion;

	private int year;

	public Degree() {
	}

	public Degree(String name, String institucion, int year) {

		this.name = name;
		this.institucion = institucion;
		this.year = year;
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
