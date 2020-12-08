package com.fundo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundo.entities.Parto;
import com.fundo.repository.PartoRepository;
import com.fundo.service.PartoService;

@Service
public class PartoServiceImpl implements PartoService {

	@Autowired
	private PartoRepository partoRepository;
	
	@Override
	public Parto addParto(Parto parto) {
		return partoRepository.save(parto);
	}

	@Override
	public List<Parto> listAllPartos() {
		return partoRepository.findAll();
	}

	@Override
	public List<Map<String, Object>> getPartos(Date fini, Date ffin,Long tipoParto,int idEstablecimiento) {
		return partoRepository.getPartos(fini, ffin, tipoParto, idEstablecimiento);
	}

	@Override
	public List<Map<String, Object>> getPartosAll(Date fini, Date ffin,int idEstablecimiento) {
		return partoRepository.getPartosAll(fini, ffin, idEstablecimiento);
	}

}
