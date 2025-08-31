package com.unla.utilizandoSpring.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.utilizandoSpring.dtos.PersonDTO;
import com.unla.utilizandoSpring.entities.Person;
import com.unla.utilizandoSpring.helpers.ViewRouteHelper;
import com.unla.utilizandoSpring.service.IPersonService;


@Controller
@RequestMapping("/person")
public class PersonController {
	
	private IPersonService personService;
	
	private ModelMapper modelMapper;
	
	public PersonController(IPersonService personService, ModelMapper modelMapper) {
		this.personService = personService;
		this.modelMapper = modelMapper;
	}
	
 	@GetMapping("")  // ya lo use
	public ModelAndView index() {
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERSON_INDEX);
		mAV.addObject("persons", personService.getAll());
		return mAV;
	}
	
	@GetMapping("/new") //ya lo use
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERSON_NEW);
		mAV.addObject("person",new PersonDTO());
		return mAV;
	}
	
	@PostMapping("/create") // ya lo use 
	public RedirectView create(@ModelAttribute("person") PersonDTO personDTO) {
		personService.insertOrUpdate(modelMapper.map(personDTO, Person.class));
		return new RedirectView(ViewRouteHelper.PERSON_ROOT);
	}
	
	@GetMapping("/{id}") //ya lo use 
	public ModelAndView get(@PathVariable("id") int id) throws Exception{
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERSON_UPDATE);
		PersonDTO personDTO = modelMapper.map(personService.findById(id).get(),PersonDTO.class );
		mAV.addObject("person", personDTO);
		return mAV;
	}
	
	@GetMapping("/partial/{id}") // anda
	public ModelAndView getPartial(@PathVariable("id") int id) throws Exception{
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERSON_PARTIAL_VIEW);
		// En caso de devolver null el optional arrojara una exeption en esta capa se deberia manejar 
		PersonDTO personDTO = modelMapper.map(personService.findById(id).get(),PersonDTO.class );
		mAV.addObject("person", personDTO);
		return mAV;
	}
	
	@GetMapping("/by_name/{name}") // ya lo use 
	public ModelAndView getByName(@PathVariable("name") String name) throws Exception{
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERSON_UPDATE);
		//Puede devolcer una exeption en caso de que la busqueda de null
		PersonDTO personDTO = modelMapper.map(personService.findByName(name),PersonDTO.class);
		mAV.addObject("person", personDTO);
		return mAV;
	}
	
	@GetMapping("/by_degree/{degree_name}") // anda
	public ModelAndView getByDegreeName(@PathVariable("degree_name") String degreName) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PERSON_INDEX);
		mAV.addObject("persons",personService.findByDegreeName(degreName));
		return mAV;
	}
	
	@PostMapping("/update") // anda
	public RedirectView update(@ModelAttribute("person") PersonDTO personDTO) throws Exception{
		Person personToUpdate = modelMapper.map(personService.findById(personDTO.getId()).get(), Person.class);
		if (personToUpdate != null ) {
			personToUpdate.setName(personDTO.getName());
			personService.insertOrUpdate(personToUpdate);
		}
		return new RedirectView(ViewRouteHelper.PERSON_ROOT);
	}
	
	@PostMapping("delete/{id}") //anda
	public RedirectView delete(@PathVariable("id")int id) {
		personService.remove(id);
		return new RedirectView(ViewRouteHelper.PERSON_ROOT);
	}
	
	

}
