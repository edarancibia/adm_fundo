package com.fundo.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fundo.entities.Parto;

public interface PartoRepository extends JpaRepository<Parto, Long> {

	@Query(value = "select distinct DATE_FORMAT(p.fecha,'%d-%m-%Y') as fecha,p.fecha as fech, p.diio_vaca as vaca,p.diio_ternero as ternero,r.descripcion as raza, t.descripcion as tipo,\r\n" + 
			"			case a.sexo\r\n" + 
			"				when 1 then 'Macho'\r\n" + 
			"			else 'Hembra' \r\n" + 
			"            end as sexo\r\n" + 
			"from parto p, animal a, raza r, tipo_parto t\r\n" + 
			"where p.tipo_parto =:tipoParto and p.idestablecimiento = :idEstablecimiento and\r\n" + 
			"p.diio_ternero = a.diio and a.idraza = r.idRaza and\r\n" + 
			"p.tipo_parto = t.idtipoParto and a.idcategoria = 7 and\r\n" +
			"p.fecha between :fini and :ffin order by p.fecha", nativeQuery = true)
	List<Map<String, Object>> getPartos(Date fini, Date ffin,Long tipoParto, int idEstablecimiento);
	
	@Query(value = "select distinct DATE_FORMAT(p.fecha,'%d-%m-%Y') as fecha,p.fecha as fech, p.diio_vaca as vaca,p.diio_ternero as ternero,r.descripcion as raza, t.descripcion as tipo,\r\n" + 
			"			case a.sexo\r\n" + 
			"				when 1 then 'Macho'\r\n" + 
			"			else 'Hembra' \r\n" + 
			"            end as sexo\r\n" + 
			"from parto p, animal a, raza r, tipo_parto t\r\n" + 
			"where p.idestablecimiento = :idEstablecimiento and\r\n" + 
			"p.diio_ternero = a.diio and a.idraza = r.idRaza and\r\n" + 
			"p.tipo_parto = t.idtipoParto and a.idcategoria = 7 and\r\n" +
			"p.fecha between :fini and :ffin order by p.fecha",nativeQuery = true)
	List<Map<String, Object>> getPartosAll(Date fini, Date ffin, int idEstablecimiento);
}
