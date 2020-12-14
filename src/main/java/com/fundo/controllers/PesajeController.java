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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fundo.entities.Pesaje;
import com.fundo.serviceImpl.PesajeServiceImpl;

@RestController
@RequestMapping("/pesaje")
public class PesajeController {

	private static final Log LOG = LogFactory.getLog(PesajeController.class);
	
	@Autowired
	private PesajeServiceImpl pesajeService;
	
	@GetMapping("/")
	public ModelAndView viewAll() {
		return new ModelAndView("./pesaje/pesaje");
	}
	
	@GetMapping("/chart/{diio}/{idestablecimiento}")
	public ModelAndView viewGrafico(@PathVariable Long diio,@PathVariable int idestablecimiento) {
		ModelAndView dato = new ModelAndView();
		dato.addObject("diio",diio);
		dato.setViewName("./pesaje/grafico");
		return dato;
		//return new ModelAndView("./pesaje/grafico");
	}
	
	@PostMapping("/add")
	public ResponseEntity<Pesaje> addPesaje(@RequestBody Pesaje pesaje){
		pesaje = pesajeService.addPesaje(pesaje);
		return new ResponseEntity<Pesaje>(pesaje,HttpStatus.OK);
	}
	
	//obtiene ultimo peso para calcular los kg ganados
	@GetMapping("/get-ultimo/{diio}/{idestablecimiento}")
	public List<Map<String, Object>> getUltPeso(@PathVariable Long diio, @PathVariable int idestablecimiento){
		List<Map<String, Object>> ultPeso = pesajeService.getPesoGanado(diio, idestablecimiento);
		return ultPeso;
	}
	
	//obtiene pesajes por categoria
	@GetMapping("/pesajes-categoria/{idCategoria}/{idestablecimiento}")
	public List<Map<String, Object>> getPesajesBycategoria(@PathVariable Long idCategoria,
			@PathVariable int idestablecimiento,
			@RequestParam(name = "fini") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fini, 
			@RequestParam(name = "ffin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date ffin){
		LOG.info("fini:" + fini + " ffin: "+ffin);
		List<Map<String, Object>> pesajes = pesajeService.getPesajeByCategoria(idCategoria,fini,ffin, idestablecimiento);
		return pesajes;
	}
	
	//obtiene pesajes por categoria
	@GetMapping("/pesajes-categoria-todos/{idestablecimiento}")
	public List<Map<String, Object>> getPesajesBycategoriaAll(
			@PathVariable int idestablecimiento,
			@RequestParam(name = "fini") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fini, 
			@RequestParam(name = "ffin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date ffin){
		List<Map<String, Object>> pesajes = pesajeService.getPesajeByCategoriaAll(fini, ffin, idestablecimiento);
		return pesajes;
	}
	
	//genera info para grafico
	@GetMapping("/grafico-peso/{diio}/{idestablecimiento}")
	public ResponseEntity<Iterable<Map<Object, Integer>>> generaGrafico(@PathVariable Long diio,
			@PathVariable int idestablecimiento) {
		return new ResponseEntity<Iterable<Map<Object,Integer>>>(pesajeService.getPesajeGrafico2(diio,idestablecimiento),HttpStatus.OK);
	}
	
}
