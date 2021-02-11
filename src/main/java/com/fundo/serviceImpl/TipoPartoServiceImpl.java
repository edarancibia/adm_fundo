package com.fundo.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundo.entities.TipoParto;
import com.fundo.repository.TipoPartoRepository;
import com.fundo.service.TipoPartoService;

@Service
public class TipoPartoServiceImpl implements TipoPartoService {

	@Autowired
	private TipoPartoRepository tPartoRepository;
	
	@Override
	public List<TipoParto> findAll() {
		return tPartoRepository.findAll();
	}

	@Override
	public List<Map<Object, String>> getTipoParto() {
		return tPartoRepository.getTipoParto();
	}

	@Override
	public List<Map<Object, String>> getTipoParto2() {
		return tPartoRepository.getTipoParto2();
	}

	@Override
	public TipoParto addTipoParto(TipoParto tipo) {
		return tPartoRepository.save(tipo);
	}

}
