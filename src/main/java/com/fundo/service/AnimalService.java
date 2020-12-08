package com.fundo.service;

import java.util.List;
import java.util.Map;

import com.fundo.entities.Animal;

public interface AnimalService {

	public abstract Animal addAnimal(Animal animal);
	
	public Animal getUltimoRegistro(int diio, int idestablecimiento);
	
	public abstract Animal findAnimalByDiioAndIdEstablecimientoAndEstado(int diio, int idEstablecimiento,int estado);
	
	public abstract Animal findAnimalByDiioAndIdCategoriaAndIdEstablecimiento(int diio, Long idCategoria, int idEstablecimiento);
	
	List<Map<String, Object>> getByCategoriaAndEstadoAndEstablecimiento(int idCategoria,Long estadoAnimal,int idEstablecimiento);
	
	List<Map<String, Object>> getAllByEstablecimiento(int idEstablecimiento);
	
	List<Map<String, Object>> getByCategoriaAndEstablecimiento(int idCategoria,int idEstablecimiento);
	
	List<Map<String, Object>> getByLoteAndEstablecimiento(int idLote, int idEstablecimiento);
	
}
