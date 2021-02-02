package com.fundo.service;

import java.util.List;

import com.fundo.entities.UsuarioEstablecimiento;

public interface UsuarioEstabService {
	
	List<UsuarioEstablecimiento> findByIdusuario(int idusuario);
	public abstract UsuarioEstablecimiento addUserEstab(UsuarioEstablecimiento userEstab);

}
