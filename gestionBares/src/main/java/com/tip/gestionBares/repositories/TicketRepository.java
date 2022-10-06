package com.tip.gestionBares.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tip.gestionBares.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
	public List<Ticket> findAllByFechaCreacion(Date fecha);

}
