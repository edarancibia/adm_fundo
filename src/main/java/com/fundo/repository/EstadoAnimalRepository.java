package com.fundo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fundo.entities.EstadoAnimal;

public interface EstadoAnimalRepository extends JpaRepository<EstadoAnimal, Long> {

}
