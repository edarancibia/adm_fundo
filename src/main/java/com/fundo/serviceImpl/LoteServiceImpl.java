package com.fundo.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundo.entities.Lote;
import com.fundo.repository.LoteRepository;
import com.fundo.service.LoteService;

@Service
public class LoteServiceImpl implements LoteService {

	@Autowired
	public LoteRepository loteRepository;

	@Override
	public void deleteLote(Long idLote) {
		Optional<Lote> lote = loteRepository.findById(idLote);
		if(lote != null) {
			loteRepository.deleteById(idLote);
		}
	}

	@Override
	public List<Map<String, Object>> getLotesByEstablecimiento(int idEstablecimiento) {
		return loteRepository.getLotesByEstablecimiento(idEstablecimiento);
	}

	
	@Override
	public Lote addLote(Lote lote) {
		return loteRepository.save(lote);
	}

	@Override
	public Lote findLoteByIdLoteAndIdEstablecimiento(Long idLote, int idEstablecimiento) {
		return loteRepository.findLoteByIdLoteAndIdEstablecimiento(idLote, idEstablecimiento);
	}

	@Override
	public List<Map<String, Object>> getAnimalesByIdLoteAndIdEstablecimiento(Long idLote, int idEstablecimiento) {
		return loteRepository.getAnimalesByIdLoteAndIdEstablecimiento(idLote, idEstablecimiento);
	}

	@Override
	public List<Map<String, Object>> validaAnimalLote(int idLote, int diio, int idEstablecimiento) {
		return loteRepository.validaAnimalLote(idLote, diio, idEstablecimiento);
	}

	@Override
	public List<Map<String, Object>> getLoteDefecto(int idEstablecimiento) {
		return loteRepository.getLoteDefecto(idEstablecimiento);
	}

}
