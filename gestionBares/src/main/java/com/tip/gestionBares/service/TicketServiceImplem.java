package com.tip.gestionBares.service;

import java.util.ArrayList;
import java.util.List;

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
	public List<TicketDto> delete(Long id) {
		List<Ticket> tickets = (List<Ticket>) this.ticketRepository.findAll();
		Ticket ticket = tickets.stream().filter(p -> p.getId().equals(id)).findAny().orElse(null);
		tickets.removeIf(c -> c.getId().equals(id));
		List<TicketDto> ticketsDto = new ArrayList<TicketDto>();
		if(tickets != null) {
			tickets.forEach(p -> ticketsDto.add(new TicketDto(p)));
		}
		this.ticketRepository.delete(ticket);
		
		return ticketsDto;
	}

}
