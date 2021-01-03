package com.fundo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idusuario")
	private Long idUsuario;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "vigente")
	private String vigente;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getVigente() {
		return vigente;
	}

	public void setVigente(String vigente) {
		this.vigente = vigente;
	}

	public Usuario(Long idUsuario, String email, String password, String nombre, String apellido, String vigente) {
		this.idUsuario = idUsuario;
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.vigente = vigente;
	}

	public Usuario() {
		
	}
	
}
