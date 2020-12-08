package com.fundo.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundo.entities.LoteAnimal;
import com.fundo.repository.LoteAnimalRepository;
import com.fundo.service.LoteAnimalService;

@Service
public class LoteAnimalServiceImpl implements LoteAnimalService {

	@Autowired
	public LoteAnimalRepository repository;

	@Override
	public LoteAnimal addLoteAnimal(LoteAnimal loteAnimal) {
		return repository.save(loteAnimal);
	}

	@Override
	@Transactional
	public void updateEstadoLoteAnimal(List<String> checkIDs, int idEstablecimiento) {
		repository.updateEstadoLoteAnimal(checkIDs, idEstablecimiento);
	}
	

}
