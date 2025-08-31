package com.unla.utilizandoSpring.service.implemantation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.utilizandoSpring.dtos.PersonDTO;
import com.unla.utilizandoSpring.entities.Person;
import com.unla.utilizandoSpring.repositories.IPersonRepository;
import com.unla.utilizandoSpring.service.IPersonService;

@Service("personService")
public class PersonService implements IPersonService{

	private IPersonRepository personRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public PersonService(IPersonRepository personRepository, ModelMapper modelMapper) {
		this.personRepository = personRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public List<Person> getAll(){
		return personRepository.findAll();
	}
	
	@Override
	public Person insertOrUpdate(Person person) {
		return personRepository.save(person);
	}
	
	@Override
	public Boolean remove(int id) {
		try {
			personRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Optional<Person> findById(int id) throws Exception{
		return personRepository.findById(id);
	}
	
	@Override
	public Person findByName(String name) throws Exception {
		
		// Al usar un optional, indico al que use el metodo que el resultado puede ser null
		return personRepository.findByName(name).orElseThrow(
				() -> new Exception("ERROR no existe Persona con Nombre: " + name)
		);
	}

	@Override
	public List<PersonDTO> findByDegreeName(String degreeName) {
		return personRepository.findByDegreeName(degreeName)
				.stream()
				.map(person -> modelMapper.map(person, PersonDTO.class))
				.collect(Collectors.toList());
	}
	
	
}
