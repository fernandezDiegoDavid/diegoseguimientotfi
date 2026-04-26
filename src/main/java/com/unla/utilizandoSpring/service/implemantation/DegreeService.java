package com.unla.utilizandoSpring.service.implemantation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.utilizandoSpring.dtos.DegreeDTO;
import com.unla.utilizandoSpring.entities.Degree;
import com.unla.utilizandoSpring.repositories.IDegreeRepository;
import com.unla.utilizandoSpring.service.IDegreeService;

/**
 * Implementación de la lógica de negocio para la gestión de títulos académicos
 * (Degrees).
 * <p>
 * Se encarga de la persistencia de datos y de realizar las transformaciones
 * necesarias entre el modelo de base de datos y los objetos de transferencia de
 * datos (DTO).
 * </p>
 */
@Service("degreeService")
public class DegreeService implements IDegreeService {

	// Declara el atributo que sera inyectado por Spring en tiempo de ejecucion
	private IDegreeRepository degreeRepository;

	private ModelMapper modelMapper;

	/**
	 * Constructor para la inyección de dependencias.
	 * 
	 * @param degreeRepository Repositorio inyectado mediante calificador
	 *                         específico.
	 * @param modelMApper      Herramienta para el mapeo automático de objetos.
	 */
	public DegreeService(@Qualifier("degreeRepository") IDegreeRepository degreeRepository, ModelMapper modelMApper) {
		this.degreeRepository = degreeRepository;
		this.modelMapper = modelMApper;
	}

	/**
	 * Recupera todos los registros de títulos como entidades persistentes.
	 * 
	 * @return Lista de objetos {@link Degree}.
	 */
	@Override
	public List<Degree> getAll() {
		return degreeRepository.findAll();

		/*
		 * List<Degree> degrees = degreeRepository.findAll(); return degrees.stream()
		 * .map(degree -> modelMapper.map(degree, DegreeModel.class))
		 * .collect(Collectors.toList());
		 */

	}

	/**
	 * Obtiene todos los títulos académicos convertidos a DTO.
	 * <p>
	 * Utiliza la API de {@link java.util.stream.Stream} para mapear la lista de
	 * entidades a una lista de {@link DegreeDTO} de forma declarativa.
	 * </p>
	 * 
	 * @return Lista de títulos en formato de transferencia.
	 */
	@Override
	public List<DegreeDTO> getallDTO() {
		// return degreeRepository.findAll();

		List<Degree> degrees = degreeRepository.findAll();

		return degrees.stream().map(degree -> modelMapper.map(degree, DegreeDTO.class)).collect(Collectors.toList());
		/*
		 * List<DegreeDTO> dtoList = new ArrayList<>(); for (Degree degree : degrees) {
		 * DegreeDTO dto = modelMapper.map(degree, DegreeDTO.class); dtoList.add(dto); }
		 * return dtoList;
		 */

	}

	/**
	 * Persiste un título académico a partir de su representación DTO.
	 * <p>
	 * El proceso incluye un doble mapeo: del DTO recibido a la Entidad para el
	 * {@code save}, y de la Entidad persistida nuevamente al DTO para el retorno.
	 * </p>
	 * 
	 * @param degreeModel Datos del título a insertar o actualizar.
	 * @return El objeto {@link DegreeDTO} resultante de la operación.
	 */
	@Override
	public DegreeDTO insertOrUpdate(DegreeDTO degreeModel) {
		Degree degree = degreeRepository.save(modelMapper.map(degreeModel, Degree.class));
		return modelMapper.map(degree, DegreeDTO.class);
	}

	/**
	 * Elimina un título académico por su identificador.
	 * 
	 * @param id ID del título a borrar.
	 * @return {@code true} si se completó con éxito, {@code false} ante cualquier
	 *         excepción.
	 */
	@Override
	public boolean remove(int id) {
		try {
			degreeRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
