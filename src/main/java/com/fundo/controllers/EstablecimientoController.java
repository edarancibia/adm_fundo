package com.fundo.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fundo.entities.Establecimiento;
import com.fundo.entities.Lote;
import com.fundo.entities.UsuarioEstablecimiento;
import com.fundo.serviceImpl.EstablecimientoServiceImpl;
import com.fundo.serviceImpl.LoteServiceImpl;
import com.fundo.serviceImpl.UsuarioEstbSerciceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu","idestab","usernomestab"})
@RequestMapping("/establecimiento")
public class EstablecimientoController {

	@Autowired
	public EstablecimientoServiceImpl service;
	
	@Autowired
	public UsuarioEstbSerciceImpl usEstaService;
	
	@Autowired
	public LoteServiceImpl loteService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addEstablecimiento(@RequestBody Establecimiento establecimiento){
		
		Establecimiento newestab = new Establecimiento();
		if(establecimiento != null) {
			newestab = service.addEstablecimiento(establecimiento);
		}
		return new ResponseEntity<Establecimiento>(newestab,HttpStatus.OK);
	}
	
	@PostMapping("/usu-est")
	public ResponseEntity<?> addUsuEstab(@RequestBody UsuarioEstablecimiento usuEstab){
		UsuarioEstablecimiento newUsuEst = new UsuarioEstablecimiento();
		if(usuEstab != null) {
			newUsuEst = usEstaService.addUserEstab(usuEstab);
		}
		return new ResponseEntity<UsuarioEstablecimiento>(newUsuEst,HttpStatus.OK);
	}
	
	@GetMapping("/by-user/{idUsuario}")
	public List<Map<String, Object>> getByUser(@PathVariable Long idUsuario){
		List<Map<String, Object>> estab = service.getEstablecimientosByUser(idUsuario);
		return estab;
	}
	
}
