package com.unla.utilizandoSpring.service;

import java.util.List;
import java.util.Optional;

import com.unla.utilizandoSpring.dtos.PersonDTO;
import com.unla.utilizandoSpring.entities.Person;

public interface IPersonService {
	
	public List<Person> getAll();
	
	public Optional<Person> findById(int id) throws Exception;
	
	public Person findByName(String name) throws Exception;
	
	public Person insertOrUpdate(Person person);
	
	public Boolean remove(int id);
	
	public List<PersonDTO> findByDegreeName(String degreeName);

}
