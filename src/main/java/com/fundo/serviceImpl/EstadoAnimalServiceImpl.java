package com.fundo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundo.entities.EstadoAnimal;
import com.fundo.repository.EstadoAnimalRepository;
import com.fundo.service.EstadoAnimalService;

@Service
public class EstadoAnimalServiceImpl implements EstadoAnimalService {

	@Autowired
	private EstadoAnimalRepository estadoRepository;
	
	@Override
	public List<EstadoAnimal> getAll() {
		return estadoRepository.findAll();
	}

}
