package com.tip.gestionBares.service;

import java.util.List;
import com.tip.gestionBares.dto.TicketDto;

public interface TicketService {

	public TicketDto create(TicketDto ticketDto);
	public List<TicketDto> delete(Long id);
	public List<TicketDto> findAll();
	public TicketDto applyDiscount(Long id, Integer porcentaje);

}
