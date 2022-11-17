package com.tip.gestionBares.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tip.gestionBares.model.TicketProducto;
import com.tip.gestionBares.model.TicketProductoId;

@Repository
public interface TicketProductoRepository extends JpaRepository<TicketProducto, TicketProductoId>{
	
}
