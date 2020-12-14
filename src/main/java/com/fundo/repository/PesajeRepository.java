package com.fundo.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fundo.entities.Pesaje;

public interface PesajeRepository extends JpaRepository<Pesaje, Long> {
	
	@Query(value = "select distinct p.peso\r\n" + 
			"from pesaje p where p.diio= :diio \r\n" + 
			"and p.fecha = (select max(fecha) from pesaje where diio=p.diio) and p.idestablecimiento = :idestablecimiento", nativeQuery = true)
	List<Map<String, Object>> getPesoGanado(Long diio, int idestablecimiento);
	
	@Query(value = "select  p.diio,p.peso,p.ganado,DATE_FORMAT(p.fecha,'%d-%m-%Y') as fecha,a.sexo\r\n" + 
			"from pesaje p,animal a\r\n" + 
			"where p.diio = a.diio and a.idCategoria= :idCategoria and p.fecha between :fini and :ffin and p.idestablecimiento = :idestablecimiento GROUP by p.diio,p.peso,p.ganado,fecha,a.sexo order by fecha desc", nativeQuery = true)
	List<Map<String, Object>> getPesajeByCategoria(Long idCategoria,Date fini, Date ffin, int idestablecimiento);
	
	@Query(value = "select  p.diio,p.peso,p.ganado,DATE_FORMAT(p.fecha,'%d-%m-%Y') as fecha,a.sexo\r\n" + 
			"from pesaje p,animal a\r\n" + 
			"where p.diio = a.diio and p.fecha between :fini and :ffin and p.idestablecimiento = :idestablecimiento GROUP by p.diio,p.peso,p.ganado,fecha,a.sexo order by fecha desc", nativeQuery = true)
	List<Map<String, Object>> getPesajeByCategoriaAll(Date fini, Date ffin,int idestablecimiento);
	
	@Query(value = "select DATE_FORMAT(p.fecha,'%d-%m-%Y') as fecha,p.peso from pesaje p where p.diio = :diio and p.idestablecimiento = :idestablecimiento",nativeQuery = true)
	List<Map<String, Integer>> getPesajeGrafico(Long diio, int idestablecimiento);
	
	@Query(value = "select DATE_FORMAT(p.fecha,'%d-%m-%Y') as fecha,p.peso from pesaje p where p.diio = :diio and p.idestablecimiento = :idestablecimiento",nativeQuery = true)
	Iterable<Map<Object, Integer>> getPesajeGrafico2(Long diio, int idestablecimiento);

}
