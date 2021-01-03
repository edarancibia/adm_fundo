package com.fundo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundo.entities.Usuario;
import com.fundo.repository.UsuarioRepository;
import com.fundo.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	public UsuarioRepository repository;

	@Override
	public Usuario addUsuario(Usuario usuario) {
		return repository.save(usuario);
	}

	@Override
	public Usuario findByEmailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

	@Override
	public Usuario findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	
}
