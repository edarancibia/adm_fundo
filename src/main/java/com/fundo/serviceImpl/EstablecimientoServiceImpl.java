package com.fundo.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundo.entities.Establecimiento;
import com.fundo.entities.UsuarioEstablecimiento;
import com.fundo.repository.EstablecimientoRepository;
import com.fundo.repository.UsuarioEstabRepository;
import com.fundo.service.EstablecimientoService;

@Service
public class EstablecimientoServiceImpl implements EstablecimientoService {

	@Autowired
	public EstablecimientoRepository repository;

	@Override
	public Establecimiento addEstablecimiento(Establecimiento establecimiento) {
		return repository.save(establecimiento);
	}

	@Override
	public void deleteEstablecimiento(Long idEstablecimiento) {
		Optional<Establecimiento> establecimiento = repository.findById(idEstablecimiento);
		if(establecimiento != null) {
			repository.deleteById(idEstablecimiento);
		}	
		
	}

	@Override
	public List<Map<String, Object>> getEstablecimientosByUser(Long idUsuario) {
		return repository.getEstablecimientosByUser(idUsuario);
	}

	@Override
	public List<Map<String, Object>> getIdcentroByUsuario(int idusuario) {
		return repository.getIdcentroByUsuario(idusuario);
	}

	@Override
	public Establecimiento fingByIdestablecimiento(Long idestablecimiento) {
		return repository.getOne(idestablecimiento);
	}


}
