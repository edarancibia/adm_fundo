package com.fundo.service;

import java.util.List;

import com.fundo.entities.Raza;

public interface RazaService {

	public abstract List<Raza> findAll();
	
	public abstract Raza addRaza(Raza raza);
	List<Raza> findByIdEstablecimiento(int idestablecimiento);
	
}
