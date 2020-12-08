package com.fundo.service;

import java.util.List;

import com.fundo.entities.LoteAnimal;

public interface LoteAnimalService {

	public abstract LoteAnimal addLoteAnimal(LoteAnimal loteAnimal);
	
	public void updateEstadoLoteAnimal(List<String> checkIDs, int idEstablecimiento);
}
