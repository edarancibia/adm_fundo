package com.fundo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "animal")
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idanimal")
	private Long id;
	
	@Column(name = "estado")
	private int estado;
	
	@Column(name = "diio")
	private int diio;
	
	@Column(name = "fecha_accion")
	private Date fecha_accion;
	
	@Column(name = "idraza")
	private Long idRaza;
	
	@Column(name = "idcategoria")
	private Long idCategoria;
	
	@Column(name = "idestado")
	private Long idEstadoAnimal;
	
	@Column(name = "sexo")
	private String sexo;
	
	@Column(name = "fecha_nacimiento")
	private Date fechaNac;
	
	@Column(name = "fecha_incorporacion")
	private Date fechaIncorporacion;
	
	@Column(name = "idestablecimiento")
	private int idEstablecimiento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getDiio() {
		return diio;
	}

	public void setDiio(int diio) {
		this.diio = diio;
	}

	public Date getFecha_accion() {
		return fecha_accion;
	}

	public void setFecha_accion(Date fecha_accion) {
		this.fecha_accion = fecha_accion;
	}

	public Long getIdRaza() {
		return idRaza;
	}

	public void setIdRaza(Long idRaza) {
		this.idRaza = idRaza;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Long getIdEstadoAnimal() {
		return idEstadoAnimal;
	}

	public void setIdEstadoAnimal(Long idEstadoAnimal) {
		this.idEstadoAnimal = idEstadoAnimal;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Date getFechaIncorporacion() {
		return fechaIncorporacion;
	}

	public void setFechaIncorporacion(Date fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}
	

	public int getIdEstablecimiento() {
		return idEstablecimiento;
	}

	public void setIdEstablecimiento(int idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}

	public Animal(Long id, int estado, int diio, Date fecha_accion, Long idRaza, Long idCategoria, Long idEstadoAnimal,
			String sexo, Date fechaNac, Date fechaIncorporacion, int idEstablecimiento) {
		super();
		this.id = id;
		this.estado = estado;
		this.diio = diio;
		this.fecha_accion = fecha_accion;
		this.idRaza = idRaza;
		this.idCategoria = idCategoria;
		this.idEstadoAnimal = idEstadoAnimal;
		this.sexo = sexo;
		this.fechaNac = fechaNac;
		this.fechaIncorporacion = fechaIncorporacion;
		this.idEstablecimiento = idEstablecimiento;
	}

	public Animal() {

	}
	
	
}
