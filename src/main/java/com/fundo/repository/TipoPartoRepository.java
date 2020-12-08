package com.fundo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fundo.entities.TipoParto;

public interface TipoPartoRepository extends JpaRepository<TipoParto, Long>{

	@Query(value = "select a.idTipoParto,a.descripcion from tipo_parto a where a.idTipoParto not in(6)",nativeQuery = true)
	List<Map<Object, String>> getTipoParto();
	
	@Query(value = "select a.idTipoParto,a.descripcion from tipo_parto a order by a.idTipoParto asc",nativeQuery = true)
	List<Map<Object, String>> getTipoParto2();
}
