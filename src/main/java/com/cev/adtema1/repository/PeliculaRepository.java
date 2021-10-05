package com.cev.adtema1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cev.adtema1.domain.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{
		
}