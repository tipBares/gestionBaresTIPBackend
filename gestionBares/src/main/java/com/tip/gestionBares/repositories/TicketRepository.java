package com.tip.gestionBares.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tip.gestionBares.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
	public Page<Ticket> findAllByFecha(LocalDate fecha, Pageable pageable);
	

}
