package com.fundo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "raza")
public class Raza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idraza")
	private Long idRaza;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "idestablecimiento")
	private int idEstablecimiento;

	public Long getIdRaza() {
		return idRaza;
	}

	public void setIdRaza(Long idRaza) {
		this.idRaza = idRaza;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getIdEstablecimiento() {
		return idEstablecimiento;
	}

	public void setIdEstablecimiento(int idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}

	public Raza() {
		
	}

	public Raza(Long idRaza, String descripcion, int idEstablecimiento) {
		this.idRaza = idRaza;
		this.descripcion = descripcion;
		this.idEstablecimiento = idEstablecimiento;
	}

	
}
