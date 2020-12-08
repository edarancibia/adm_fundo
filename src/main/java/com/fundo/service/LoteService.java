package com.fundo.service;

import java.util.List;
import java.util.Map;

import com.fundo.entities.Lote;

public interface LoteService {

	public abstract Lote addLote(Lote lote);
	
	public void deleteLote(Long idLote);
	
	List<Map<String, Object>> getLotesByEstablecimiento(int idEstablecimiento);
	
	public abstract Lote findLoteByIdLoteAndIdEstablecimiento(Long idLote, int idEstablecimiento);
	
	List<Map<String, Object>> getAnimalesByIdLoteAndIdEstablecimiento(Long idLote, int idEstablecimiento);
	
	List<Map<String, Object>> validaAnimalLote(int idLote, int diio, int idEstablecimiento);
	
	List<Map<String, Object>> getLoteDefecto(int idEstablecimiento);
}
