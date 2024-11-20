package com.unla.utilizandoSpring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//@Controller:usado para especificar que la clase es un componente Controller
@Controller
/*@RequestMapping("/firstspring"):usado para especificar la ruta por la cual se va a acceder a 
 * los metodos, por intermedio de request.
 *  Normalmente la ruta se llama como el controller,sacando el sufijo*/
@RequestMapping("/firstspring")
public class FirstSpringController {
/*especifica que se accede al método por intermedio
 * de una peticion get ademas la notacion agrega un nombre a la ruta
 * para especificar como debe ser esta
 */
	@GetMapping("/helloworld")
	public String helloworld() {
		/*el nombre del string es el nombre de la vista a la cual
		 * se va a traer por intermedio del Controller.
		 */
		return "HelloWorld";
	}
	
}
