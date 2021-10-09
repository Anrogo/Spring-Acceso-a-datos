package com.cev.adtema1.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cev.adtema1.domain.Cine;
import com.cev.adtema1.repository.CineRepository;
import com.cev.adtema1.repository.PeliculaRepository;

@RestController
public class CineController {

	@Autowired
	CineRepository cineRepository;

	@Autowired
	PeliculaRepository peliculaRepository;
	
	public CineController(CineRepository cineRepository) {
		this.cineRepository = cineRepository;
	}

	@GetMapping(path = "/cines")
	List<Cine> getCines() {
		return cineRepository.findAll();
	}

	@PostMapping(path = "/cines")
	Cine altaCine(@RequestBody Cine cine) {
		return cineRepository.save(cine);
	}

	@PutMapping(path = "/cines/{id}")
	Cine modificaCine(@RequestBody Cine cine, @PathVariable Long id) {
		Cine cineGuardado = cineRepository.getById(id);
		cineGuardado.setNombre(cine.getNombre());
		cineGuardado.setPoblacion(cine.getPoblacion());
		cineGuardado.setCodigoPostal(cine.getCodigoPostal());
		cineGuardado.setProvincia(cine.getProvincia());
		cineGuardado.setPrecio(cine.getPrecio());
		cineGuardado.setPeliculas(cine.getPeliculas());
		return cine;
	}

	@DeleteMapping(path = "/cines/{id}")
	String borraPelicula(@PathVariable Long id) {
		cineRepository.delete(cineRepository.getById(id));
		return ("OK");
	}
/*
	@GetMapping(path = "/cinesPrecio")
	List<Pelicula> getCinesPrueba(@RequestParam(required = false, name = "titulo") String titulo) {
		// return cineRepository.findOrderByPrecioDesc();
		if (titulo != null) {
			return cineRepository.findAllByPeliculasTitulo_OrderByPrecioAsc(titulo);
		} else {
			return peliculaRepository.findAll();
		}
	}
*/

}
