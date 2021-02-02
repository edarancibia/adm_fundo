package com.fundo.service;

import com.fundo.entities.Usuario;

public interface UsuarioService {

	public abstract Usuario addUsuario(Usuario usuario);
	public abstract Usuario findByEmailAndPassword(String email, String password);
	public abstract Usuario findByEmail(String email);
	public abstract Usuario findByIdUsuario(Long idusuario);
}
