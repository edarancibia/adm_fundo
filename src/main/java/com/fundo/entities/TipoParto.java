package com.fundo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
 @Table(name = "tipo_parto")
public class TipoParto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtipoparto")
	private Long idTipoParto;
	
	@Column(name = "descripcion")
	private String descripcion;

	public Long getIdTipoParto() {
		return idTipoParto;
	}

	public void setIdTipoParto(Long idTipoParto) {
		this.idTipoParto = idTipoParto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoParto(Long idTipoParto, String descripcion) {
		
		this.idTipoParto = idTipoParto;
		this.descripcion = descripcion;
	}

	public TipoParto() {
		
	}

	@Override
	public String toString() {
		return "TipoParto [idTipoParto=" + idTipoParto + ", descripcion=" + descripcion + "]";
	}
	
	
}
