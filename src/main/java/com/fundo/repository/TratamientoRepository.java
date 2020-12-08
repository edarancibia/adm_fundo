package com.fundo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fundo.entities.Tratamiento;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
	
	@Query(value = "SELECT  t.diio ,DATE_FORMAT(t.fecha_desde,'%d-%m-%Y') as fecha ,t.descripcion ,DATEDIFF( t.fecha_hasta , t.fecha_desde) as resguardo \r\n" + 
			" from tratamiento t \r\n" + 
			" where t.diio = :diio and t.idestablecimiento = :idEstablecimiento ", nativeQuery = true)
	List<Map<Object, String>> findTratamientoByDiioAndIdEstablecimiento(int diio, int idEstablecimiento);

}
