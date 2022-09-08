package com.tip.gestionBares.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tip.gestionBares.model.Mozo;

@Repository
public interface MozoRepository extends CrudRepository<Mozo, Long>{

}
