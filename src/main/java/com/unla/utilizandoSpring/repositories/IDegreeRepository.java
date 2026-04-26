package com.unla.utilizandoSpring.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.utilizandoSpring.entities.Degree;

/**
 * Repositorio para la gestión de persistencia de la entidad {@link Degree}.
 * <p>
 * Proporciona métodos de búsqueda automática basados en convenciones de nombres
 * de Spring Data JPA para filtrar títulos por institución, año y nombre.
 * </p>
 */
@Repository("degreeRepository")
public interface IDegreeRepository extends JpaRepository<Degree, Serializable> {

	/**
	 * Busca un título académico por su nombre exacto.
	 * 
	 * @param name Nombre del título (ej: "Licenciatura en Sistemas").
	 * @return La instancia de {@link Degree} encontrada o {@code null} si no
	 *         existe.
	 */
	public abstract Degree findByName(String name);

	/**
	 * Busca un título que coincida exactamente con una institución y un año
	 * específicos.
	 * 
	 * @param institution Nombre de la entidad académica.
	 * @param year        Año de obtención del título.
	 * @return El objeto {@link Degree} coincidente.
	 */
	public abstract Degree findByInstitutionAndYear(String institution, int year);

	/**
	 * Recupera una lista de títulos de una misma institución y año, ordenados de
	 * forma descendente por el año de graduación.
	 * 
	 * @param institution Nombre de la institución.
	 * @param year        Año de referencia para la búsqueda.
	 * @return Lista de {@link Degree} ordenada por año (Desc).
	 */
	public abstract List<Degree> findByInstitutionAndYearOrderByYearDesc(String institution, int year);
}
