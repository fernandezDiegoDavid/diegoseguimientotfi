package com.unla.utilizandoSpring.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.utilizandoSpring.entities.Person;
import java.util.List;

/**
 * Repositorio para la gestión de persistencia de la entidad {@link Person}.
 * <p>
 * Incluye métodos de búsqueda por atributos básicos y consultas complejas
 * relacionadas con los títulos académicos (Degrees).
 * </p>
 */
@Repository("personRepository")
public interface IPersonRepository extends JpaRepository<Person, Serializable> {

	/**
	 * Recupera una persona mediante su identificador único.
	 * 
	 * @param id El ID de la persona.
	 * @return Un {@link Optional} que contiene la persona si existe, o vacío en
	 *         caso contrario.
	 */
	public abstract Optional<Person> findById(int id);

	/**
	 * Busca una persona por su nombre exacto.
	 * 
	 * @param name Nombre completo a buscar.
	 * @return Un {@link Optional} con el resultado de la búsqueda.
	 */
	public abstract Optional<Person> findByName(String name);

	/**
	 * Obtiene una lista de personas que poseen un título académico específico.
	 * <p>
	 * Utiliza {@code JOIN FETCH} para cargar la colección de títulos (degrees) de
	 * cada persona en una sola consulta, optimizando el rendimiento y evitando el
	 * problema de N+1 consultas.
	 * </p>
	 * 
	 * @param name Nombre del título académico (ej: "Licenciatura").
	 * @return Lista de objetos {@link Person} que cumplen con el criterio.
	 */
	// Todas las personas que tengan un título con ese nombre (párametro name)
	@Query("SELECT p FROM Person p JOIN FETCH p.degrees d WHERE d.name = (:name)")
	public abstract List<Person> findByDegreeName(String name);
}
