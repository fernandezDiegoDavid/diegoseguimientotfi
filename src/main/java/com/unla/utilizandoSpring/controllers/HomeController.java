package com.unla.utilizandoSpring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.utilizandoSpring.dtos.DegreeDTO;
import com.unla.utilizandoSpring.helpers.ViewRouteHelper;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping("/degree")
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

	// GET Example: SERVER/index
	@GetMapping("/index")
	public String index() {
		return ViewRouteHelper.INDEX;
	}

	// GET Example: SERVER/hello?name=someName
	/*
	 * (@RequestParam(name="nombre", required=false, defaultValue="null")String
	 * name) : el nombre del primer parametro, name="nombre", debe coincidir con el
	 * de la ruta ?nombre=someName required=false: indica que no es requerido
	 * defaultValue="null": indica que puede ser nulo String name : dentro del
	 * metodo sera de tipo cadena y se llamara name puede identificarse con
	 * cualquier variable
	 */
	@GetMapping("/hello")
	public ModelAndView helloParams1(
			@RequestParam(name = "nombre", required = false, defaultValue = "null") String name) {

		ModelAndView mV = new ModelAndView(ViewRouteHelper.HELLO);
		// el identificador entre comillas debe ser el mismo en utilizado en la vista
		mV.addObject("nombre", name);
		return mV;
	}

	// Get example : SERVER/hello/someName
	@GetMapping("/hello/{name}")
	public ModelAndView helloparam2(@PathVariable("name") String nombre) {

		ModelAndView mV = new ModelAndView(ViewRouteHelper.HELLO);
		// el identificador entre comillas sebe ser el mismo en utilizado en la vista
		// al utilizar la misma vista que el caso de uso anterior se llanam igual
		// el parametro pasado como argumento debe ser el mismo que el que se declaro en
		// la firma del metodo
		mV.addObject("nombre", nombre);

		return mV;
	}

	/*
	 * Una peticicón que en verdad es una redirección: Se puede usar una redirección
	 * en caso que una vista no contenga toda la información que necesita para
	 * devolver un recurso. Se devuelve una instancia de RedirectView, ya que esta
	 * clase se usa para realizar la redirección manteniendo la petición Get
	 * original De esta manera se redirecciona a la ruta localhost:8080/index, la
	 * cual corresponde al primer metodo del controlador
	 */
	@GetMapping("/")
	public RedirectView redirectToHomeIndex() {

		return new RedirectView(ViewRouteHelper.ROUTE_INDEX);
	}

}
