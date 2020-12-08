package com.fundo.service;

import java.util.List;
import java.util.Map;

import com.fundo.entities.TipoParto;

public interface TipoPartoService {

	public abstract List<TipoParto> findAll();
	
	List<Map<Object, String>> getTipoParto();
	
	List<Map<Object, String>> getTipoParto2();
}
