package com.cev.adtema1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cev.adtema1.domain.Cine;
import com.cev.adtema1.domain.Pelicula;

@Repository
public interface CineRepository extends JpaRepository<Cine, Long> {
	// CONSULTA SQL.
	@Query(nativeQuery = true, value = "SELECT PELICULA.TITULO, CINE.NOMBRE, CINE.PRECIO FROM CINE_PELICULAS JOIN PELICULA  ON (PELICULA.ID = CINE_PELICULAS.PELICULAS_ID) JOIN CINE ON (CINE.ID = CINE_PELICULAS.CINES_ID) WHERE PELICULA.TITULO LIKE 'Mision%' ORDER BY PRECIO")
	List<Pelicula> findByTituloContainingIgnoreCase(String titulo);
	
	//Consulta de prueba:
	List<Cine> findByNombreContainingIgnoreCase(String nombre);
}
