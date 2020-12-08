package com.fundo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundo.entities.Pesaje;
import com.fundo.repository.PesajeRepository;
import com.fundo.service.PesajeService;

@Service
public class PesajeServiceImpl implements PesajeService {

	@Autowired
	private PesajeRepository pesajeRepository;
	
	@Override
	public Pesaje addPesaje(Pesaje pesaje) {
		return pesajeRepository.save(pesaje);
	}

	@Override
	public List<Pesaje> getAll() {
		return pesajeRepository.findAll();
	}

	@Override
	public List<Pesaje> getAllByFecha() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePesaje(Long idPesaje) {
		pesajeRepository.deleteById(idPesaje);
		
	}

	@Override
	public List<Map<String, Object>> getPesoGanado(Long diio) {
		return pesajeRepository.getPesoGanado(diio);
	}

	@Override
	public List<Map<String, Object>> getPesajeByCategoria(Long idCategoria, Date fini, Date ffin) {
		return pesajeRepository.getPesajeByCategoria(idCategoria, fini, ffin);
	}

	@Override
	public List<Map<String, Integer>> getPesajeGrafico(Long diio) {
		return pesajeRepository.getPesajeGrafico(diio);
	}

	@Override
	public Iterable<Map<Object, Integer>> getPesajeGrafico2(Long diio) {
		return pesajeRepository.getPesajeGrafico2(diio);
	}

	@Override
	public List<Map<String, Object>> getPesajeByCategoriaAll(Date fini, Date ffin) {
		return pesajeRepository.getPesajeByCategoriaAll(fini, ffin);
	}

}
