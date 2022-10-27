package com.tip.gestionBares.dto;

import com.tip.gestionBares.model.Mesa;
import com.tip.gestionBares.model.Ticket;

public class MesaDto {
	
	private Long id;
	
	private Boolean abierta;
	
	private Long idTicket;
	
	private Integer nroMesa;

	public MesaDto(Boolean abierta, Integer nroMesa) {
		super();
		this.abierta = abierta;
		this.nroMesa = nroMesa;
	}

	public MesaDto(Mesa mesa) {
		this(mesa.getAbierta(),
			 mesa.getNroMesa());
		this.idTicket = mesa.getIdTicket();
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

	public Long getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}

	public Integer getNroMesa() {
		return nroMesa;
	}

	public void setNroMesa(Integer nroMesa) {
		this.nroMesa = nroMesa;
	}
	
	
}
