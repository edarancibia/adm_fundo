package com.fundo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estado_animal")
public class EstadoAnimal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idestadoanimal")
	private Long idEstadoAnimal;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "idestablecimiento")
	private int idEstablecimiento;

	public Long getIdEstadoAnimal() {
		return idEstadoAnimal;
	}

	public void setIdEstadoAnimal(Long idEstadoAnimal) {
		this.idEstadoAnimal = idEstadoAnimal;
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

	public EstadoAnimal() {
		
	}

	public EstadoAnimal(Long idEstadoAnimal, String descripcion, int idEstablecimiento) {
		this.idEstadoAnimal = idEstadoAnimal;
		this.descripcion = descripcion;
		this.idEstablecimiento = idEstablecimiento;
	}
	
	
}
