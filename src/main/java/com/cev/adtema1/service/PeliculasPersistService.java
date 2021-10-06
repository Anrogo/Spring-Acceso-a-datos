package com.cev.adtema1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cev.adtema1.domain.Pelicula;
import com.cev.adtema1.repository.PeliculaRepository;

@Service
public class PeliculasPersistService {
	PeliculaRepository peliculaRepository;

	public PeliculasPersistService(PeliculaRepository peliculaRepository) {
		this.peliculaRepository = peliculaRepository;
	}

	public Pelicula getPelicula(Long id) {
		return peliculaRepository.getById(id);
	}

	public Long add(Pelicula pelicula) {
		Pelicula peliculaGuardada = peliculaRepository.save(pelicula);
		return peliculaGuardada.getId();
	}

	public List<Pelicula> getPeliculas() {
		return peliculaRepository.findAll();
	}

	public void guarda(Long id, Pelicula pelicula) {
		Pelicula peliculaGuardada = peliculaRepository.getById(id);
		peliculaGuardada.setDirector(pelicula.getDirector());
		peliculaGuardada.setFechaEstreno(pelicula.getFechaEstreno());
		peliculaGuardada.setPuntuacion(pelicula.getPuntuacion());
		peliculaGuardada.setSinopsis(pelicula.getSinopsis());
		peliculaGuardada.setTitulo(pelicula.getTitulo());
		peliculaRepository.save(peliculaGuardada);
	}

	public void borra(Long id) {
		peliculaRepository.delete(peliculaRepository.getById(id));
	}

	public Optional<Pelicula> findById(Long id) {

		return peliculaRepository.findById(id);
	}

	public List<Pelicula> findByTitulo(String titulo) {

		return peliculaRepository.findByTituloContaining(titulo);
	}

}
