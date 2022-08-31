package com.tip.gestionBares.service;

import java.time.LocalDate;
import java.util.List;

import com.tip.gestionBares.dto.TicketDto;

public interface TicketService {

	public TicketDto create(TicketDto ticketDto);
	public List<TicketDto> delete(Long id);
	public List<TicketDto> findByDate(LocalDate fecha);
	public void generarImporteTotal(Long idTicket);
	public TicketDto agregarProducto(Long idTicket, Long idProducto);
	public List<TicketDto> findAll();
	public TicketDto applyDiscount(Long id, Integer porcentaje);
}
