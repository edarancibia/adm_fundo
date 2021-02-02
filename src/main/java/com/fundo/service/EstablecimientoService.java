package com.fundo.service;

import java.util.List;
import java.util.Map;

import com.fundo.entities.Establecimiento;
import com.fundo.entities.UsuarioEstablecimiento;

public interface EstablecimientoService {

	public abstract Establecimiento addEstablecimiento(Establecimiento establecimiento);
	
	public void deleteEstablecimiento(Long idEstablecimiento);
	
	List<java.util.Map<String, Object>> getEstablecimientosByUser(Long idUsuario);
	
	List<Map<String, Object>> getIdcentroByUsuario(int idusuario);
	
	public abstract Establecimiento fingByIdestablecimiento(Long idestablecimiento);
	
}
