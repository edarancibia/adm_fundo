package com.fundo.controllers;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fundo.entities.TipoParto;
import com.fundo.serviceImpl.TipoPartoServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu","idestab","usernomestab"})
@RequestMapping("/tipo-parto")
public class TipoPartoController {

	@Autowired
	private TipoPartoServiceImpl tPartoService;
	
	private static final Log LOG = LogFactory.getLog(TipoPartoController.class);
	
	@GetMapping("/all2")
	public @ResponseBody List<TipoParto> getAll2(){
		List<TipoParto> tipos = tPartoService.findAll();
		return tipos;
	}
	
	@GetMapping("/all-todos")
	public @ResponseBody List<Map<Object, String>> getAllTodos(){		
		List<Map<Object, String>> tipos = tPartoService.getTipoParto2();
		return tipos;
	}
	
	@GetMapping("/all")
	public @ResponseBody List<Map<Object, String>> getAll(){
		List<Map<Object, String>> tipos = tPartoService.getTipoParto();
		return tipos;
	}
}
