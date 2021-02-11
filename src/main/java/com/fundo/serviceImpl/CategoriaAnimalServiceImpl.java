package com.fundo.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundo.entities.CategoriaAnimal;
import com.fundo.repository.CategoriaAnimalRepository;
import com.fundo.service.CategorialAnimalService;

@Service
public class CategoriaAnimalServiceImpl implements CategorialAnimalService {

	@Autowired
	private CategoriaAnimalRepository repository;
	

	@Override
	public List<Map<Object, String>> getCategoriasPesaje() {
		return repository.getCategoriasPesaje();
	}

	@Override
	public List<Map<Object, String>> getCategorias() {
		return repository.getCategorias();
	}

	@Override
	public List<Map<Object, String>> getCatByEstablecimiento(int idEstablecimiento) {
		return repository.getCatByEstablecimiento(idEstablecimiento);
	}

	//RECIBE UN 0 COMO PARAMETRO PARA OBTENER LAS CATEGORIAS POR DEFECTO
	@Override
	public List<CategoriaAnimal> findByIdEstablecimiento(int idEstablecimiento) {
		return repository.findByIdEstablecimiento(idEstablecimiento);
	}

	@Override
	public CategoriaAnimal addCategoriaAnimal(CategoriaAnimal categoria) {
		return repository.save(categoria);
	}

	@Override
	public void deleteCategoria(Long idcategoria) {
		if(this.findByIdCategoriaAnimal(idcategoria) != null) {
			repository.deleteById(idcategoria);
		}
		
	}

	@Override
	public CategoriaAnimal findByIdCategoriaAnimal(Long idcategoria) {
		return repository.findByIdCategoriaAnimal(idcategoria);
	}

}
