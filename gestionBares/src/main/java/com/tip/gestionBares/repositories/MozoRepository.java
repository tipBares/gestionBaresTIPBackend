package com.tip.gestionBares.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tip.gestionBares.model.Mesa;
import com.tip.gestionBares.model.Mozo;

@Repository
public interface MozoRepository extends CrudRepository<Mozo, UUID>{

}
