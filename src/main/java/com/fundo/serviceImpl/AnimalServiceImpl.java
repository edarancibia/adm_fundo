package com.fundo.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundo.entities.Animal;
import com.fundo.repository.AnimalRepository;
import com.fundo.service.AnimalService;

@Service
public class AnimalServiceImpl implements AnimalService {

	@Autowired
	public AnimalRepository animalRepository;
	
	@Override
	public Animal addAnimal(Animal animal) {
		return animalRepository.save(animal);
	}

	@Override
	public Animal findAnimalByDiioAndIdCategoriaAndIdEstablecimiento(int diio, Long idCategoria,
			int idEstablecimiento) {
		return animalRepository.findAnimalByDiioAndIdCategoriaAndIdEstablecimiento(diio, idCategoria, idEstablecimiento);
	}

	@Override
	public List<Map<String, Object>> getByCategoriaAndEstadoAndEstablecimiento(int idCategoria, Long estadoAnimal,
			int idEstablecimiento) {
		return animalRepository.getByCategoriaAndEstadoAndEstablecimiento(idCategoria, estadoAnimal, idEstablecimiento);
	}

	@Override
	public List<Map<String, Object>> getAllByEstablecimiento(int idEstablecimiento) {
		return animalRepository.getAllByEstablecimiento(idEstablecimiento);
	}

	@Override
	public Animal findAnimalByDiioAndIdEstablecimientoAndEstado(int diio, int idEstablecimiento, int estado) {
		return animalRepository.findAnimalByDiioAndIdEstablecimientoAndEstado(diio, idEstablecimiento, estado);
	}

	@Override
	public List<Map<String, Object>> getByCategoriaAndEstablecimiento(int idCategoria, int idEstablecimiento) {
		return animalRepository.getByCategoriaAndEstablecimiento(idCategoria, idEstablecimiento);
	}

	@Override
	public List<Map<String, Object>> getByLoteAndEstablecimiento(int idLote, int idEstablecimiento) {
		return animalRepository.getByLoteAndEstablecimiento(idLote, idEstablecimiento);
	}

	@Override
	public Animal getUltimoRegistro(int diio, int idestablecimiento) {
		return animalRepository.getUltimoRegistro(diio, idestablecimiento);
	}
	
}
