package com.fundo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tratamiento")
public class Tratamiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtratamiento")
	private Long idTratamiento;
	
	@Column(name = "diio")
	private int diio;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "fecha_desde")
	private Date fechaDesde;
	
	@Column(name = "vigente")
	private int vigente;
	
	@Column(name = "fecha_hasta")
	private Date fechaHasta;
	
	@Column(name = "idestablecimiento")
	private int idEstablecimiento;
	
	@Column(name = "resguardo")
	private String resguardo;

	public Long getIdTratamiento() {
		return idTratamiento;
	}

	public void setIdTratamiento(Long idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	public int getDiio() {
		return diio;
	}

	public void setDiio(int diio) {
		this.diio = diio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	public int getVigente() {
		return vigente;
	}

	public void setVigente(int vigente) {
		this.vigente = vigente;
	}


	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public int getIdEstablecimiento() {
		return idEstablecimiento;
	}

	public void setIdEstablecimiento(int idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}

	public Tratamiento(Long idTratamiento, int diio, Date fecha, String descripcion, Date fechaDesde, int vigente,
			Date fechaHasta, int idEstablecimiento, String resguardo) {
		this.idTratamiento = idTratamiento;
		this.diio = diio;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.fechaDesde = fechaDesde;
		this.vigente = vigente;
		this.fechaHasta = fechaHasta;
		this.idEstablecimiento = idEstablecimiento;
		this.resguardo = resguardo;
	}

	public String getResguardo() {
		return resguardo;
	}

	public void setResguardo(String resguardo) {
		this.resguardo = resguardo;
	}

	public Tratamiento() {
		
	}
	
}
