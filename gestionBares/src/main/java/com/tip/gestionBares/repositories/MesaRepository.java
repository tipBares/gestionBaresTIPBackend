package com.tip.gestionBares.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tip.gestionBares.model.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long>{

}
