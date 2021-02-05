package com.fundo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fundo.entities.EstadoAnimal;
import com.fundo.serviceImpl.EstadoAnimalServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu","idestab","usernomestab"})
@RequestMapping("/estado")
public class EstadoAnimalController {

	@Autowired
	private EstadoAnimalServiceImpl estadoService;
	
	@GetMapping("/all-estados")
	public @ResponseBody List<EstadoAnimal> allEstados(){
		List<EstadoAnimal> estados = estadoService.getAll();
		return estados;
	}
}
