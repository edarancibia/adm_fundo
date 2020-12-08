package com.fundo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "toro")
public class Toro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idToro")
	private Long idToro;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "origen")
	private String origen;

	public Long getIdToro() {
		return idToro;
	}

	public void setIdToro(Long idToro) {
		this.idToro = idToro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public Toro(Long idToro, String descripcion, String origen) {
		this.idToro = idToro;
		this.descripcion = descripcion;
		this.origen = origen;
	}

	public Toro() {
		
	}

	@Override
	public String toString() {
		return "Toro [idToro=" + idToro + ", descripcion=" + descripcion + ", origen=" + origen + "]";
	}
	
	
}
