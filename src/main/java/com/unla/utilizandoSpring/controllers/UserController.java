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

/**
 * Controlador encargado de gestionar el flujo de autenticación de usuarios.
 * <p>
 * Proporciona los endpoints para visualizar el formulario de login, procesar el
 * cierre de sesión y redirigir tras un inicio de sesión exitoso.
 * </p>
 */
@Controller
// Indica que esta clase es un controlador MVC. Spring la detecta y la usa para manejar peticiones web

public class UserController {

	/**
	 * Muestra el formulario de inicio de sesión personalizado.
	 * <p>
	 * Este método captura parámetros opcionales enviados automáticamente por Spring
	 * Security en caso de falla o cierre de sesión previo.
	 * </p>
	 * 
	 * @param model  Objeto para pasar atributos a la vista.
	 * @param error  Parámetro {@link RequestParam} presente si las credenciales
	 *               fueron inválidas.
	 * @param logout Parámetro {@link RequestParam} presente si el usuario cerró
	 *               sesión exitosamente.
	 * @return El nombre de la vista de login definido en
	 *         {@link ViewRouteHelper#USER_LOGIN}.
	 */
	@GetMapping("/login")
	// Mapea las solicitudes GET a la URL /login
	public String login(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		// Toma parámetros que vienen en la URL (como ?error=true).
		model.addAttribute("error", error);
		// Agrega datos que podés mostrar en el HTML con ${...}.
		model.addAttribute("logout", logout);
		return ViewRouteHelper.USER_LOGIN;
	}

	/**
	 * Renderiza la vista de confirmación tras el cierre de sesión.
	 * 
	 * @param model Objeto para el pasaje de datos a la vista.
	 * @return La vista configurada en {@link ViewRouteHelper#USER_LOGOUT}.
	 */
	@GetMapping("/logout")
	public String logout(Model model) {
		return ViewRouteHelper.USER_LOGOUT;
	}

	/**
	 * Endpoint de transición tras una autenticación exitosa.
	 * <p>
	 * Utiliza el prefijo {@code redirect:} para forzar una redirección del
	 * navegador hacia la ruta principal, evitando problemas de reenvío de
	 * formularios.
	 * </p>
	 * 
	 * @return Una instrucción de redirección a la ruta "/index".
	 */
	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/index";
		// Redirige a la ruta /index (y no a una vista).
	}
}