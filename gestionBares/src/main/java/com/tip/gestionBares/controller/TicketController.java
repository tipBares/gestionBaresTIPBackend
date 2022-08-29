package com.tip.gestionBares.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<TicketDto> create(@RequestBody TicketDto ticketDto){
		ticketDto = this.ticketService.create(ticketDto);
		
		return new ResponseEntity<TicketDto>(ticketDto, HttpStatus.CREATED);
		
	}
}
