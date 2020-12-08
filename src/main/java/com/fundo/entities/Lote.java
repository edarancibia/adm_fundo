package com.fundo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lotes")
public class Lote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idlote")
	private Long idLote;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "idestablecimiento")
	private int idEstablecimiento;
	
	@Column(name = "vigente")
	private int vigente;
	
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	public Long getIdLote() {
		return idLote;
	}

	public void setIdLote(Long idLote) {
		this.idLote = idLote;
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

	public int getVigente() {
		return vigente;
	}

	public void setVigente(int vigente) {
		this.vigente = vigente;
	}
	
	
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Lote(Long idLote, String descripcion, int idEstablecimiento, int vigente, Date fechaCreacion) {
		this.idLote = idLote;
		this.descripcion = descripcion;
		this.idEstablecimiento = idEstablecimiento;
		this.vigente = vigente;
		this.fechaCreacion = fechaCreacion;
	}

	public Lote() {
	
	}
	
}
