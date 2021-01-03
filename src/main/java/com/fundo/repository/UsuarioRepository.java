package com.fundo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fundo.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public abstract Usuario findByEmailAndPassword(String email, String password);
	public abstract Usuario findByEmail(String email);
}
