package com.unla.utilizandoSpring.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad base que representa a una Persona física en el sistema.
 * 
 * Utiliza una estrategia de herencia {@link InheritanceType#JOINED}, lo que
 * significa que cada subclase tendrá su propia tabla vinculada por una clave
 * foránea al ID de esta tabla.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

	/** Identificador único de la persona. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** Nombre completo de la persona. */
	private String name;

	/** Fecha de nacimiento para cálculos de edad y validaciones. */
	private LocalDate birthdate;

	/** Fecha de creación del registro (Auditoría). */
	@CreationTimestamp
	private LocalDate createdAt;

	/** Fecha de la última actualización del registro (Auditoría). */
	@UpdateTimestamp
	private LocalDate updatedAt;

	/**
	 * Conjunto de títulos o grados académicos asociados a la persona. Relación uno
	 * a muchos con carga perezosa (LAZY).
	 */
	// una persona puede tener muchos degree
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	private Set<Degree> degrees = new HashSet<>();

	/**
	 * Constructor para inicializar una persona con datos completos.
	 * 
	 * @param id        Identificador único.
	 * @param name      Nombre completo.
	 * @param birthdate Fecha de nacimiento.
	 */
	public Person(int id, String name, LocalDate birthdate) {
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}

	/**
	 * Constructor simplificado para búsquedas o registros rápidos.
	 * 
	 * @param name Nombre de la persona.
	 */
	public Person(String name) {
		this.name = name;
	}

}
