package com.tip.gestionBares.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tip.gestionBares.model.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long>{

}
