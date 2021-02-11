package com.fundo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fundo.entities.CategoriaAnimal;

public interface CategoriaAnimalRepository extends JpaRepository<CategoriaAnimal, Long> {
	
	//OBTIENE CATEGORIAS POR DEFECTO MAS LAS DEL ESTABLECIMIENTO
	@Query(value = "select a.idCategoriaAnimal,a.descripcion from categoria_animal a where idestablecimiento in(0,:idEstablecimiento)", nativeQuery = true)
	List<Map<Object, String>> getCatByEstablecimiento(int idEstablecimiento);
	
	//RECIBE UN 0 COMO PARAMETRO PARA OBTENER LAS CATEGORIAS POR DEFECTO
	List<CategoriaAnimal> findByIdEstablecimiento(int idEstablecimiento);

	@Query(value = "select a.idCategoriaAnimal,a.descripcion from categoria_animal a where a.idCategoriaAnimal > 1 \r\n" +
	"order by a.idCategoriaAnimal desc",nativeQuery = true)
	List<Map<Object, String>> getCategoriasPesaje();
	
	@Query(value = "select a.idCategoriaAnimal,a.descripcion from categoria_animal a where a.idCategoriaAnimal > 1 \r\n" +
			"and a.idCategoriaAnimal <> 6",nativeQuery = true)
	List<Map<Object, String>> getCategorias();
	
	public abstract CategoriaAnimal findByIdCategoriaAnimal(Long idcategoria);
}
