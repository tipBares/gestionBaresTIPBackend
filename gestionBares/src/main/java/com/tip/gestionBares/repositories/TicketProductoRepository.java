package com.tip.gestionBares.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tip.gestionBares.model.TicketProducto;

@Repository
public interface TicketProductoRepository extends CrudRepository<TicketProducto, Long>{

}
