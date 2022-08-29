package com.tip.gestionBares.dto;

import java.time.LocalDate;

import com.tip.gestionBares.model.Mesa;
import com.tip.gestionBares.model.Mozo;
import com.tip.gestionBares.model.Ticket;

public class TicketDto {

	private Long id;
	
	private Mesa mesa;
	
	private Mozo mozo;
	
	private LocalDate fecha;
	
	private String nombreBar;
	
	private String direccionBar;
	
	private Integer nroTicket;
	
	private Double importeTotal;

	private String metodoDePago;


	public TicketDto(Mesa mesa, Mozo mozo, LocalDate fecha, String nombreBar, String direccionBar) {
		super();
		this.mesa = mesa;
		this.mozo = mozo;
		this.fecha = fecha;
		this.nombreBar = nombreBar;
		this.direccionBar = direccionBar;
		
	}
	
	public TicketDto(Ticket ticket) {
		this(ticket.getMesa(),
			 ticket.getMozo(),
			 ticket.getFecha(),
			 ticket.getNombreBar(),
			 ticket.getDireccionBar());
			this.id = ticket.getId();
			this.nroTicket = ticket.getNroTicket();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Mozo getMozo() {
		return mozo;
	}

	public void setMozo(Mozo mozo) {
		this.mozo = mozo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getNombreBar() {
		return nombreBar;
	}

	public void setNombreBar(String nombreBar) {
		this.nombreBar = nombreBar;
	}

	public String getDireccionBar() {
		return direccionBar;
	}

	public void setDireccionBar(String direccionBar) {
		this.direccionBar = direccionBar;
	}

	public Integer getNroTicket() {
		return nroTicket;
	}

	public void setNroTicket(Integer nroTicket) {
		this.nroTicket = nroTicket;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getMetodoDePago() {
		return metodoDePago;
	}

	public void setMetodoDePago(String metodoDePago) {
		this.metodoDePago = metodoDePago;
	}
	
}
