package com.fundo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pesaje")
public class Pesaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpesaje")
	private Long idPesaje;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "diio")
	private Long diio;
	
	@Column(name = "peso")
	private Double peso;
	
	@Column(name = "ganado")
	private Double ganado;
	
	@Column(name = "observaciones")
	private String observaciones;
	
	@Column(name = "idestablecimiento")
	private int idEstablecimiento;
	

	public void prePersist() {
		this.fecha = new Date();
	}

	public Long getIdPesaje() {
		return idPesaje;
	}

	public void setIdPesaje(Long idPesaje) {
		this.idPesaje = idPesaje;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getGanado() {
		return ganado;
	}

	public void setGanado(Double ganado) {
		this.ganado = ganado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	

	public Long getDiio() {
		return diio;
	}

	public void setDiio(Long diio) {
		this.diio = diio;
	}

	public int getIdEstablecimiento() {
		return idEstablecimiento;
	}

	public void setIdEstablecimiento(int idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}
	
	public Pesaje(Long idPesaje, Date fecha, Long diio, Double peso, Double ganado, String observaciones,
			int idEstablecimiento) {
		this.idPesaje = idPesaje;
		this.fecha = fecha;
		this.diio = diio;
		this.peso = peso;
		this.ganado = ganado;
		this.observaciones = observaciones;
		this.idEstablecimiento = idEstablecimiento;
	}

	public Pesaje() {
		
	}

}
