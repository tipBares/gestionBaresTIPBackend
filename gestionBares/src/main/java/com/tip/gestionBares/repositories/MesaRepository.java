package com.tip.gestionBares.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tip.gestionBares.model.Mesa;

@Repository
public interface MesaRepository extends CrudRepository<Mesa, Long>{

}
