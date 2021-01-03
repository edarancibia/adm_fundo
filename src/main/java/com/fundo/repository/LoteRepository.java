package com.fundo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fundo.entities.Lote;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long> {

	@Query(value = "select l.idLote,l.descripcion from lote l, establecimiento e\r\n" + 
			"where l.idEstablecimiento = e.idEstablecimiento and l.idEstablecimiento=:idEstablecimiento \r\n"+
			" and l.vigente=1",nativeQuery = true)
	List<Map<String, Object>> getLotesByEstablecimiento(int idEstablecimiento);
	
	public abstract Lote findLoteByIdLoteAndIdEstablecimiento(Long idLote,int idEstablecimiento);
	
	//OBTIENE ANIMALES X LOTE
	@Query(value = "SELECT la.idAnimal as Diio, c.descripcion as categoria, DATE_FORMAT(la.fecha_accion,'%d-%m-%Y') as fecha\r\n" + 
			"FROM lote_animal la, animal a, categoria_animal c\r\n" + 
			"where la.idLote = :idLote and la.idAnimal = a.diio and \r\n" + 
			"la.vigente = 1 and a.idcategoria = c.idCategoriaAnimal and la.id_establecimiento = :idEstablecimiento", nativeQuery = true)
	List<Map<String, Object>> getAnimalesByIdLoteAndIdEstablecimiento(Long idLote, int idEstablecimiento);
	
	//VALIDA SI UN ANIMAL YA PERTENECE A UN LOTE
	@Query(value = "select la.idLote,la.idAnimal,l.descripcion as lote from lote_animal la, lote l \r\n"
			+ "where la.idLote = l.idLote and la.idLote = :idLote and la.idAnimal = :diio and \r\n"
			+ " la.id_establecimiento = :idEstablecimiento and la.vigente = 1", nativeQuery = true)
	List<Map<String, Object>> validaAnimalLote(int idLote, int diio, int idEstablecimiento);
	
	
	//OBTIENE LOTE POR DEFECTO PARA ASIGNAR NUEVOS ANIMALES
	@Query(value = "select l.idLote,l.descripcion from lote l where l.descripcion ='Sin Lote' and l.idEstablecimiento =:idEstablecimiento", nativeQuery = true)
	List<Map<String, Object>> getLoteDefecto(int idEstablecimiento);
	
}
