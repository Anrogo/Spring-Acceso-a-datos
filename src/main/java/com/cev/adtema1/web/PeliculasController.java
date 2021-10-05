package com.cev.adtema1.web;

import java.util.List;

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
import com.cev.adtema1.service.PeliculasPersistService;
import com.cev.adtema1.web.error.CustomError;


@RestController
public class PeliculasController {

    @Autowired
    PeliculasPersistService peliculasPersistService;

    @GetMapping(path = "/peliculas")
    List<Pelicula> getPeliculas(@RequestParam(required = false) String titulo, @RequestParam(required = false, name = "puntuacion.min", defaultValue = "2") int puntuacionMinima) {

        if (puntuacionMinima < 2) {
            throw new CustomError("La puntuacion no puede ser menor que 2");
        }
        if (titulo != null) {
            //return peliculasPersistService.buscaPorTitulo(titulo);
        	return peliculasPersistService.getPeliculas();
        } else
            return peliculasPersistService.getPeliculas();
    }


    @GetMapping(path = "/peliculas/{id}")
    Pelicula getPeliculas(@PathVariable Long id) {
    	if(peliculasPersistService.findById(id).isPresent()) {
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

    @GetMapping(path = "/peliculasHeader")
    ResponseEntity<List<Pelicula>> getPeliculasHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("MiHeaderRespuesta", "HeaderRespuesta");

        return ResponseEntity.ok().headers(headers).body(peliculasPersistService.getPeliculas());
    }


}

