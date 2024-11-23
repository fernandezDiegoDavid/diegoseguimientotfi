package com.unla.utilizandoSpring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
	//GET Example: SERVER/index
	@GetMapping("/index")
	public String index() {
		return "home/index";
	}
	
	//GET Example: SERVER/hello?name=someName
	@GetMapping("/hello")
	public ModelAndView helloParams1(@RequestParam(name="nombre", required=false, defaultValue="null")String name) {
		ModelAndView mV= new ModelAndView("home/hello");
		mV.addObject("nombre",name);
		return mV;
	}

}
