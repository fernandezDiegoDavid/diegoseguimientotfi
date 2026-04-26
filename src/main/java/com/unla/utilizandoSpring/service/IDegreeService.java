package com.unla.utilizandoSpring.service;

import java.util.List;

import com.unla.utilizandoSpring.dtos.DegreeDTO;
import com.unla.utilizandoSpring.entities.Degree;

/**
 * Interfaz de servicio para la gestión de títulos académicos (Degrees).
 * <p>
 * Proporciona los métodos de negocio necesarios para administrar la información
 * de los grados, facilitando la conversión entre entidades persistentes
 * {@link Degree} y objetos de transferencia de datos {@link DegreeDTO}.
 * </p>
 */
public interface IDegreeService {

	/**
	 * Obtiene la lista completa de títulos académicos como entidades de
	 * persistencia.
	 * 
	 * @return Una lista de objetos {@link Degree}.
	 */
	public List<Degree> getAll();

	/**
	 * Recupera todos los títulos registrados en formato de transferencia (DTO).
	 * <p>
	 * Este método es ideal para ser utilizado en la capa de controladores, ya que
	 * desacopla la lógica de negocio del modelo de datos.
	 * </p>
	 * 
	 * @return Una lista de {@link DegreeDTO}.
	 */
	public List<DegreeDTO> getallDTO();

	/**
	 * Crea un nuevo título o actualiza uno existente a partir de un modelo DTO.
	 * 
	 * @param degreeModel El objeto DTO con los datos a procesar.
	 * @return El {@link DegreeDTO} persistido y actualizado (incluyendo ID si es
	 *         nuevo).
	 */
	public DegreeDTO insertOrUpdate(DegreeDTO degreeModel);

	/**
	 * Elimina un registro de título académico mediante su identificador único.
	 * 
	 * @param id El identificador del título a eliminar.
	 * @return {@code true} si la eliminación fue exitosa, {@code false} en caso
	 *         contrario.
	 */
	public boolean remove(int id);

}
