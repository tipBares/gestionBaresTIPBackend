package com.tip.gestionBares.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.tip.gestionBares.model.Mesa;
import com.tip.gestionBares.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, UUID>{

}
