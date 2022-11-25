package com.tip.gestionBares.service;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.tip.gestionBares.dto.TicketDto;
import com.tip.gestionBares.dto.TicketProductoDto;
import com.tip.gestionBares.model.Ticket;

public interface TicketService {
	public TicketDto create(TicketDto ticketDto, Long idMesa);
	public List<TicketDto> delete(Long id);
	public Page<Ticket> findByDate(Date fecha, Pageable pageable);
	public void generarImporteTotal(Long idTicket);
	public TicketDto agregarProducto(TicketProductoDto ticketProductoDto);
	public List<TicketDto> findAll();
	public Page<Ticket> findAllPag(Pageable pageable);
	public TicketDto applyDiscount(Long id, Integer porcentaje);
	public TicketDto update(TicketDto ticketDto, Long id);
	public TicketDto getTicketById (Long id);
	public TicketDto updateFinal(Long id);
	public TicketDto updateMozo(Long idTicket, Long idMozo);
	public TicketDto updateMetodoDePago(Long idTicket, String metodoDePago);
	public TicketDto deleteTicketProducto(Long idTicket, Long idProducto);
	public void updateMesa(Long idTicket, Long idMesa);
	public void cancelarTicket(Long idTicket, Long idMesa);
	//public Page<Ticket> ticketsNoCancelados(Page<Ticket> tickets);
}
