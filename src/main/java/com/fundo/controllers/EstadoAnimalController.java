package com.fundo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fundo.entities.EstadoAnimal;
import com.fundo.entities.Lote;
import com.fundo.serviceImpl.EstadoAnimalServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu","idestab","usernomestab"})
@RequestMapping("/estado")
public class EstadoAnimalController {

	@Autowired
	private EstadoAnimalServiceImpl estadoService;
	
	@GetMapping("/all-estados/{idEstablecimiento}")
	public @ResponseBody List<EstadoAnimal> allEstados(@PathVariable int idEstablecimiento){
		List<EstadoAnimal> estados = estadoService.findByIdEstablecimiento(idEstablecimiento);
		return estados;
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> addEstado(@RequestBody EstadoAnimal estado){
		if(estado != null) {
			estado = estadoService.addEstadoAnimal(estado);
		}
		return new ResponseEntity<EstadoAnimal>(estado,HttpStatus.OK);
	}
}
