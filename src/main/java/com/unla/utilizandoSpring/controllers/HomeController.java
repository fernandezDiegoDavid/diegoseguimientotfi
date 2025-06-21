package com.unla.utilizandoSpring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
	//GET Example: SERVER/index
	@GetMapping("")
	public String index() {
		return "home/index";
	}
	
	//GET Example: SERVER/hello?name=someName
	/*(@RequestParam(name="nombre", required=false, defaultValue="null")String name) : el nombre del primer parametro, name="nombre",
	 *  debe coincidir con el de la ruta ?nombre=someName 
	 *required=false: indica que no es requerido
	 * defaultValue="null": indica que puede ser nulo
	 * String name : dentro del metodo sera de tipo cadena y se llamara name puede identificarse con cualquier variable*/
	@GetMapping("/hello")
	public ModelAndView helloParams1(@RequestParam(name="nombre", required=false, defaultValue="null")String name) {
		
		ModelAndView mV= new ModelAndView("home/hello");
		//el identificador entre comillas sebe ser el mismo en utilizado en la vista
		mV.addObject("nombre",name);
		return mV;
	}
	
	//Get example : SERVER/hello/someName
	@GetMapping("/hello/{name}")
	public ModelAndView helloparam2(@PathVariable("name")String nombre) {
		
		ModelAndView mV = new ModelAndView("home/hello");
		//el identificador entre comillas sebe ser el mismo en utilizado en la vista
		//al utilizar la misma vista que el caso de uso anterior se llanam igual
		//el parametro pasado como argumento debe ser el mismo que el que se declaro en la firma del metodo
		mV.addObject("nombre",nombre);
		
		return mV;
	}

}
