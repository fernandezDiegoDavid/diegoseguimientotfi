package com.unla.utilizandoSpring.service;

import java.util.List;

import com.unla.utilizandoSpring.dtos.DegreeDTO;
import com.unla.utilizandoSpring.entities.Degree;

public interface IDegreeService {
	
	public List<Degree> getAll();
	
	public List<DegreeDTO> getallDTO();
	
	public DegreeDTO insertOrUpdate(DegreeDTO degreeModel);
	
	public boolean remove(int id);

}
