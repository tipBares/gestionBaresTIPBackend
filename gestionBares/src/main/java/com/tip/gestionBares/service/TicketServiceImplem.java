package com.tip.gestionBares.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tip.gestionBares.dto.TicketDto;
import com.tip.gestionBares.dto.TicketProductoDto;
import com.tip.gestionBares.model.Ticket;
import com.tip.gestionBares.model.TicketProducto;
import com.tip.gestionBares.repositories.TicketProductoRepository;
import com.tip.gestionBares.repositories.TicketRepository;

@Service
public class TicketServiceImplem implements TicketService{

	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private TicketProductoRepository ticketProductoRepository;
	
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
	public TicketDto agregarProducto(TicketProductoDto ticketProductoDto) {
		
		TicketProducto ticketProducto = new TicketProducto( ticketProductoDto.getIdTicket(), ticketProductoDto.getIdProducto(), ticketProductoDto.getCantidad());
			
		Ticket ticket = this.ticketRepository.findById(ticketProductoDto.getIdTicket()).orElse(null);

		Optional<TicketProducto> ticketProductoExist = ticket.getTicketProductos().stream().filter(x -> x.getIdProducto().equals(ticketProductoDto.getIdProducto())).findFirst();

		if( ticketProductoExist.isPresent()) {
			ticketProductoExist.get().setCantidad(ticketProductoDto.getCantidad() + ticketProductoExist.get().getCantidad());
			this.ticketProductoRepository.saveAndFlush(ticketProductoExist.get());
		}else {
			ticket.getTicketProductos().add(ticketProducto);
			this.ticketProductoRepository.saveAndFlush(ticketProducto);
		}
		
		TicketDto ticketDto = getTicketById(ticketProductoDto.getIdTicket());
		return ticketDto;
	}

	@Override
	public void generarImporteTotal(Long idTicket) {
		Ticket ticket = this.ticketRepository.findById(idTicket).orElse(null);
		Double importeTotal = ticket.getTicketProductos().stream().mapToDouble(ticketProducto -> ticketProducto.getCantidad() * ticketProducto.getProducto().getPrecio()).sum();
		ticket.setImporteTotal(importeTotal);
		this.ticketRepository.save(ticket);
	}

  @Override
	public List<TicketDto> findAll() {
		List<Ticket> tickets = (List<Ticket>) this.ticketRepository.findAll();
		List<TicketDto> ticketsDto = new ArrayList<TicketDto>();
		
		if(tickets != null) {
			tickets.forEach(t -> ticketsDto.add(new TicketDto(t)));
		}
		return ticketsDto;
	}

	@Override
	public TicketDto applyDiscount(Long id, Integer porcentaje) {
		Ticket ticket = this.ticketRepository.findById(id).orElse(null);
		Double importeTotal = (Double) (ticket.getImporteTotal() - (ticket.getImporteTotal() * porcentaje / 100));
		ticket.setDescuento(porcentaje);
		ticket.setImporteTotal(importeTotal);
		this.ticketRepository.save(ticket);
		TicketDto ticketDto = new TicketDto(ticket);
		return ticketDto;
	}

	@Override
	public TicketDto update(TicketDto ticketDto, Long id) {
		Ticket ticket = this.ticketRepository.findById(id).orElse(null);
		ticket.setMesa(ticketDto.getMesa());
		ticket.setMozo(ticketDto.getMozo());
		ticket.setNombreBar(ticketDto.getNombreBar());
		ticket.setDireccionBar(ticketDto.getDireccionBar());
		this.ticketRepository.save(ticket);
		return new TicketDto(ticket);
	}

	@Override
	public TicketDto getTicketById(Long id) {
		Optional<Ticket> ticket = this.ticketRepository.findById(id);
		if(ticket.isPresent()) {
			TicketDto ticketDto = new TicketDto(ticket.get());
			return ticketDto;
		} else {
			return null;
		}
	}

	@Override
	public Page<Ticket> findAllPag(Pageable pageable) {
		return this.ticketRepository.findAll(pageable);
	}

	@Override
	public Page<Ticket> findByDate(LocalDate fecha, Pageable pageable) {
		Page<Ticket> tickets = this.ticketRepository.findAllByFecha(fecha, pageable);
		return tickets;
	}

	

	

	

		
}
