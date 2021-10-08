package com.cev.adtema1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cev.adtema1.domain.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

	List<Pelicula> findByTituloContaining(String titulo);

	List<Pelicula> findByTituloContainingIgnoreCaseAndPuntuacionGreaterThanEqualAndPuntuacionLessThanEqual(
			String titulo, Integer puntuacionMin, Integer puntuacionMax);

	List<Pelicula> findByPuntuacionGreaterThanEqualAndPuntuacionLessThanEqual(Integer puntuacionMin,
			Integer puntuacionMax);

	List<Pelicula> findByTituloContainingIgnoreCaseAndPuntuacionGreaterThanEqual(String titulo, Integer puntuacionMin);

	List<Pelicula> findByPuntuacionGreaterThanEqual(Integer puntuacionMin);

	List<Pelicula> findByTituloContainingIgnoreCaseAndPuntuacionLessThanEqual(String titulo, Integer puntuacionMax);

	List<Pelicula> findByPuntuacionLessThanEqual(Integer puntuacionMax);

	List<Pelicula> findByTituloContainingIgnoreCase(String titulo);

	List<Pelicula> findByActores_nombreContainingIgnoreCaseOrderByPuntuacionDesc(String nombre);

	List<Pelicula> findByCines_nombreContainingIgnoreCase(String nombre);

	// CONSULTA HQL
	@Query("Select pelicula from Pelicula pelicula where pelicula.titulo =: titulo")
	List<Pelicula> findByTituloHibernate(@Param("titulo") String titulo);

	// CONSULTA SQL. Son más recomendables para actualizar datos
	@Query(nativeQuery = true, value = " select * from pelicula p where p.titulo =:titulo")
	List<Pelicula> findByTitulosSql(@Param("titulo") String titulo);

	// CONSULTA SQL para obtener listado de cines y precios de menor a mayor en
	// función de la película dada
	@Query(nativeQuery = true, value = "SELECT PELICULA.TITULO, CINE.NOMBRE, CINE.PRECIO FROM CINE_PELICULAS JOIN PELICULA  ON (PELICULA.ID = CINE_PELICULAS.PELICULAS_ID) JOIN CINE ON (CINE.ID = CINE_PELICULAS.CINES_ID) WHERE PELICULA.TITULO LIKE :titulo% ORDER BY PRECIO")
	List<Pelicula> findByTitulo(String titulo);
}