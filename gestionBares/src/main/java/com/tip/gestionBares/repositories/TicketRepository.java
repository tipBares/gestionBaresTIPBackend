package com.tip.gestionBares.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tip.gestionBares.model.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long>{
	public List<Ticket> findAllByFecha(LocalDate fecha);
}
