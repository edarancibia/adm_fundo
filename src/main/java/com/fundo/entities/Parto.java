package com.fundo.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "parto")
public class Parto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idparto")
	private Long idParto;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "observaciones")
	private String observaciones;
	
	@Column(name = "diio_vaca")
	private Long idVaca;
	
	@Column(name = "diio_ternero")
	private Long idCria;
	
	@Column(name = "tipo_parto")
	private Long idTipoParto;
	
	@Column(name = "idestablecimiento")
	private int idEstablecimiento;
	
	@Column(name = "sexo")
	private String sexo;
	
	public void prePersist() {
		this.fecha = new Date();
	}

	public Long getIdParto() {
		return idParto;
	}

	public void setIdParto(Long idParto) {
		this.idParto = idParto;
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

	public Long getIdVaca() {
		return idVaca;
	}

	public void setIdVaca(Long idVaca) {
		this.idVaca = idVaca;
	}

	public Long getIdCria() {
		return idCria;
	}

	public void setIdCria(Long idCria) {
		this.idCria = idCria;
	}
	
	public Long getIdTipoParto() {
		return idTipoParto;
	}

	public void setIdTipoParto(Long idTipoParto) {
		this.idTipoParto = idTipoParto;
	}

	public int getIdEstablecimiento() {
		return idEstablecimiento;
	}

	public void setIdEstablecimiento(int idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Parto(Long idParto, Date fecha, String observaciones, Long idVaca, Long idCria, Long idTipoParto,
			int idEstablecimiento, String sexo) {
		this.idParto = idParto;
		this.fecha = fecha;
		this.observaciones = observaciones;
		this.idVaca = idVaca;
		this.idCria = idCria;
		this.idTipoParto = idTipoParto;
		this.idEstablecimiento = idEstablecimiento;
		this.sexo = sexo;
	}

	public Parto() {
		
	}

	@Override
	public String toString() {
		return "Parto [idParto=" + idParto + ", fecha=" + fecha + ", observaciones=" + observaciones + ", idVaca="
				+ idVaca + ", idCria=" + idCria + ", idTipoParto=" + idTipoParto + ", idEstablecimiento="
				+ idEstablecimiento + ", sexo=" + sexo + "]";
	}

	
}
