package com.tip.gestionBares.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.gestionBares.dto.TicketDto;
import com.tip.gestionBares.model.Ticket;
import com.tip.gestionBares.repositories.TicketRepository;

@Service
public class TicketServiceImplem implements TicketService{

	@Autowired
	private TicketRepository ticketRepository;
	
	public TicketServiceImplem() {
		
	}

	@Override
	public TicketDto create(TicketDto ticketDto) {
		Ticket ticket = new Ticket(ticketDto.getMesa(), ticketDto.getMozo(), ticketDto.getFecha(), 
							ticketDto.getNombreBar(), ticketDto.getDireccionBar(),
							ticketDto.getImporteTotal(), ticketDto.getMetodoDePago());
		this.ticketRepository.save(ticket);
		return new TicketDto(ticket);
	}
	
}
