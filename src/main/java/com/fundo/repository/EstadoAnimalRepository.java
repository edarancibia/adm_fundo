package com.fundo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fundo.entities.EstadoAnimal;

public interface EstadoAnimalRepository extends JpaRepository<EstadoAnimal, Long> {
	
	List<EstadoAnimal> findByIdEstablecimiento(int idestablecimiento);

}
