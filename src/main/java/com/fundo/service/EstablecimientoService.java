package com.fundo.service;

import java.util.List;

import com.fundo.entities.Establecimiento;

public interface EstablecimientoService {

	public abstract Establecimiento addEstablecimiento(Establecimiento establecimiento);
	
	public void deleteEstablecimiento(Long idEstablecimiento);
	
	List<java.util.Map<String, Object>> getEstablecimientosByUser(Long idUsuario);
}
