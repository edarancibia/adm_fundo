package com.fundo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fundo.entities.Raza;

public interface RazaRepository extends JpaRepository<Raza, Long>{
	
	List<Raza> findByIdEstablecimiento(int idestablecimiento);

}
