package com.fundo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fundo.entities.LoteAnimal;

@Repository
public interface LoteAnimalRepository extends JpaRepository<LoteAnimal, Long> {
	
	//deja los animales seleccionados en estado cero antes de ser asignados a un nuevo lote
	@Modifying
	@Query(value = "update lote_animal set vigente = 0 where diio in(:checkIDs) and id_establecimiento=:idEstablecimiento",nativeQuery = true)
	public void updateEstadoLoteAnimal(List<String> checkIDs, int idEstablecimiento);

}
