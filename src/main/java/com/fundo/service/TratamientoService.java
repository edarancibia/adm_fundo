package com.fundo.service;

import java.util.List;
import java.util.Map;

import com.fundo.entities.Tratamiento;

public interface TratamientoService {

	public Tratamiento addTratamiento(Tratamiento tratamiento);
	
	List<Map<Object, String>> findTratamientoByDiioAndIdEstablecimiento(int diio, int idEstablecimiento);
}
