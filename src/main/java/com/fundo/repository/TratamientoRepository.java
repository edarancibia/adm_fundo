package com.fundo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fundo.entities.Tratamiento;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
	
	@Query(value = "select a.diio,r.descripcion as raza,DATE_FORMAT(t.fecha,'%d-%m-%Y') as fecha,ca.descripcion as categoria,ea.descripcion as estadoAnimal ,\n"
			+ "			t.descripcion  as tratamiento,\n"
			+ "			if((SELECT  DATEDIFF( t.fecha_hasta , t.fecha_desde) \n"
			+ "				from tratamiento t  where t.diio = a.diio and t.fecha_hasta >= NOW()\n"
			+ "			order by fecha_hasta desc limit 1 ) is null,'No',\n"
			+ "			(SELECT  DATEDIFF( t.fecha_hasta , t.fecha_desde) \n"
			+ "				from tratamiento t  where t.diio = a.diio and t.fecha_hasta >= NOW()\n"
			+ "			order by fecha_hasta desc limit 1 )\n"
			+ "			)as resguardo\n"
			+ "			from animal a\n"
			+ "			left join tratamiento t on a.diio = t.diio \n"
			+ "			left join raza r on a.idraza = r.idRaza \n"
			+ "			left join categoria_animal ca on a.idcategoria = ca.idCategoriaAnimal \n"
			+ "			left join estado_animal ea on a.idestado = ea.idEstadoAnimal \n"
			+ "			where \n"
			+ "			a.idestablecimiento= :idEstablecimiento  and a.diio = :diio \n"
			+ "			and a.estado=1 order by fecha_hasta desc limit 1", nativeQuery = true)
	List<Map<Object, String>> findTratamientoByDiioAndIdEstablecimiento(int diio, int idEstablecimiento);

}
