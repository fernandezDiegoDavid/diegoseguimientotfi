package com.unla.utilizandoSpring.configuration;

import com.unla.utilizandoSpring.dtos.DegreeDTO;
import com.unla.utilizandoSpring.entities.Degree;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	ModelMapper modelMapper() {

		ModelMapper mapper = new ModelMapper();

		// DTO --> Entity
		PropertyMap<DegreeDTO, Degree> dtoToEntityMAp = new PropertyMap<>() {
			@Override
			protected void configure() {
				map().setInstitution(source.getInstitucion());
			}
		};

		// Entity to DTO
		PropertyMap<Degree, DegreeDTO> entityToDtoMap = new PropertyMap<>() {
			@Override
			protected void configure() {
				map().setInstitucion(source.getInstitution());
			}
		};

		mapper.addMappings(dtoToEntityMAp);
		mapper.addMappings(entityToDtoMap);

		return mapper;

	}

}
