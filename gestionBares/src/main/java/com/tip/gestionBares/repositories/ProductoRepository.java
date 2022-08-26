package com.tip.gestionBares.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tip.gestionBares.model.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long>{

	public List<Producto> findByCategoria(String categoria);



}
