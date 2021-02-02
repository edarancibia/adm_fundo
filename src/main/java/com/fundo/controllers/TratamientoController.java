package com.fundo.controllers;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.fundo.entities.Tratamiento;
import com.fundo.serviceImpl.TratamientoServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu","idestab","usernomestab"})
@RequestMapping("/tratamientos")
public class TratamientoController {

	@Autowired
	public TratamientoServiceImpl tratamientoService;
	
	@GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("./tratamientos/index");
	}
	
	@GetMapping("/new")
	public ModelAndView newTratatiemto() {
		return new ModelAndView("./tratamientos/tratamientos");
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addTratamiento(@RequestBody Tratamiento tratamiento){
		tratamiento = tratamientoService.addTratamiento(tratamiento);
		return ResponseEntity.status(HttpStatus.CREATED).body(tratamiento);
	}
	
	@GetMapping("/find-by-diio/{diio}/{idEstablecimiento}")
	public @ResponseBody List<Map<Object, String>> getByDiioAndEstablecimiento(@PathVariable int diio, @PathVariable int idEstablecimiento){
		List<Map<Object, String>> tratamientos = tratamientoService.findTratamientoByDiioAndIdEstablecimiento(diio, idEstablecimiento);
		return  tratamientos;
	}
}
