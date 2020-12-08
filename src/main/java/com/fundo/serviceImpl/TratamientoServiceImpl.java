package com.fundo.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundo.entities.Tratamiento;
import com.fundo.repository.TratamientoRepository;
import com.fundo.service.TratamientoService;

@Service
public class TratamientoServiceImpl implements TratamientoService {

	@Autowired
	public TratamientoRepository repository;
	
	@Override
	public Tratamiento addTratamiento(Tratamiento tratamiento) {
		return repository.save(tratamiento);
	}

	@Override
	public List<Map<Object, String>> findTratamientoByDiioAndIdEstablecimiento(int diio, int idEstablecimiento) {
		return repository.findTratamientoByDiioAndIdEstablecimiento(diio, idEstablecimiento);
	}
	
}
