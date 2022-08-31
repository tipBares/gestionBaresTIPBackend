package com.tip.gestionBares.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.gestionBares.dto.TicketDto;
import com.tip.gestionBares.model.Producto;
import com.tip.gestionBares.model.Ticket;
import com.tip.gestionBares.repositories.ProductoRepository;
import com.tip.gestionBares.repositories.TicketRepository;

@Service
public class TicketServiceImplem implements TicketService{

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	public TicketServiceImplem() {
		
	}

	@Override
	public TicketDto create(TicketDto ticketDto) {
		Ticket ticket = new Ticket(ticketDto.getMesa(), ticketDto.getMozo(), 
							ticketDto.getNombreBar(), ticketDto.getDireccionBar()
							);
		this.ticketRepository.save(ticket);
		return new TicketDto(ticket);
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

	@Override
	public List<TicketDto> findByDate(LocalDate fecha) {
		List<Ticket> tickets = this.ticketRepository.findAllByFecha(fecha);
		List<TicketDto> ticketsDto = new ArrayList<TicketDto>();
		if(tickets != null) {
			tickets.forEach(ticket -> ticketsDto.add(new TicketDto(ticket)));
		}
		
		return ticketsDto;
	}
	
	@Override
	public TicketDto agregarProducto(Long idTicket, Long idProducto) {
		Ticket ticket = this.ticketRepository.findById(idTicket).orElse(null);
		Producto producto = this.productoRepository.findById(idProducto).orElse(null);
		TicketDto ticketDto = new TicketDto(ticket);
		ticketDto.getProductos().add(producto);
		this.ticketRepository.save(ticket);
		return ticketDto;
	}

	@Override
	public void generarImporteTotal(Long idTicket) {
		Ticket ticket = this.ticketRepository.findById(idTicket).orElse(null);
		Double importeTotal = ticket.getProductos().stream().mapToDouble(producto -> producto.getPrecio()).sum();
		ticket.setImporteTotal(importeTotal);
		this.ticketRepository.save(ticket);
	}

	
	
}
