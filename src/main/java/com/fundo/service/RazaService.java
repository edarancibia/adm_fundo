package com.fundo.service;

import java.util.List;

import com.fundo.entities.Raza;

public interface RazaService {

	public abstract List<Raza> findAll();
	
	List<Raza> findByIdEstablecimiento(int idestablecimiento);
}
