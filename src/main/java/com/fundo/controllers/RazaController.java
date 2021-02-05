package com.fundo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fundo.entities.Raza;
import com.fundo.serviceImpl.RazaServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu","idestab","usernomestab"})
@RequestMapping("/raza")
public class RazaController {

	@Autowired
	private RazaServiceImpl razaService;
	
	@GetMapping("/all-razas/{idestablecimiento}")
	public @ResponseBody List<Raza> getAllRazas(@PathVariable int idestablecimiento){
		List<Raza> razas = razaService.findByIdEstablecimiento(idestablecimiento);
		return razas;
	}
}
