package com.unla.utilizandoSpring.controllers.api.v1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.utilizandoSpring.dtos.DegreeDTO;
import com.unla.utilizandoSpring.service.IDegreeService;
import com.unla.utilizandoSpring.service.implemantation.DegreeService;

@RestController
/*
 * Es una combinación de:
 * 
 * @Controller → le dice a Spring que esta clase maneja peticiones web.
 * 
 * @ResponseBody → indica que el retorno del método se convierte directamente en
 * JSON (o XML, pero JSON es lo normal).
 * 
 * Es lo que convierte tu clase en un controlador REST.
 */

@RequestMapping("/api/v1/degree")
/*
 * Define la ruta base para todos los endpoints de esta clase. Significa que
 * cualquier método aquí tendrá URLs que empiezan por /api/v1/degree. Ejemplo:
 * si luego un método se mapea con /all, la ruta final será:
 * 
 */

public class DegreeRestController {
	
	// Declara el atributo que sera inyectado por Spring en tiempo de ejecucion
	
		private IDegreeService degreeService;

		// Inyecta una implemantacion del bean llamado degreeService que impleneta la
		// interfaz IDegreeService
		
		public DegreeRestController(@Qualifier("degreeService") IDegreeService degreeService) {
			this.degreeService = degreeService;

		}

	@GetMapping("/all")
	public ResponseEntity<List<DegreeDTO>> allDegrees() {
		List<DegreeDTO> degrees = new ArrayList<>();
		degrees.add(new DegreeDTO(1, "Lic. Sistemas", "UNLa", 2025));
		degrees.add(new DegreeDTO(2, "Lic. Turismo", "UNLa", 2025));
		return new ResponseEntity<>(degrees, HttpStatus.OK);
		//return new ResponseEntity<>(degreeService.getallDTO(), HttpStatus.OK);
	}
	/*
	 * @GetMapping("/all") → Mapea solicitudes HTTP GET a la ruta
	 * /api/v1/degree/all.
	 * 
	 * ResponseEntity<List<DegreeDTO>> → El método devuelve:
	 * 
	 * Un cuerpo: lista de objetos DegreeDTO.
	 * 
	 * Un código HTTP: en este caso 200 OK.
	 */

	// TODO: AGREGAR CRUD
}