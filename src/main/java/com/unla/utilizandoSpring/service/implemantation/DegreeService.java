package com.unla.utilizandoSpring.service.implemantation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.utilizandoSpring.dtos.DegreeDTO;
import com.unla.utilizandoSpring.entities.Degree;
import com.unla.utilizandoSpring.repositories.IDegreeRepository;
import com.unla.utilizandoSpring.service.IDegreeService;

@Service("degreeService")
public class DegreeService implements IDegreeService {

	// Declara el atributo que sera inyectado por Spring en tiempo de ejecucion
	private IDegreeRepository degreeRepository;
	
	private ModelMapper modelMapper;
	
	// // Inyecta una implemantacion del bean llamado degreeRepository que impleneta la interfaz IDegreeRepository
	public DegreeService(@Qualifier("degreeRepository") IDegreeRepository degreeRepository,ModelMapper modelMApper) {
		this.degreeRepository = degreeRepository;
		this.modelMapper = modelMApper;
	}
	
	@Override
	public List<Degree> getAll(){
		return degreeRepository.findAll();
		
		 /* List<Degree> degrees = degreeRepository.findAll();
		    return degrees.stream()
		                  .map(degree -> modelMapper.map(degree, DegreeModel.class))
		                  .collect(Collectors.toList());
		  */
		
	}
	
	@Override
	public DegreeDTO insertOrUpdate(DegreeDTO degreeModel) {
		Degree degree = degreeRepository.save(modelMapper.map(degreeModel, Degree.class));
		return modelMapper.map(degree, DegreeDTO.class);
	}
	
	@Override
	public boolean remove(int id) {
		try {
			degreeRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
}
