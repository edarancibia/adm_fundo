package com.fundo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fundo.entities.Establecimiento;

@Repository
public interface EstablecimientoRepository extends JpaRepository<Establecimiento, Long> {

	@Query(value = "select e.idestablecimiento, e.descripcion from establecimientos e, usuario_establecimientos ue\r\n" + 
			"where e.idEstablecimiento = ue.idEstablecimiento and ue.idUsuario= :idUsuario", nativeQuery = true)
	List<java.util.Map<String, Object>> getEstablecimientosByUser(Long idUsuario);
}
