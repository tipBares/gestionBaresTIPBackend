package com.tip.gestionBares.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/All")
	public ResponseEntity<List<TicketDto>> findAll() throws NotFoundException{
		List<TicketDto> ticketsDto = this.ticketService.findAll();
		if(ticketsDto.size() <= 0) {
			throw new NotFoundException();
		}
		return new ResponseEntity<List<TicketDto>>(ticketsDto, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TicketDto> applyDiscount(@PathVariable(value = "id") Long id, @RequestParam Integer porcentaje){
		TicketDto ticketDto = this.ticketService.applyDiscount(id, porcentaje);
		
		return new ResponseEntity<TicketDto>(ticketDto, HttpStatus.OK);
		
	}
	
}
