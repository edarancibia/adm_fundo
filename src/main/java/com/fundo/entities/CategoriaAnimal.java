package com.fundo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categoria_animal")
public class CategoriaAnimal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcategoriaanimal")
	private Long idCategoriaAnimal;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "idestablecimiento")
	private int idEstablecimiento;

	public Long getIdCategoriaAnimal() {
		return idCategoriaAnimal;
	}

	public void setIdCategoriaAnimal(Long idCategoriaAnimal) {
		this.idCategoriaAnimal = idCategoriaAnimal;
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

	public CategoriaAnimal(Long idCategoriaAnimal, String descripcion, int idEstablecimiento) {
		super();
		this.idCategoriaAnimal = idCategoriaAnimal;
		this.descripcion = descripcion;
		this.idEstablecimiento = idEstablecimiento;
	}

	public CategoriaAnimal() {
		
	}


	
	
}
