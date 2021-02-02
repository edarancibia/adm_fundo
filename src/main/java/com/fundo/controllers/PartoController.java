package com.fundo.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fundo.entities.Parto;
import com.fundo.serviceImpl.PartoServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu","idestab","usernomestab"})
@RequestMapping("/partos")
public class PartoController {

	private static final Log LOG = LogFactory.getLog(PartoController.class);
	@Autowired
	private PartoServiceImpl partoService;
	
	@GetMapping("/")
	public ModelAndView viewAll() {
		return new ModelAndView("./partos/partos");
	}
	
	@GetMapping("/all")
	public List<Map<String, Object>> allPartos(@RequestParam(name = "fini") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fini, 
				@RequestParam(name = "ffin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date ffin,
				@RequestParam(name = "tipoParto") Long tipoParto,
				@RequestParam(name = "idEstablecimiento") int idEstablecimiento){
		//LOG.info("fechas:"+ fini+" "+ffin);
		List<Map<String, Object>> partos = partoService.getPartos(fini, ffin, tipoParto, idEstablecimiento);
		return partos;
	}
	
	@GetMapping("/all-partos")
	public List<Map<String, Object>> allPartos2(@RequestParam(name = "fini") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fini, 
			@RequestParam(name = "ffin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date ffin,
			@RequestParam(name = "idEstablecimiento") int idEstablecimiento){
		//LOG.info("fechas:"+ fini+" "+ffin);
		List<Map<String, Object>> partos = partoService.getPartosAll(fini, ffin, idEstablecimiento);
		return partos;
	}
	
	@PostMapping("/new")
	public ResponseEntity<Parto> addParto(@RequestBody Parto parto){
		parto = partoService.addParto(parto);
		return new ResponseEntity<Parto>(parto,HttpStatus.OK);
	}
	
}
