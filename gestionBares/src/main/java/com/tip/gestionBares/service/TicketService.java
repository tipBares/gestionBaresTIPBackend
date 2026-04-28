package com.tip.gestionBares.service;

import java.util.Date;
import java.util.List;

import com.tip.gestionBares.model.enums.EstadoTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.tip.gestionBares.dto.TicketDto;
import com.tip.gestionBares.dto.TicketProductoDto;
import com.tip.gestionBares.model.Ticket;

public interface TicketService {
	 TicketDto create(TicketDto ticketDto, Long idMesa);
	 List<TicketDto> delete(Long id);
	 Page<Ticket> findByDate(Date fecha, Pageable pageable);
	 void generarImporteTotal(Long idTicket);
	 TicketDto agregarProducto(TicketProductoDto ticketProductoDto);
	 List<TicketDto> findAll();
	 Page<Ticket> findAllPag(Pageable pageable);
	 TicketDto applyDiscount(Long id, Integer porcentaje);
	 TicketDto update(TicketDto ticketDto, Long id);
	 TicketDto getTicketById(Long id);
	 TicketDto updateFinal(Long id);
	 TicketDto updateMozo(Long idTicket, Long idMozo);
	 TicketDto updateMetodoDePago(Long idTicket, String metodoDePago);
	 TicketDto deleteTicketProducto(Long idTicket, Long idProducto);
	 void updateMesa(Long idTicket, Long idMesa);
	 void cancelarTicket(Long idTicket, Long idMesa);
     TicketDto actualizarEstado(Long id, EstadoTicket estado);
	 List<TicketDto> findByEstado(EstadoTicket estado);
	 List<TicketDto> getTicketsCocina();
}
