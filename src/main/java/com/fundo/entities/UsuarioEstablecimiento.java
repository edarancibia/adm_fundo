package com.fundo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_establecimiento")
public class UsuarioEstablecimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idusuarioestab")
	private Long idusuarioestab;
	
	@Column(name = "idusuario")
	private int idusuario;
	
	@Column(name = "idestablecimiento")
	private int idestablecimiento;

	public Long getIdusuarioestab() {
		return idusuarioestab;
	}

	public void setIdusuarioestab(Long idusuarioestab) {
		this.idusuarioestab = idusuarioestab;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public int getIdestablecimiento() {
		return idestablecimiento;
	}

	public void setIdestablecimiento(int idestablecimiento) {
		this.idestablecimiento = idestablecimiento;
	}

	public UsuarioEstablecimiento(Long idusuarioestab, int idusuario, int idestablecimiento) {
		this.idusuarioestab = idusuarioestab;
		this.idusuario = idusuario;
		this.idestablecimiento = idestablecimiento;
	}

	public UsuarioEstablecimiento() {
		
	}
	
}
