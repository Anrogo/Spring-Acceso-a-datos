package com.cev.adtema1.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cev.adtema1.domain.Pelicula;
import com.cev.adtema1.repository.PeliculaRepository;
import com.cev.adtema1.service.PeliculasPersistService;
import com.cev.adtema1.web.error.CustomError;

@RestController
public class PeliculasController {

	private final Logger log = LoggerFactory.getLogger(PeliculasController.class);
	
	@Autowired
	PeliculasPersistService peliculasPersistService;

	@Autowired
	PeliculaRepository peliculaRepository;

	@GetMapping(path = "/peliculas")
	List<Pelicula> getPeliculas(@RequestParam(required = false) String titulo,
			@RequestParam(required = false, name = "puntuacion.min") Integer puntuacionMin,
			@RequestParam(required = false, name = "puntuacion.max") Integer puntuacionMax,
			@RequestParam(required = false, name = "actor") String actor, HttpServletRequest request, Principal principal) {

		log.info("Usuario autenticado: " + principal.getName());
		
		if (request.isUserInRole("ROLE_ADMIN")) {
			
			if (actor != null) {
				return peliculaRepository.findByActores_nombreContainingIgnoreCaseOrderByPuntuacionDesc(actor);
			}

			if (puntuacionMin != null) {
				if (puntuacionMax != null) {
					if (titulo != null) { // Puntuacion Min, Puntuacion Max y titulo
						return peliculaRepository
								.findByTituloContainingIgnoreCaseAndPuntuacionGreaterThanEqualAndPuntuacionLessThanEqual(
										titulo, puntuacionMin, puntuacionMax);
					} else { // Puntuacion Min, Puntuacion Max y titulo null
						return peliculaRepository.findByPuntuacionGreaterThanEqualAndPuntuacionLessThanEqual(
								puntuacionMin, puntuacionMax);
					}
				} else { // Puntuacion Min, Puntuacion Max null y titulo
					if (titulo != null) {
						return peliculaRepository.findByTituloContainingIgnoreCaseAndPuntuacionGreaterThanEqual(titulo,
								puntuacionMin);
					} else { // Puntuacion Min, Puntuacion Max null y titulo null
						return peliculaRepository.findByPuntuacionGreaterThanEqual(puntuacionMin);
					}

				}
			} else {
				if (puntuacionMax != null) {
					if (titulo != null) { // Puntuacion Min null, Puntuacion Max y titulo
						return peliculaRepository.findByTituloContainingIgnoreCaseAndPuntuacionLessThanEqual(titulo,
								puntuacionMax);
					} else { // Puntuacion Min null, Puntuacion Max y titulo null
						return peliculaRepository.findByPuntuacionLessThanEqual(puntuacionMax);
					}
				} else { // Puntuacion Max null
					if (titulo != null) {
						return peliculaRepository.findByTituloContainingIgnoreCase(titulo);
					} else {
						return peliculaRepository.findAll();
					}
				}

			}
		} else {
			return peliculaRepository.findAll();
		}
	}

	@GetMapping(path = "/peliculas/{id}")
	Pelicula getPeliculas(@PathVariable Long id) {
		if (peliculasPersistService.findById(id).isPresent()) {
			return peliculasPersistService.findById(id).get();
		} else {
			throw new CustomError("No encuentro esa película");
		}

	}

	@PostMapping(path = "/peliculas")
	Long altaPelicula(@RequestBody Pelicula pelicula) {
		return peliculasPersistService.add(pelicula);
	}

	@PutMapping(path = "/peliculas/{id}")
	Pelicula modificaPelicula(@RequestBody Pelicula pelicula, @PathVariable Long id) {
		peliculasPersistService.guarda(id, pelicula);
		return pelicula;
	}

	@DeleteMapping(path = "/peliculas/{id}")
	String borraPelicula(@PathVariable Long id) {
		peliculasPersistService.borra(id);
		return ("OK");
	}

	@GetMapping(path = "/peliculasPrecio")
	List<Pelicula> getPeliculaPrecio(@RequestParam(required = false, name = "titulo") String titulo){
		
		if(titulo != null) {
			return peliculaRepository.findByTitulo(titulo);
		} else {
			return peliculaRepository.findAll();
		}
		
	}
	
	@GetMapping(path = "/peliculasHeader")
	ResponseEntity<List<Pelicula>> getPeliculasHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("MiHeaderRespuesta", "HeaderRespuesta");

		return ResponseEntity.ok().headers(headers).body(peliculasPersistService.getPeliculas());
	}

}
