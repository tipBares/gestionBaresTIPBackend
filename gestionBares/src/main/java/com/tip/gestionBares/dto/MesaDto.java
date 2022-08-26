package com.tip.gestionBares.dto;

import com.tip.gestionBares.model.Mesa;
import com.tip.gestionBares.model.Ticket;

public class MesaDto {
	
	private Long id;
	
	private Boolean abierta;
	
	private Ticket ticket;
	
	private Integer nroMesa;

	public MesaDto(Boolean abierta, Ticket ticket, Integer nroMesa) {
		super();
		this.abierta = abierta;
		this.ticket = ticket;
		this.nroMesa = nroMesa;
	}

	public MesaDto(Mesa mesa) {
		this(mesa.getAbierta(),
			 mesa.getTicket(),
			 mesa.getNroMesa());
		this.id = mesa.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAbierta() {
		return abierta;
	}

	public void setAbierta(Boolean abierta) {
		this.abierta = abierta;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Integer getNroMesa() {
		return nroMesa;
	}

	public void setNroMesa(Integer nroMesa) {
		this.nroMesa = nroMesa;
	}
	
	
}
