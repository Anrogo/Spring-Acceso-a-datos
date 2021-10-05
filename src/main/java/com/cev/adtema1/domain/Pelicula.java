package com.cev.adtema1.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Pelicula {

	@Id
	@GeneratedValue
	Long id;
	
	String titulo;
	int puntuacion;
	String sinopsis;
	String director;
	Date fechaEstreno;
	
	@OneToOne(mappedBy = "pelicula")
	@JsonManagedReference
	Estreno estreno;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public String getSinopsis() {
		return sinopsis;
	}
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public Date getFechaEstreno() {
		return fechaEstreno;
	}
	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}
	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", puntuacion=" + puntuacion + ", sinopsis=" + sinopsis + ", director="
				+ director + ", fechaEstreno=" + fechaEstreno + "]";
	}
	

}
