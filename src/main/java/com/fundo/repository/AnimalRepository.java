package com.fundo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fundo.entities.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

	public abstract Animal findAnimalByDiioAndIdEstablecimientoAndEstado(int diio, int idEstablecimiento, int estado);
	
	public abstract Animal findAnimalByDiioAndIdCategoriaAndIdEstablecimiento(int diio, Long idCategoria, int idEstablecimiento);

	//obtiene ultimo registro para replicar en nuevo aminal luego de actualizar
	@Query(value = "select * from animal a2  where a2.diio = :diio and a2.idestablecimiento = :idestablecimiento \r\n"
			+ "ORDER  by a2.idanimal DESC LIMIT 1", nativeQuery = true)
	public Animal getUltimoRegistro(int diio, int idestablecimiento);
	
	@Query(value = "select DISTINCT a.diio,r.descripcion as raza,ca.descripcion as categoria,ea.descripcion as estadoAnimal ,\r\n" + 
			"			if((SELECT  DATEDIFF( t.fecha_hasta , t.fecha_desde) \r\n" + 
			"				from tratamiento t  where t.diio = a.diio and t.fecha_hasta >= NOW()\r\n" + 
			"				order by fecha_hasta desc limit 1 ) is null,'No',\r\n" + 
			"				(SELECT  DATEDIFF( t.fecha_hasta , t.fecha_desde) \r\n" + 
			"				from tratamiento t  where t.diio = a.diio and t.fecha_hasta >= NOW()\r\n" + 
			"	order by fecha_hasta desc limit 1 ))as resguardo \r\n" + 
			"				from animal a\r\n" + 
			"				left join tratamiento t2  on a.diio = t2.diio \r\n" + 
			"				left join raza r on a.idraza = r.idRaza \r\n" + 
			"				LEFT JOIN categoria_animal ca on a.idCategoria = ca.idCategoriaAnimal \r\n" + 
			"				LEFT JOIN estado_animal ea on a.idestado = ea.idEstadoAnimal \r\n" + 
			"				where a.idestado=:estadoAnimal and  \r\n" + 
			"				a.idcategoria=:idCategoria and a.idestablecimiento=:idEstablecimiento \r\n" + 
			"				and a.estado=1", nativeQuery = true)
List<Map<String, Object>> getByCategoriaAndEstadoAndEstablecimiento(int idCategoria,Long estadoAnimal,int idEstablecimiento);
	
	@Query(value = "select a.diio,r.descripcion as raza,ca.descripcion as categoria,ea.descripcion as estadoAnimal ,\r\n" + 
			"			if((SELECT  DATEDIFF( t.fecha_hasta , t.fecha_desde) \r\n" + 
			"				from tratamiento t  where t.diio = a.diio and t.fecha_hasta >= NOW()\r\n" + 
			"			order by fecha_hasta desc limit 1 ) is null,'No',\r\n" + 
			"			(SELECT  DATEDIFF( t.fecha_hasta , t.fecha_desde) \r\n" + 
			"				from tratamiento t  where t.diio = a.diio and t.fecha_hasta >= NOW()\r\n" + 
			"			order by fecha_hasta desc limit 1 )\r\n" + 
			"			)as resguardo\r\n" + 
			"			from animal a\r\n" + 
			"			left join tratamiento t on a.diio = t.diio \r\n" + 
			"			left join raza r on a.idraza = r.idRaza \r\n" + 
			"			left join categoria_animal ca on a.idcategoria = ca.idCategoriaAnimal \r\n" + 
			"			left join estado_animal ea on a.idestado = ea.idEstadoAnimal \r\n" + 
			"			where \r\n" + 
			"			a.idestablecimiento=:idEstablecimiento\r\n" + 
			"			and a.estado=1", nativeQuery = true)
	List<Map<String, Object>> getAllByEstablecimiento(int idEstablecimiento);
	
	
	//LISTA POR CATEGORIA Y ESTABLECIMIENTO PARA ASIGNAR A LOTES
	@Query(value = "select a.diio,r.descripcion as raza,ca.descripcion as categoria,ea.descripcion as estadoAnimal,\r\n" + 
			"			IF(l.descripcion is null,'Sin Lote',l.descripcion )as lote,  \r\n" + 
			"			a.sexo as sexo,DATE_FORMAT(la.fecha_accion,'%d-%m-%Y') as fecha \r\n" + 
			"			from animales a\r\n" + 
			"			left join lote_animales la on a.diio  = la.idAnimal \r\n" + 
			"			LEFT join raza r on r.idRaza = a.idraza \r\n" + 
			"			LEFT  join categoria_animal ca on ca.idCategoriaAnimal  = a.idCategoria\r\n" + 
			"			LEFT JOIN  estado_animal ea on ea.idEstadoAnimal = a.idestado \r\n" + 
			"			LEFT JOIN lotes l on l.idLote = la.idLote \r\n" + 
			"			where \r\n" + 
			"			a.idcategoria=:idCategoria and a.idestablecimiento=:idEstablecimiento    \r\n" + 
			"			and a.estado=1", nativeQuery = true)
	List<Map<String, Object>> getByCategoriaAndEstablecimiento(int idCategoria,int idEstablecimiento);
	
	//LISTA DE ANIMALES POR LOTE, PARA ASIGNAR LOTE
	@Query(value = "select \r\n" + 
			"a.diio ,la.idLote ,ca.descripcion as categoria,DATE_FORMAT(la.fecha_accion,'%d-%m-%Y') as fecha, \r\n" + 
			"a.sexo \r\n" +
			"from animales a \r\n" + 
			"left join lote_animales la on a.diio =la.idAnimal\r\n" + 
			"left join categoria_animal ca on a.idcategoria = ca.idCategoriaAnimal \r\n" + 
			"where la.vigente = 1 and la.idLote =:idLote and a.idestablecimiento =:idEstablecimiento and la.idestablecimiento =:idEstablecimiento", nativeQuery = true)
	List<Map<String, Object>> getByLoteAndEstablecimiento(int idLote, int idEstablecimiento);
	
}
