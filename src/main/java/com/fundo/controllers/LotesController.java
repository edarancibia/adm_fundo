package com.fundo.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fundo.entities.Animal;
import com.fundo.entities.Lote;
import com.fundo.entities.LoteAnimal;
import com.fundo.serviceImpl.LoteAnimalServiceImpl;
import com.fundo.serviceImpl.LoteServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu","idestab","usernomestab"})
@RequestMapping("/lotes")
public class LotesController {

	private static final Log LOG = LogFactory.getLog(LotesController.class);
	
	@Autowired
	public LoteServiceImpl lotesService;
	
	@Autowired
	public LoteAnimalServiceImpl loteAnimalService;
	
	@GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("./lotes/lotes");
	}
	
	@GetMapping("/asignar")
	public ModelAndView asignar() {
		return new ModelAndView("./lotes/asignar");
	}
	
	//OBTIENE DATOS DEL LOTE
	@GetMapping("/find-by-id/{idLote}/{idEstablecimiento}")
	public ResponseEntity<?> getLoteByIdAndIdEstablecimiento(@PathVariable Long idLote,@PathVariable int idEstablecimiento){
		Lote lote = lotesService.findLoteByIdLoteAndIdEstablecimiento(idLote, idEstablecimiento);
		
		if(lote == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Lote>(lote,HttpStatus.OK);
		}
	}
	
	@GetMapping("/detalle/{idLote}/{idEstablecimiento}")
	public ModelAndView showDetalle(@PathVariable int idLote, @PathVariable int idEstablecimiento) {
		ModelAndView model = new ModelAndView();
		model.addObject("idLote", idLote);
		model.addObject("idEstablecimiento", idEstablecimiento);
		model.setViewName("./lotes/detalle_lote");
		return model;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Lote> addLote(@RequestBody Lote lote){
		if(lote != null) {
			lote = lotesService.addLote(lote);
		}
		return new ResponseEntity<Lote>(lote,HttpStatus.OK);
	}
	
	@GetMapping("by-establecimiento/{idEstablecimiento}")
	public List<Map<String, Object>> getLotesByEstablecimiento(@PathVariable int idEstablecimiento){
		List<Map<String, Object>> lotes = lotesService.getLotesByEstablecimiento(idEstablecimiento);
		return lotes;
	}
	
	@PutMapping("/{idLote}")
	public ResponseEntity<?> removeLote(@PathVariable Long idLote, @RequestBody Lote lote,@PathVariable int idEstablecimiento){
		Lote loteDb = this.lotesService.findLoteByIdLoteAndIdEstablecimiento(idLote, idEstablecimiento);
		
		if(loteDb == null) {
			return ResponseEntity.notFound().build();
		}
		
		loteDb.setDescripcion(lote.getDescripcion());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.lotesService.addLote(loteDb));
	}
	
	//OBTIENE ANIMALES POR LOTE Y ESTABLECIMIENTO
	@GetMapping("/animales-lote/{idLote}/{idEstablecimiento}")
	public List<?> getAnimalesByLoteAndEstablecimiento(@PathVariable Long idLote,@PathVariable int idEstablecimiento){
		List<Map<String, Object>> animalesLote = lotesService.getAnimalesByIdLoteAndIdEstablecimiento(idLote, idEstablecimiento);
		return animalesLote;
	}
	
	//OBTIENE LOTE POR DEFECTO PARA NUEVOS ANIMALES
	@GetMapping("/get-lote-defecto/{idEstablecimiento}")
	public List<?> getLoteDefecto(@PathVariable int idEstablecimiento){
		List<Map<String, Object>> lote = lotesService.getLoteDefecto(idEstablecimiento);
		return lote;
	}
	
	//VALIDA SI UN ANIMAL YA PERTENECE A UN LOTE
	@GetMapping("/valida-animal-lote/{idLote}/{diio}/{idEstablecimiento}")
	public ResponseEntity<?> validaLoteAnimal(@PathVariable int idLote, @PathVariable int diio,
			@PathVariable int idEstablecimiento){
		//List<Map<String, Object>> animalLote = lotesService.validaAnimalLote(idLote, diio, idEstablecimiento);
		return ResponseEntity.ok(lotesService.validaAnimalLote(idLote, diio, idEstablecimiento));
	}
	
	//AGREGA UN ANIMAL A UN LOTE
	@PostMapping("/add-animal-lote")
	public ResponseEntity<?> addAnimalLote(@RequestBody LoteAnimal loteAnimal){
		LOG.info("datos: "+ loteAnimal);
		loteAnimalService.addLoteAnimal(loteAnimal);
		return new ResponseEntity<LoteAnimal>(loteAnimal,HttpStatus.OK);
	}
	
	//DEJA LOS ANIMALES DE UN LOTE EN VIDGENTE=0 ANTES DE SER ASIGNADOS A UN NUEVO LOTE
	@PutMapping("/upd-vigente/{idEstablecimiento}")
	public ResponseEntity<?> uddateLotesVigente(@RequestBody List<String> checkIDs,
			@PathVariable int idEstablecimiento){
		LOG.info("arreglo: "+checkIDs);
		loteAnimalService.updateEstadoLoteAnimal(checkIDs, idEstablecimiento);
		return ResponseEntity.noContent().build();
	}
}
