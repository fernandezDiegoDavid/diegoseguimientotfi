package com.unla.utilizandoSpring.controllers;
// Define el paquete donde está esta clase. Es útil para organizar el código

import org.springframework.stereotype.Controller;
// Importa la anotación @Controller, que le indica a Spring que esta calase es un controlador web

import org.springframework.ui.Model;
// Model permite pasar datos desde el controlador a la vista 

import org.springframework.web.bind.annotation.GetMapping;
// Permite mapear solicitudes HTTP GET a métodos específicos del controlador

import org.springframework.web.bind.annotation.RequestParam;
// Se usa para obtener parámetros de la URL, como ?error=true

import com.unla.utilizandoSpring.helpers.ViewRouteHelper;
// Importa una clase auxiliar con constantes de rutas de vistas. Mejora la legivilidad y evita usar Strings

@Controller
// Indica que est clase es un controlador MVC. Spring la detecta y la usa para manejar peticiones web

public class UserController {

	@GetMapping("/login")
	// Mapea las solicitudes GET a la URL /login
	public String login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout) {
						// Toma parámetros que vienen en la URL (como ?error=true).
		model.addAttribute("error", error);
		// 	Agrega datos que podés mostrar en el HTML con ${...}.
		model.addAttribute("logout", logout);
		return ViewRouteHelper.USER_LOGIN;
	}

	@GetMapping("/logout")
	public String logout(Model model) {
		return ViewRouteHelper.USER_LOGOUT;
	}

	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/index";
		// 	Redirige a la ruta /index (y no a una vista).
	}
}