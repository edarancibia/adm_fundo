package com.fundo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundo.entities.Establecimiento;
import com.fundo.serviceImpl.EstablecimientoServiceImpl;

@RestController
@RequestMapping("/establecimiento")
public class EstablecimientoController {

	@Autowired
	public EstablecimientoServiceImpl service;
	
	@PostMapping("/add")
	public ResponseEntity<Establecimiento> addEstablecimiento(@RequestBody Establecimiento establecimiento){
		if(establecimiento != null) {
			establecimiento = service.addEstablecimiento(establecimiento);
		}
		return new ResponseEntity<Establecimiento>(establecimiento,HttpStatus.OK);
	}
	
	@GetMapping("/by-user/{idUsuario}")
	public List<Map<String, Object>> getByUser(@PathVariable Long idUsuario){
		List<Map<String, Object>> estab = service.getEstablecimientosByUser(idUsuario);
		return estab;
	}
	
}
