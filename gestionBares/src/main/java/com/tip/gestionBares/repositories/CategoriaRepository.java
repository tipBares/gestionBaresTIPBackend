package com.tip.gestionBares.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tip.gestionBares.model.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

}
