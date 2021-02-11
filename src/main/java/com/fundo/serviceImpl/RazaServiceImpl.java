package com.fundo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundo.entities.Raza;
import com.fundo.repository.RazaRepository;
import com.fundo.service.RazaService;

@Service
public class RazaServiceImpl implements RazaService {

	@Autowired
	public RazaRepository razaRepository;
	
	@Override
	public List<Raza> findAll() {
		return razaRepository.findAll();
	}

	@Override
	public List<Raza> findByIdEstablecimiento(int idestablecimiento) {
		return razaRepository.findByIdEstablecimiento(idestablecimiento);
	}

	@Override
	public Raza addRaza(Raza raza) {
		return razaRepository.save(raza);
	}
}
