package com.fundo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundo.entities.UsuarioEstablecimiento;
import com.fundo.repository.UsuarioEstabRepository;
import com.fundo.service.UsuarioEstabService;

@Service
public class UsuarioEstbSerciceImpl implements UsuarioEstabService {

	@Autowired
	public UsuarioEstabRepository reposotory;
	

	@Override
	public UsuarioEstablecimiento addUserEstab(UsuarioEstablecimiento userEstab) {
		return reposotory.save(userEstab);
	}

	@Override
	public List<UsuarioEstablecimiento> findByIdusuario(int idusuario) {
		return reposotory.findByIdusuario(idusuario);
	}

}
