package com.fundo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fundo.entities.CategoriaAnimal;
import com.fundo.serviceImpl.CategoriaAnimalServiceImpl;

@RestController
@RequestMapping("/categoria")
public class CategoriaAnimalController {

	@Autowired
	private CategoriaAnimalServiceImpl categoriaService;
	
	//GET ALL BY ESTABLECIMIENTO
	@GetMapping("/all/{idEstablecimiento}")
	public List<Map<Object, String>> allCatByEstablecimiento(@PathVariable int idEstablecimiento){
		List<Map<Object, String>> categorias = categoriaService.getCatByEstablecimiento(idEstablecimiento);
		return categorias;
	}
	
	//GET CATEGORIAS POR DEFECTO
	@GetMapping("/{idEstablecimiento}")
	@ResponseBody
	public List<CategoriaAnimal> getDefault(@PathVariable int idEstablecimiento){
		List<CategoriaAnimal> categorias = categoriaService.findByIdEstablecimiento(idEstablecimiento);
		return categorias;
	}
	
	@GetMapping("/allCategorias-pesaje")
	@ResponseBody
	public List<Map<Object, String>> allCategoriasPesaje(){
		List<Map<Object, String>> categorias = categoriaService.getCategoriasPesaje();
		return categorias;
	}
	
}
