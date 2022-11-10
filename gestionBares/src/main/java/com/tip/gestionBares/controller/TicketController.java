package com.tip.gestionBares.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tip.gestionBares.dto.TicketDto;
import com.tip.gestionBares.dto.TicketProductoDto;
import com.tip.gestionBares.model.Ticket;
import com.tip.gestionBares.service.TicketService;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = {"http://localhost:3000", "*"}, maxAge = 3600)

public class TicketController {

	@Autowired
	private TicketService ticketService;

	@PostMapping("{id}")
	public ResponseEntity<TicketDto> create(@RequestBody TicketDto ticketDto,@PathVariable(value = "id") Long idMesa) {
		ticketDto = this.ticketService.create(ticketDto, idMesa);

		return new ResponseEntity<TicketDto>(ticketDto, HttpStatus.CREATED);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<List<TicketDto>> delete(@PathVariable(value = "id") Long id) throws NotFoundException {
		List<TicketDto> ticketsDto = this.ticketService.delete(id);
		

		return new ResponseEntity<List<TicketDto>>(ticketsDto, HttpStatus.OK);

	}
	
	@GetMapping("byId/{id}")
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
	

	@GetMapping("{date}/{pag}")
    public ResponseEntity<Page<Ticket>> findBydate(
    		@PathVariable(value = "pag" ) int page,
            @RequestParam(defaultValue = "7") int size,
            @PathVariable(value = "date")
            @DateTimeFormat(pattern  = "yyyy-MM-dd") Date fecha) throws NotFoundException{
		
		Page<Ticket> tickets = this.ticketService.findByDate(fecha, PageRequest.of(page, size));

		return new ResponseEntity<Page<Ticket>>(tickets, HttpStatus.OK);
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
	
	@GetMapping("{pag}")
	public ResponseEntity<Page<Ticket>> findAllPag(
			@PathVariable(value = "pag" ) int page,
            @RequestParam(defaultValue = "7") int size
			) throws NotFoundException{
		Page<Ticket> tickets = ticketService.findAllPag(PageRequest.of(page, size));

		return new ResponseEntity<Page<Ticket>>(tickets, HttpStatus.OK);
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
	
	@PutMapping("historico/{id}")
	public ResponseEntity<TicketDto> updateFinal(@PathVariable("id") Long id) throws NotFoundException {
		TicketDto ticketDto = this.ticketService.updateFinal(id);
		if(ticketDto == null) {
			throw new NotFoundException();
		}
		return new ResponseEntity<TicketDto>(ticketDto, HttpStatus.OK);
	}
	
	@PutMapping("{id}/mozos/{idMozo}")
	public ResponseEntity<TicketDto> updateMozo(@PathVariable("id") Long id, @PathVariable("idMozo") Long idMozo) throws NotFoundException {
		TicketDto ticketDto = this.ticketService.updateMozo(id, idMozo);
		if(ticketDto == null) {
			throw new NotFoundException();
		}
		return new ResponseEntity<TicketDto>(ticketDto, HttpStatus.OK);
	} 
	
	@PutMapping("{id}/metodoDePago")
	public ResponseEntity<TicketDto> updateMetodoDePago(@PathVariable("id") Long id, @RequestParam String metodoDePago) throws NotFoundException {
		TicketDto ticketDto = this.ticketService.updateMetodoDePago(id, metodoDePago);
		if(ticketDto == null) {
			throw new NotFoundException();
		}
		return new ResponseEntity<TicketDto>(ticketDto, HttpStatus.OK);
	} 
}
