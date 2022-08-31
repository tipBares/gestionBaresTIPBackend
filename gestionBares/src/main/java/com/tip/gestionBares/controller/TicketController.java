package com.tip.gestionBares.controller;

import java.time.LocalDate;
import java.util.List;

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

import com.tip.gestionBares.dto.TicketDto;
import com.tip.gestionBares.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@PostMapping
	public ResponseEntity<TicketDto> create(@RequestBody TicketDto ticketDto) {
		ticketDto = this.ticketService.create(ticketDto);

		return new ResponseEntity<TicketDto>(ticketDto, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<List<TicketDto>> delete(@PathVariable(value = "id") Long id) throws NotFoundException {
		List<TicketDto> ticketsDto = this.ticketService.delete(id);
		if (ticketsDto.size() <= 0) {
			throw new NotFoundException();
		}

		return new ResponseEntity<List<TicketDto>>(ticketsDto, HttpStatus.OK);

	}
	
	@GetMapping("/date/{date}")
    public ResponseEntity<List<TicketDto>> findBydate(@PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha) throws NotFoundException{
		
		List<TicketDto> ticketsDto = this.ticketService.findByDate(fecha);
		if (ticketsDto.size() <= 0) {
			throw new NotFoundException();
		}

		return new ResponseEntity<List<TicketDto>>(ticketsDto, HttpStatus.OK);
    }
	
	@PostMapping("/addProducto/{idTicket}/{idProducto}")
	public ResponseEntity<TicketDto> addProduct(@PathVariable(value = "idTicket")Long idTicket, @PathVariable(value = "idProducto")Long idProducto) {
		TicketDto ticketDto = this.ticketService.agregarProducto(idTicket, idProducto);
		return new ResponseEntity<TicketDto>(ticketDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/sumImporteTotal/{idTicket}")
	public ResponseEntity<Void> generarImporteTotal(@PathVariable(value = "idTicket")Long idTicket) {
		this.ticketService.generarImporteTotal(idTicket);
		return null;
	}
}
