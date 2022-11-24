package com.tip.gestionBares.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tip.gestionBares.dto.TicketDto;
import com.tip.gestionBares.dto.TicketProductoDto;
import com.tip.gestionBares.model.Mesa;
import com.tip.gestionBares.model.Mozo;
import com.tip.gestionBares.model.Ticket;
import com.tip.gestionBares.model.TicketProducto;
import com.tip.gestionBares.repositories.MesaRepository;
import com.tip.gestionBares.repositories.MozoRepository;
import com.tip.gestionBares.repositories.TicketProductoRepository;
import com.tip.gestionBares.repositories.TicketRepository;

@Service
public class TicketServiceImplem implements TicketService{

	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private TicketProductoRepository ticketProductoRepository;
	@Autowired
	private MesaRepository mesaRepository;
	@Autowired
	private MozoRepository mozoRepository;
	public TicketServiceImplem() {
		
	}

	@Override
	public TicketDto create(TicketDto ticketDto, Long idMesa) {
		System.out.println("HOLA SOY EL TICKET ENTRO EN CREATE"+ ticketDto + "mesa" + idMesa );

		Ticket ticket = new Ticket(
							ticketDto.getNombreBar(), ticketDto.getDireccionBar()
							);
		System.out.println("HOLA SOY EL TICKET ID"+ ticket.getId());
		Mesa mesa = mesaRepository.findById(idMesa).orElse(null);
		ticket.setMesa(mesa);
		ticket.setEnProceso(true);
		
		mesa.setAbierta(true);
		this.ticketRepository.saveAndFlush(ticket);
		mesa.setTicket(ticket);
		this.mesaRepository.saveAndFlush(mesa);
		return  new TicketDto(ticket);
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
		Double importeTotal = (Double) ticket.getTicketProductos().stream().mapToDouble(ticketProducto -> ticketProducto.getCantidad() * ticketProducto.getProducto().getPrecio()).sum();
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
		Double importeFinal = (Double) (ticket.getImporteTotal() - (ticket.getImporteTotal() * porcentaje / 100));
		ticket.setDescuento(porcentaje);
		ticket.setImporteFinal(importeFinal);
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
		
		return this.ticketRepository.findByEnProceso(false, pageable);
	}

	@Override
	public Page<Ticket> findByDate(Date fecha, Pageable pageable) {
		Calendar c = Calendar.getInstance(); 
		c.setTime(fecha); 
		c.add(Calendar.DATE, 1);	 
		Page<Ticket> tickets = this.ticketRepository.findByFechaCreacion(fecha, c.getTime(), pageable);
		//Page<Ticket> ticketsFinales =  (Page<Ticket>) tickets.stream().filter(t -> t.getEnProceso() == false).collect(Collectors.toList());
		return  tickets;
	}

	@Override
	public TicketDto updateFinal(Long id) {
		Ticket ticket = this.ticketRepository.findById(id).orElse(null);
		ticket.setFechaUltimaModificacion(new Date(System.currentTimeMillis()));
		ticket.setEnProceso(false);
		ticket.getMesa().setAbierta(false);
		this.ticketRepository.save(ticket);
		return new TicketDto(ticket);
	}

	@Override
	public TicketDto updateMozo(Long idTicket, Long idMozo) {
		Ticket ticket = this.ticketRepository.findById(idTicket).orElse(null);
		Mozo mozoNuevo = this.mozoRepository.findById(idMozo).orElse(null);
		ticket.setMozo(mozoNuevo);
		this.ticketRepository.save(ticket);
		return new TicketDto(ticket);
	}

	@Override
	public TicketDto updateMetodoDePago(Long idTicket, String metodoDePago) {
		Ticket ticket = this.ticketRepository.findById(idTicket).orElse(null);
		ticket.setMetodoDePago(metodoDePago);
		this.ticketRepository.save(ticket);
		return new TicketDto(ticket);
	}
	
	@Override
	public TicketDto deleteTicketProducto(Long idTicket, Long idProducto) {
		Ticket ticket = this.ticketRepository.findById(idTicket).orElse(null);
		//ticket.getTicketProductos().removeIf(x -> x.getIdProducto() == idProducto);
		TicketProducto ticketProducto = ticket.getTicketProductos().stream().filter(t -> t.getIdProducto() == idProducto).findAny().orElse(null);
		ticket.getTicketProductos().remove(ticketProducto);
		this.ticketProductoRepository.delete(ticketProducto);
		this.ticketRepository.saveAndFlush(ticket);
		return new TicketDto(ticket);
	}

	@Override
	public void updateMesa(Long idTicket, Long idMesa) {
		Ticket ticket = this.ticketRepository.findById(idTicket).orElse(null);
		ticket.getMesa().setAbierta(false);
		ticket.getMesa().setTicket(null);
		this.mesaRepository.saveAndFlush(ticket.getMesa());
		Mesa mesa = this.mesaRepository.findById(idMesa).orElse(null);
		mesa.setAbierta(true);
		mesa.setTicket(ticket);
		this.mesaRepository.save(mesa);
		
		ticket.setMesa(mesa);
		
		this.ticketRepository.save(ticket);
	}

	public void deleteTicketProductoAux(Long idTicket, Long idProducto) {
		Ticket ticket = this.ticketRepository.findById(idTicket).orElse(null);
		//ticket.getTicketProductos().removeIf(x -> x.getIdProducto() == idProducto);
		TicketProducto ticketProducto = ticket.getTicketProductos().stream().filter(t -> t.getIdProducto() == idProducto).findAny().orElse(null);
		ticket.getTicketProductos().remove(ticketProducto);
		this.ticketProductoRepository.delete(ticketProducto);
		//this.ticketRepository.saveAndFlush(ticket);
	}
	
	@Override
	public void cancelarTicket(Long idTicket, Long idMesa) {
		Ticket ticket = this.ticketRepository.findById(idTicket).orElse(null);
		Mesa mesa = this.mesaRepository.findById(idMesa).orElse(null);
		mesa.setAbierta(false);
		mesa.setTicket(null);
		//acca seteamos el atributo cancelado en true
		ticket.setCancelado(true);
		this.mesaRepository.saveAndFlush(mesa);
		this.ticketRepository.saveAndFlush(ticket);
		/*while(ticket.getTicketProductos().size() > 0) {
		ticket.getTicketProductos().stream().forEach(t -> this.deleteTicketProductoAux(idTicket, t.getIdProducto()));
		}
		*/
	}

	@Override
	public Page<Ticket> ticketsNoCancelados(Page<Ticket> tickets) {
		Page<Ticket> ticketsNoCancelados = (Page<Ticket>) tickets.stream().filter(t -> t.getCancelado().equals(false)).collect(Collectors.toList());
		return ticketsNoCancelados;
	}
	
	
}
