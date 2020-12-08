package com.fundo.service;

import java.util.List;
import java.util.Map;

import com.fundo.entities.CategoriaAnimal;

public interface CategorialAnimalService {
	
	List<Map<Object, String>> getCategoriasPesaje();
	
	List<Map<Object, String>> getCategorias();
	
	List<Map<Object, String>> getCatByEstablecimiento(int idEstablecimiento);
	
	//RECIBE UN 0 COMO PARAMETRO PARA OBTENER LAS CATEGORIAS POR DEFECTO
	List<CategoriaAnimal> findByIdEstablecimiento(int idEstablecimiento);
}
