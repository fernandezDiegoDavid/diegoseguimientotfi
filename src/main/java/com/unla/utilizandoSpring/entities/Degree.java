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

@Entity
@Table(name="degree")
public class Degree {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="institution")
	private String institution;
	
	@Column(name="year")
	private int year;
	
	// muchos degree pueden ser de una persona
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="person_id", nullable = true)
	private Person person;
	
	@Column(name="createdat", columnDefinition = "DATETIME")
	@CreationTimestamp
	private LocalDateTime createAt;
	
	@Column(name="updatedat", columnDefinition = "DATETIME")
	@UpdateTimestamp
	private LocalDateTime updateAt;
	
	public Degree() {}
	
	public Degree(int id, String name, String institution,int year) {
		this.id = id;
		this.name = name;
		this.institution = institution;
		this.year = year;
	}
	
	public Degree(String name, String institution, int year) {
		this.name = name;
		this.institution = institution;
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
