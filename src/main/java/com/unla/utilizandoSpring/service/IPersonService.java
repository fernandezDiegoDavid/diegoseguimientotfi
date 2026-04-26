package com.unla.utilizandoSpring.service;

import java.util.List;
import java.util.Optional;

import com.unla.utilizandoSpring.dtos.PersonDTO;
import com.unla.utilizandoSpring.entities.Person;

/**
 * Interfaz de servicio para la gestión de personas.
 * <p>
 * Define las operaciones de negocio permitidas para la entidad {@link Person},
 * incluyendo la gestión de persistencia (CRUD) y búsquedas especializadas
 * mediante DTOs.
 * </p>
 */
public interface IPersonService {

	/**
	 * Recupera la lista completa de personas registradas en el sistema.
	 * 
	 * @return Una lista de objetos {@link Person}.
	 */
	public List<Person> getAll();

	/**
	 * Busca una persona por su identificador único.
	 * 
	 * @param id El identificador de la persona.
	 * @return Un {@link Optional} que contiene la persona si se encuentra.
	 * @throws Exception Si ocurre un error inesperado durante la búsqueda.
	 */
	public Optional<Person> findById(int id) throws Exception;

	/**
	 * Busca una persona específica por su nombre completo.
	 * 
	 * @param name El nombre a buscar.
	 * @return La instancia de {@link Person} hallada.
	 * @throws Exception Si el nombre no existe o hay un error en la consulta.
	 */
	public Person findByName(String name) throws Exception;

	/**
	 * Registra una nueva persona o actualiza una existente en el sistema.
	 * 
	 * @param person La entidad con los datos a persistir.
	 * @return La instancia de {@link Person} persistida (con ID asignado si es
	 *         nueva).
	 */
	public Person insertOrUpdate(Person person);

	/**
	 * Elimina una persona del sistema mediante su identificador.
	 * 
	 * @param id El ID de la persona a remover.
	 * @return {@code true} si la operación fue exitosa, {@code false} en caso
	 *         contrario.
	 */
	public Boolean remove(int id);

	/**
	 * Busca personas que posean un título académico específico y las devuelve
	 * transformadas en objetos de transferencia de datos (DTO).
	 * 
	 * @param degreeName Nombre del título a filtrar.
	 * @return Lista de {@link PersonDTO} que cumplen el criterio.
	 */
	public List<PersonDTO> findByDegreeName(String degreeName);

}
