package com.fundo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "establecimiento")
public class Establecimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idestablecimiento")
	private Long idEstablecimiento;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "vigente")
	private int vigente;

	public Long getIdEstablecimiento() {
		return idEstablecimiento;
	}

	public void setIdEstablecimiento(Long idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getVigente() {
		return vigente;
	}

	public void setVigente(int vigente) {
		this.vigente = vigente;
	}

	public Establecimiento() {

	}

	public Establecimiento(Long idEstablecimiento, String descripcion, int vigente) {
		this.idEstablecimiento = idEstablecimiento;
		this.descripcion = descripcion;
		this.vigente = vigente;
	}
	
	
}
