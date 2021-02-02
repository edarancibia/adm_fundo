package com.fundo.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fundo.entities.Animal;
import com.fundo.serviceImpl.AnimalServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes({"idusuario","role","username","rutusu","idestab","usernomestab"})
@RequestMapping("/animales")
public class AnimalController {
	
	private static final Log LOG = LogFactory.getLog(AnimalController.class);

	@Autowired
	public AnimalServiceImpl animalService;
	
	@GetMapping("/new")
	public ModelAndView nuevoAnimal(){
		return new ModelAndView("nuevoAnimal");
	}
	
	@GetMapping("/list")
	public ModelAndView viewAll() {
		return new ModelAndView("./animales/lista");
	}
	
	//INSERT NUEVO
	@PostMapping("/add")
	public ResponseEntity<?> addAnimal(@RequestBody Animal animal){
		animal = animalService.addAnimal(animal);
		return ResponseEntity.status(HttpStatus.CREATED).body(animal);
	}
	
	//LISTA POR CATEGORIA/ESTADO/ESTABLECIMIENTO
	@GetMapping("/list-by-cat-and-est/{idCategoria}/{estadoAnimal}/{idEstablecimiento}")
	public @ResponseBody List<Map<String, Object>> listByCategoriaAndEstadoAndEstablecimiento(@PathVariable int idCategoria, 
			@PathVariable Long estadoAnimal, @PathVariable int idEstablecimiento){
		List<Map<String, Object>> animales = animalService.getByCategoriaAndEstadoAndEstablecimiento(idCategoria, estadoAnimal, idEstablecimiento);
		return animales;
	}
	
	//LISTA ALL POR ESTABLECIMIENTO
	@GetMapping("/list-by-est/{idEstablecimiento}")
	public @ResponseBody List<Map<String, Object>> listAllByEstablecimiento(@PathVariable int idEstablecimiento){
		List<Map<String, Object>> animales = animalService.getAllByEstablecimiento(idEstablecimiento);
		return animales;
	}
	
	//GET LIST BY CATEGORIA Y ESTABLECIMIENTO
	@GetMapping("/get-by-cat-est/{idCategoria}/{idEstablecimiento}")
	public @ResponseBody List<Map<String, Object>> getByCategoriaAndEstablecimiento(@PathVariable int idCategoria,
			@PathVariable int idEstablecimiento){
		List<Map<String, Object>> animales = animalService.getByCategoriaAndEstablecimiento(idCategoria, idEstablecimiento);
		return animales;
	}
	
	//GET LISTA DE ANIMALES POR LOTES PARA AGISNAR
	@GetMapping("/get-by-lote-and-est/{idLote}/{idEstablecimiento}")
	public @ResponseBody List<Map<String, Object>> getByLoteAndEstablecimiento(@PathVariable int idLote,
			@PathVariable int idEstablecimiento){
		List<Map<String, Object>> animales = animalService.getByLoteAndEstablecimiento(idLote, idEstablecimiento);
		return animales;
	}
	
	//GET BY DIIO AND ESTABLECIMIENTO
	@GetMapping("/find/{diio}/{idEstablecimiento}")
	public ResponseEntity<?> getByDiioAndEstablecimiento(@PathVariable int diio, @PathVariable int idEstablecimiento){
		Animal animal = animalService.findAnimalByDiioAndIdEstablecimientoAndEstado(diio, idEstablecimiento, 1);
	
		if(animal == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Animal>(animal,HttpStatus.OK);
		}
	}
	
	//GET BY DIIO-CAT-EST PARA VALIDAR SI EXISTE TERNERO AL REGISTRAR PARTO
	@GetMapping("/find-by-cat/{diioCria}/{idCategoria}/{idEstablecimiento}")
	public ResponseEntity<?> getByDiioCatEst(@PathVariable int diioCria, @PathVariable Long idCategoria,
			@PathVariable int idEstablecimiento){
		Animal animal = animalService.findAnimalByDiioAndIdCategoriaAndIdEstablecimiento(diioCria, idCategoria, idEstablecimiento);
		
		if(animal == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Animal>(animal,HttpStatus.OK);
		}
	}
	
	//EDITAR
	@PutMapping("/{diio}/{idEstablecimiento}")
	public ResponseEntity<Animal> editAnimal(@PathVariable("diio") int diio,
			@PathVariable int idEstablecimiento){
		Animal animal = animalService.findAnimalByDiioAndIdEstablecimientoAndEstado(diio, idEstablecimiento, 1);
		
		 if(animal == null) {
			 return ResponseEntity.notFound().build();
		 }
		 
		 animal.setEstado(0);
		 animalService.addAnimal(animal);
		 return new ResponseEntity<Animal>(animal,HttpStatus.OK);
	}
	
	@PostMapping("/nuevo-edita/{diio}/{idEstablecimiento}")
	public ResponseEntity<?> addNuevoEdita(@RequestBody Animal animal, @PathVariable int diio,
			@PathVariable int idEstablecimiento){
		Animal animalBd = animalService.getUltimoRegistro(diio, idEstablecimiento);
		LOG.info("animal: "+ animalBd);
		if(animalBd == null) {
			return ResponseEntity.notFound().build();
			
		}
		
		Animal nuevoAnimal = new Animal();
		
		nuevoAnimal.setDiio(animal.getDiio());
		nuevoAnimal.setFecha_accion(new Date());
		nuevoAnimal.setFechaIncorporacion(animalBd.getFechaIncorporacion());
		nuevoAnimal.setFechaNac(animalBd.getFechaNac());
		nuevoAnimal.setIdCategoria(animal.getIdCategoria());
		nuevoAnimal.setIdEstadoAnimal(animal.getIdEstadoAnimal());
		nuevoAnimal.setIdRaza(animal.getIdRaza());
		nuevoAnimal.setIdEstablecimiento(idEstablecimiento);
		nuevoAnimal.setSexo(animalBd.getSexo());
		nuevoAnimal.setEstado(1);
		animalService.addAnimal(nuevoAnimal);
		return new ResponseEntity<Animal>(nuevoAnimal,HttpStatus.CREATED);
	}
	
}
