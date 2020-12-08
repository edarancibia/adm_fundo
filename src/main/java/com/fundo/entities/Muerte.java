package com.fundo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "muerte")
public class Muerte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idmuerte")
	private Long idMuerte;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "observaciones")
	private String observaciones;
	
	@Column(name = "diio")
	private int diio;
	
	@Column(name = "idestablecimiento")
	private int idEstablecimiento;

	public Long getIdMuerte() {
		return idMuerte;
	}

	public void setIdMuerte(Long idMuerte) {
		this.idMuerte = idMuerte;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public int getDiio() {
		return diio;
	}

	public void setDiio(int diio) {
		this.diio = diio;
	}

	public int getIdEstablecimiento() {
		return idEstablecimiento;
	}

	public void setIdEstablecimiento(int idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}
	
	public Muerte(Long idMuerte, Date fecha, String observaciones, int diio, int idEstablecimiento) {
		this.idMuerte = idMuerte;
		this.fecha = fecha;
		this.observaciones = observaciones;
		this.diio = diio;
		this.idEstablecimiento = idEstablecimiento;
	}

	public Muerte() {
		
	}


}
