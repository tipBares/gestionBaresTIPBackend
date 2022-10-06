package com.tip.gestionBares.controller;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tip.gestionBares.dto.TicketDto;
import com.tip.gestionBares.dto.TicketProductoDto;
import com.tip.gestionBares.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@PostMapping
	public ResponseEntity<TicketDto> create(@RequestBody TicketDto ticketDto) {
		ticketDto = this.ticketService.create(ticketDto);

		return new ResponseEntity<TicketDto>(ticketDto, HttpStatus.CREATED);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<List<TicketDto>> delete(@PathVariable(value = "id") Long id) throws NotFoundException {
		List<TicketDto> ticketsDto = this.ticketService.delete(id);
		

		return new ResponseEntity<List<TicketDto>>(ticketsDto, HttpStatus.OK);

	}
	
	@GetMapping("{id}")
	public ResponseEntity<TicketDto> findById(@PathVariable(value = "id") Long id) throws NotFoundException {
		TicketDto ticketDto = this.ticketService.getTicketById(id);
		if(ticketDto == null) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "entity not found"
					);
		} else {
			return new ResponseEntity<TicketDto>(ticketDto, HttpStatus.OK);
		}
		
	}
	
	@GetMapping("fecha/{date}")
    public ResponseEntity<List<TicketDto>> findBydate(@PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date fecha) throws NotFoundException{
		
		List<TicketDto> ticketsDto = this.ticketService.findByDate(fecha);
		if (ticketsDto.size() <= 0) {
			throw new NotFoundException();
		}

		return new ResponseEntity<List<TicketDto>>(ticketsDto, HttpStatus.OK);
    }
	
	@PostMapping("/productos")
	public ResponseEntity<TicketDto> addProduct(@RequestBody TicketProductoDto ticketProductoDto) {
		TicketDto ticketDto = this.ticketService.agregarProducto(ticketProductoDto);
		return new ResponseEntity<TicketDto>(ticketDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/sumImporteTotal/{idTicket}")
	public ResponseEntity<Void> generarImporteTotal(@PathVariable(value = "idTicket")Long idTicket) {
		this.ticketService.generarImporteTotal(idTicket);
		return null;
	}

	@GetMapping()
	public ResponseEntity<List<TicketDto>> findAll() throws NotFoundException{
		List<TicketDto> ticketsDto = this.ticketService.findAll();
		if(ticketsDto.size() <= 0) {
			throw new NotFoundException();
		}
		return new ResponseEntity<List<TicketDto>>(ticketsDto, HttpStatus.OK);
	}
	
	@PutMapping("{id}/descuento/{porcentaje}")
	public ResponseEntity<TicketDto> applyDiscount(@PathVariable Long id, @PathVariable Integer porcentaje){
		TicketDto ticketDto = this.ticketService.applyDiscount(id, porcentaje);
		
		return new ResponseEntity<TicketDto>(ticketDto, HttpStatus.OK);
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<TicketDto> updateById(@RequestBody TicketDto ticket, @PathVariable("id") Long id) throws NotFoundException {
		TicketDto ticketDto = this.ticketService.update(ticket, id);
		if(ticketDto == null) {
			throw new NotFoundException();
		}
		return new ResponseEntity<TicketDto>(ticketDto, HttpStatus.OK);
	}
	
}
