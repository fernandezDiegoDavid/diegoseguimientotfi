package com.unla.utilizandoSpring.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad que representa un título académico o grado (Degree).
 * 
 * Almacena información sobre estudios realizados, la institución otorgante y el
 * año de obtención. Se vincula con una {@link Person} mediante una relación de
 * muchos a uno.
 */
@Entity
@Table(name = "degree")
public class Degree {

	/** Identificador único del título académico. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** Nombre del título o carrera (ej: "Licenciatura en Sistemas"). */
	@Column(name = "name")
	private String name;

	/** Nombre de la institución o universidad que otorga el título. */
	@Column(name = "institution")
	private String institution;

	/** Año en el que se completó u obtuvo el grado. */
	@Column(name = "year")
	private int year;

	/**
	 * Persona a la que pertenece este título. La relación es opcional (nullable =
	 * true) y utiliza carga perezosa (LAZY).
	 */
	// muchos degree pueden ser de una persona
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id", nullable = true)
	private Person person;

	/** Fecha y hora de registro del título en el sistema. */
	@Column(name = "createdat", columnDefinition = "DATETIME")
	@CreationTimestamp
	private LocalDateTime createAt;

	/** Fecha y hora de la última modificación de los datos del título. */
	@Column(name = "updatedat", columnDefinition = "DATETIME")
	@UpdateTimestamp
	private LocalDateTime updateAt;

	/** Constructor por defecto requerido por JPA. */
	public Degree() {
	}

	/**
	 * Constructor para inicializar un título con ID (útil para actualizaciones).
	 * 
	 * @param id          Identificador único.
	 * @param name        Nombre del título.
	 * @param institution Entidad académica.
	 * @param year        Año de egreso.
	 */
	public Degree(int id, String name, String institution, int year) {
		this.id = id;
		this.name = name;
		this.institution = institution;
		this.year = year;
	}

	/**
	 * Constructor para la creación de nuevos títulos sin ID asignado.
	 * 
	 * @param name        Nombre del título.
	 * @param institution Entidad académica.
	 * @param year        Año de egreso.
	 */
	public Degree(String name, String institution, int year) {
		this.name = name;
		this.institution = institution;
		this.year = year;
	}

	// --- Getters y Setters ---

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

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

}
