package com.unla.utilizandoSpring.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

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

@Entity
@Getter @Setter @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private LocalDate birthdate;
	
	@CreationTimestamp
	private LocalDate createdAt;
	
	@CreationTimestamp
	private LocalDate updatedAt;
	
	// una persona puede tener muchos degree
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	private Set<Degree> degrees = new HashSet<>();
	
	public Person(int id, String name, LocalDate birthdate) {
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}
	
	public Person(String name) {
		this.name = name;
	}

}
