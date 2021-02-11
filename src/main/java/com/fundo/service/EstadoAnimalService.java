package com.fundo.service;

import java.util.List;
import java.util.Map;

import com.fundo.entities.EstadoAnimal;

public interface EstadoAnimalService {

	public abstract EstadoAnimal addEstadoAnimal(EstadoAnimal estado);
	public abstract List<EstadoAnimal> getAll();
	List<EstadoAnimal> findByIdEstablecimiento(int idestablecimiento);
	
}
