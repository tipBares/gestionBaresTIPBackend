package com.tip.gestionBares.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tip.gestionBares.model.Mozo;

@Repository
public interface MozoRepository extends JpaRepository<Mozo, Long>{

}
