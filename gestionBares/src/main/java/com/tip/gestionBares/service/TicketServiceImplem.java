package com.tip.gestionBares.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<TicketDto> findByDate(LocalDate fecha) {
		List<Ticket> tickets = this.ticketRepository.findAllByFecha(fecha);
		List<TicketDto> ticketsDto = new ArrayList<TicketDto>();
		if(tickets != null) {
			tickets.forEach(ticket -> ticketsDto.add(new TicketDto(ticket)));
		}
		
		return ticketsDto;
	}
	
	@Override
	public TicketDto agregarProducto(TicketProductoDto ticketProductoDto) {
		
		TicketProducto ticketProducto = new TicketProducto(ticketProductoDto.getIdTicket(), ticketProductoDto.getIdProducto(), ticketProductoDto.getCantidad());
		
		System.out.println("SOY TICKET PRODUCTO" + ticketProducto.getIdProducto());
		//System.out.println("SOY TICKET PRODUCTO" + ticketProducto.getProducto().getNombre());
		
		
		Ticket ticket = this.ticketRepository.findById(ticketProductoDto.getIdTicket()).orElse(null);
		System.out.println("SOY EL TICKET PRODUCTO SIN FILLTROS" + ticket.getTicketProductos().stream().findFirst());

		Optional<TicketProducto> ticketProductoExist = ticket.getTicketProductos().stream().filter(x -> x.getIdProducto().equals(ticketProductoDto.getIdProducto())).findFirst();

		
		//ticketProducto.orElse(null)
		//ticketProducto.stream().forEach(t -> System.out.println(" SOY EL TICKET PRODUCTO " + t.toString()));
		System.out.println("SOY EL TICKET PRODUCTO EXISTENTE" + ticketProductoExist);
		if( ticketProductoExist.isPresent()) {
		System.out.println("SOY TICKET PRODUCTO DTO CANTIDAD" + ticketProductoDto.getCantidad());
		System.out.println("SOY TICKET PRODUCTO MODEL CANTIDAD" + ticketProducto.getCantidad());


			ticketProductoExist.get().setCantidad(ticketProductoDto.getCantidad() + ticketProducto.getCantidad());
		}else {
			ticket.getTicketProductos().add(ticketProducto);
		}
		//System.out.println("SOY EL TICKET" + ticket.getId());
		
		this.ticketProductoRepository.save(ticketProducto);
		this.ticketRepository.save(ticket);
		
		TicketDto ticketDto = new TicketDto(ticket);
		ticketDto.getTicketProductosDto().add(ticketProductoDto);
		System.out.println("SOY EL TICKET DTO"+ ticketDto.getTicketProductosDto());
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
		List<TicketDto> ticketsDto = new ArrayList<>();
		
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
		
}
