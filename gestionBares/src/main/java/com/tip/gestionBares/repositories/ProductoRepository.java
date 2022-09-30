package com.tip.gestionBares.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tip.gestionBares.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

	@Query("FROM Producto p WHERE p.nombre LIKE :nombre%")
	public Page<Producto> findByNombre(@Param("nombre") String nombre, Pageable pageable);
	public List<Producto> findByCategoria(String categoria);
}
