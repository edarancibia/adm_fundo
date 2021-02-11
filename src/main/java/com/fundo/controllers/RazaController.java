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

import com.fundo.entities.Lote;
import com.fundo.entities.Raza;
import com.fundo.serviceImpl.RazaServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu","idestab","usernomestab"})
@RequestMapping("/raza")
public class RazaController {

	@Autowired
	private RazaServiceImpl razaService;
	
	@GetMapping("/all-razas/{idEstablecimiento}")
	public @ResponseBody List<Raza> getAllRazas(@PathVariable int idEstablecimiento){
		List<Raza> razas = razaService.findByIdEstablecimiento(idEstablecimiento);
		return razas;
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> addRaza(@RequestBody Raza raza){
		if(raza != null) {
			raza = razaService.addRaza(raza);
		}
		return new ResponseEntity<Raza>(raza,HttpStatus.OK);
	}
}
