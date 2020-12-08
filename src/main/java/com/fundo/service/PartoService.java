package com.fundo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fundo.entities.Parto;

public interface PartoService {

	public abstract Parto addParto(Parto parto);
	
	public abstract List<Parto> listAllPartos();
	
	List<Map<String, Object>> getPartos(Date fini, Date ffin,Long tipoParto, int idEstablecimiento);
	
	List<Map<String, Object>> getPartosAll(Date fini, Date ffin,int idEstablecimiento);
}
