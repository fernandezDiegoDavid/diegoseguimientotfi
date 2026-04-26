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

/**
 * Implementación de la lógica de negocio para la gestión de personas.
 * <p>
 * Esta clase actúa como intermediaria entre el repositorio
 * {@link IPersonRepository} y la capa de controladores, encargándose de la
 * persistencia y la conversión a objetos de transferencia (DTO).
 * </p>
 */
@Service("personService")
public class PersonService implements IPersonService {

	private IPersonRepository personRepository;

	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * Constructor para la inyección de dependencias.
	 * 
	 * @param personRepository Repositorio de personas.
	 * @param modelMapper      Herramienta para mapeo entre Entidades y DTOs.
	 */
	public PersonService(IPersonRepository personRepository, ModelMapper modelMapper) {
		this.personRepository = personRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<Person> getAll() {
		return personRepository.findAll();
	}

	/**
	 * Guarda una nueva persona o actualiza una existente.
	 * 
	 * @param person Entidad a persistir.
	 * @return La entidad guardada con sus datos actualizados (ej: ID generado).
	 */
	@Override
	public Person insertOrUpdate(Person person) {
		return personRepository.save(person);
	}

	/**
	 * Intenta eliminar una persona por su ID.
	 * 
	 * @param id Identificador de la persona.
	 * @return {@code true} si se eliminó correctamente, {@code false} si ocurrió un
	 *         error.
	 */
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
	public Optional<Person> findById(int id) throws Exception {
		return personRepository.findById(id);
	}

	/**
	 * Busca una persona por nombre y lanza una excepción si no la encuentra.
	 * <p>
	 * Se utiliza el método {@code orElseThrow} del {@link Optional} devuelto por el
	 * repositorio para manejar el flujo de error de forma declarativa.
	 * </p>
	 * 
	 * @param name Nombre a buscar.
	 * @return La instancia de {@link Person} hallada.
	 * @throws Exception Si no se encuentra ninguna coincidencia.
	 */
	@Override
	public Person findByName(String name) throws Exception {

		// Al usar un optional, indico al que use el metodo que el resultado puede ser
		// null
		return personRepository.findByName(name)
				.orElseThrow(() -> new Exception("ERROR no existe Persona con Nombre: " + name));
	}

	/**
	 * Busca personas por título y las convierte a DTO.
	 * <p>
	 * Utiliza la API de {@link java.util.stream.Stream} para transformar cada
	 * entidad en un {@link PersonDTO} mediante {@link ModelMapper}.
	 * </p>
	 * 
	 * @param degreeName Nombre del título académico.
	 * @return Lista de DTOs resultantes.
	 */
	@Override
	public List<PersonDTO> findByDegreeName(String degreeName) {
		return personRepository.findByDegreeName(degreeName).stream()
				.map(person -> modelMapper.map(person, PersonDTO.class)).collect(Collectors.toList());
	}

}
