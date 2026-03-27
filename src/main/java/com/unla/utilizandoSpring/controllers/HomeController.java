package com.unla.utilizandoSpring.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.utilizandoSpring.helpers.ViewRouteHelper;
import com.unla.utilizandoSpring.service.IPersonService;

/**
 * Controlador principal de la aplicación.
 * <p>
 * Gestiona las rutas base, la página de inicio (index) y ejemplos de pasaje de
 * parámetros a través de peticiones GET.
 * </p>
 * <p>
 * <strong>Nota técnica:</strong> Actúa como el punto de entrada post-autenticación. 
 * Utiliza el SecurityContext de Spring para recuperar los detalles del usuario 
 * actualmente logueado, demostrando la integración entre la capa de seguridad 
 * y la capa de presentación.
 * </p>
 */
@Controller
@RequestMapping("/")
public class HomeController {

	private IPersonService personService;

	/**
	 * Constructor para inyección de dependencias.
	 * 
	 * @param personService Servicio para gestionar la lógica de personas.
	 */
	public HomeController(IPersonService personService) {
		this.personService = personService;
	}

	/**
	 * Muestra la página principal (Index) de la aplicación.
	 * <p>
	 * Recupera el usuario autenticado desde el contexto de seguridad de Spring y
	 * carga el listado completo de personas para ser mostrado en la vista.
	 * </p>
	 * 
	 * @return {@link ModelAndView} configurado con la vista index, el nombre de
	 *         usuario y la lista de personas.
	 */
	// GET Example: SERVER/index
	@GetMapping("/index")
	public ModelAndView index() {

		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.INDEX);

		// Obtención del usuario principal desde el contexto de Spring Security
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		modelAndView.addObject("username", user.getUsername());
		modelAndView.addObject("persons", personService.getAll());

		return modelAndView;
	}

	// GET Example: SERVER/hello?name=someName
	/**
	 * Ejemplo de endpoint que recibe parámetros mediante Query Params
	 * (?nombre=valor).
	 * <p>
	 * El uso de {@link RequestParam} permite capturar datos opcionales de la URL.
	 * </p>
	 * 
	 * @param name Nombre recibido en la URL. Configurado como
	 *             {@code required = false} para permitir llamadas sin parámetros y
	 *             {@code defaultValue = "null"} para evitar valores nulos en la
	 *             lógica.
	 * @return {@link ModelAndView} que renderiza la vista de saludo.
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
	/**
	 * Ejemplo de endpoint que recibe parámetros mediante Path Variables
	 * (/hello/valor).
	 * <p>
	 * A diferencia de los Query Params, {@link PathVariable} extrae el valor
	 * directamente de un segmento dinámico definido en la ruta de la anotación
	 * {@link GetMapping}.
	 * </p>
	 * 
	 * @param nombre Valor extraído del segmento "{name}" de la URL.
	 * @return {@link ModelAndView} con el atributo 'nombre' cargado para la vista.
	 */
	@GetMapping("/hello/{name}")
	public ModelAndView helloparam2(@PathVariable("name") String nombre) {

		ModelAndView mV = new ModelAndView(ViewRouteHelper.HELLO);
		// el identificador entre comillas debe ser el mismo en utilizado en la vista
		// al utilizar la misma vista que el caso de uso anterior se llanam igual
		// el parametro pasado como argumento debe ser el mismo que el que se declaro en
		// la firma del metodo
		mV.addObject("nombre", nombre);

		return mV;
	}

	/**
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
