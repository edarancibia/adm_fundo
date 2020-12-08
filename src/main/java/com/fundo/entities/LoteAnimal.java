package com.fundo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lote_animal")
public class LoteAnimal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idloteanimal")
	private Long idLoteAnimal;
	
	@Column(name = "idlote")
	private int idLote;
	
	@Column(name = "diio")
	private int diio;
	
	@Column(name = "vigente")
	private int vigente;
	
	@Column(name = "id_establecimiento")
	private int idEstablecimiento;
	
	@Column(name = "fecha_accion")
	private Date fechaAccion;

	public int getIdLote() {
		return idLote;
	}

	public void setIdLote(int idLote) {
		this.idLote = idLote;
	}

	public int getDiio() {
		return diio;
	}

	public void setDiio(int diio) {
		this.diio = diio;
	}

	public int getVigente() {
		return vigente;
	}

	public void setVigente(int vigente) {
		this.vigente = vigente;
	}

	public int getIdEstablecimiento() {
		return idEstablecimiento;
	}

	public void setIdEstablecimiento(int idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}

	public Date getFechaAccion() {
		return fechaAccion;
	}

	public void setFechaAccion(Date fechaAccion) {
		this.fechaAccion = fechaAccion;
	}


	public Long getIdLoteAnimal() {
		return idLoteAnimal;
	}

	public void setIdLoteAnimal(Long idLoteAnimal) {
		this.idLoteAnimal = idLoteAnimal;
	}
	
	public LoteAnimal(Long idLoteAnimal, int idLote, int diio, int vigente, int idEstablecimiento, Date fechaAccion) {
		this.idLoteAnimal = idLoteAnimal;
		this.idLote = idLote;
		this.diio = diio;
		this.vigente = vigente;
		this.idEstablecimiento = idEstablecimiento;
		this.fechaAccion = fechaAccion;
	}

	public LoteAnimal() {
		
	}
	
	
}
