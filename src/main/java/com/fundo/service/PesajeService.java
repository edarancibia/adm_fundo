package com.fundo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fundo.entities.Pesaje;

public interface PesajeService {

	public abstract Pesaje addPesaje(Pesaje pesaje);
	
	public abstract List<Pesaje> getAll();
	
	public abstract List<Pesaje> getAllByFecha();
	
	public abstract void removePesaje(Long idPesaje);
	
	List<Map<String, Object>> getPesoGanado(Long diio);
	
	List<Map<String, Object>> getPesajeByCategoria(Long idCategoria,Date fini, Date ffin);
	
	List<Map<String, Object>> getPesajeByCategoriaAll(Date fini, Date ffin);
	
	List<Map<String, Integer>> getPesajeGrafico(Long diio);
	
	Iterable<Map<Object, Integer>> getPesajeGrafico2(Long diio);
	
}
