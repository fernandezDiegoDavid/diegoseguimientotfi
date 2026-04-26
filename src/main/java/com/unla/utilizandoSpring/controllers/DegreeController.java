package com.unla.utilizandoSpring.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.utilizandoSpring.dtos.DegreeDTO;
import com.unla.utilizandoSpring.helpers.ViewRouteHelper;
import com.unla.utilizandoSpring.service.IDegreeService;

import jakarta.validation.Valid;

//@Controller:usado para especificar que la clase es un componente Controller

@Controller

/*
 * @RequestMapping("/degree"):usado para especificar la ruta por la cual se va a
 * acceder a los metodos, por intermedio de la peticion. Normalmente la ruta se
 * llama como el controller,sacando el sufijo
 */

@PreAuthorize("hasRole('ROLE_ADMIN')") // para acceder a este controlador se debe poseer el rol admin
@RequestMapping("/degrees")
public class DegreeController {

	// Declara el atributo que sera inyectado por Spring en tiempo de ejecucion
	
	private IDegreeService degreeService;

	// Inyecta una implemantacion del bean llamado degreeService que impleneta la
	// interfaz IDegreeService
	
	public DegreeController(@Qualifier("degreeService") IDegreeService degreeService) {
		this.degreeService = degreeService;

	}
	
	/* Especifica que se accede al método por intermedio
	 * de una peticion get ademas la notacion agrega un nombre a la ruta
	 * para especificar como debe ser esta
	 */
	@GetMapping("")
	public ModelAndView index() {
		
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.DEGREE_INDEX);
		
		mAV.addObject("degrees", degreeService.getAll());
		
		mAV.addObject("degree", new DegreeDTO());
		
		return mAV;
		
	}
	

	
	@PostMapping("")
	public RedirectView create(@ModelAttribute("degree") DegreeDTO degreeDTO ) {
	// El binding @ModelAttribute es lo que convierte automáticamente los valores del formulario en un objeto Java listo para usar.
		
	degreeService.insertOrUpdate(degreeDTO);
	
	return new RedirectView(ViewRouteHelper.ROOT);
	}
	
	@GetMapping("/form")
	public String degree(Model model) {
		//Model model, sirve para pasar datos del controlador a la vista
		model.addAttribute("degree", new DegreeDTO());
		return ViewRouteHelper.DEGREE;
	}

	@PostMapping("/newdegree")
	public ModelAndView newdegree(@Valid @ModelAttribute("degree") DegreeDTO degree, BindingResult bindingResult) {
		
		ModelAndView mV = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			mV.setViewName(ViewRouteHelper.DEGREE);
		} else {
			mV.setViewName(ViewRouteHelper.NEWDEGREE);
		}
		
		mV.addObject("degree", degree);
		
		return mV;
	}

}
