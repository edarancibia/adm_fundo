package com.fundo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fundo.entities.UsuarioEstablecimiento;

@Repository
public interface UsuarioEstabRepository extends JpaRepository<UsuarioEstablecimiento, Long> {

	List<UsuarioEstablecimiento> findByIdusuario(int idusuario);
	
}
