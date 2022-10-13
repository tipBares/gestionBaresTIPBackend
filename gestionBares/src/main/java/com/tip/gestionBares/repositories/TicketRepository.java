package com.tip.gestionBares.repositories;


import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tip.gestionBares.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

	@Query(value = "from Ticket t where fechaCreacion BETWEEN :startDate AND :endDate")
	public Page<Ticket> findByFechaCreacion(@Param("startDate")Date startDate,@Param("endDate")Date endDate, Pageable pageable);
	public Page<Ticket> findByEnProceso(Boolean enProceso, Pageable pageable);

}
