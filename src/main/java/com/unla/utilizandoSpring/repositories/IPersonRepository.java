package com.unla.utilizandoSpring.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.utilizandoSpring.entities.Person;
import java.util.List;


@Repository("personRepository")
public interface IPersonRepository extends JpaRepository<Person, Serializable> {

	public abstract Optional<Person> findById(int id);
	
	public abstract Optional<Person> findByName(String name);
	
	// Todas las personas que tenagan un título con ese nombre (párametro name)
	@Query("SELECT p FROM Person p JOIN FETCH p.degrees d WHERE d.name = (:name)")
	public abstract List<Person> findByDegreeName(String name);
}
