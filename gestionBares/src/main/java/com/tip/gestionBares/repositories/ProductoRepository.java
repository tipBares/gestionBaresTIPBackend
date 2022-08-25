package com.tip.gestionBares.repositories;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tip.gestionBares.model.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, UUID>{
	@Query("FROM Producto p WHERE p.nombre LIKE :nombre%")
	public List<Producto> findByNombre(@Param("nombre") String nombre);
}
