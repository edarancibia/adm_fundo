package com.fundo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fundo.entities.Establecimiento;

@Repository
public interface EstablecimientoRepository extends JpaRepository<Establecimiento, Long> {

	@Query(value = "select e.idestablecimiento, e.descripcion from establecimiento e, usuario_establecimiento ue\r\n" + 
			"where e.idEstablecimiento = ue.idEstablecimiento and ue.idUsuario= :idUsuario", nativeQuery = true)
	List<java.util.Map<String, Object>> getEstablecimientosByUser(Long idUsuario);
	
	@Query(value = "select * from usuario_establecimiento u where u.idusuario = :idusuario", nativeQuery = true)
	List<Map<String, Object>> getIdcentroByUsuario(int idusuario);
}
