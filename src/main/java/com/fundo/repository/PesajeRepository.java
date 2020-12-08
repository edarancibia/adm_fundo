package com.fundo.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fundo.entities.Pesaje;

public interface PesajeRepository extends JpaRepository<Pesaje, Long> {
	
	@Query(value = "select p.peso\r\n" + 
			"from pesaje p where p.diio= :diio \r\n" + 
			"and p.fecha = (select max(fecha) from pesaje where diio=p.diio)", nativeQuery = true)
	List<Map<String, Object>> getPesoGanado(Long diio);
	
	@Query(value = "select  p.diio,p.peso,p.ganado,DATE_FORMAT(p.fecha,'%d-%m-%Y') as fecha,a.sexo\r\n" + 
			"from pesaje p,animales a\r\n" + 
			"where p.diio = a.diio and a.idCategoria= :idCategoria and p.fecha between :fini and :ffin", nativeQuery = true)
	List<Map<String, Object>> getPesajeByCategoria(Long idCategoria,Date fini, Date ffin);
	
	@Query(value = "select  p.diio,p.peso,p.ganado,DATE_FORMAT(p.fecha,'%d-%m-%Y') as fecha,a.sexo\r\n" + 
			"from pesaje p,animales a\r\n" + 
			"where p.diio = a.diio and p.fecha between :fini and :ffin", nativeQuery = true)
	List<Map<String, Object>> getPesajeByCategoriaAll(Date fini, Date ffin);
	
	@Query(value = "select DATE_FORMAT(p.fecha,'%d-%m-%Y') as fecha,p.peso from pesaje p where p.diio = :diio",nativeQuery = true)
	List<Map<String, Integer>> getPesajeGrafico(Long diio);
	
	@Query(value = "select DATE_FORMAT(p.fecha,'%d-%m-%Y') as fecha,p.peso from pesaje p where p.diio = :diio",nativeQuery = true)
	Iterable<Map<Object, Integer>> getPesajeGrafico2(Long diio);

}
