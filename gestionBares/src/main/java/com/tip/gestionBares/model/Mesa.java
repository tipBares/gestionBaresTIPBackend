package com.tip.gestionBares.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.ForeignKey;

@Entity(name="Mesa")
@Table(name="mesa")
public class Mesa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "abierta")
	private Boolean abierta;
	
	@OneToOne
	@JoinColumn(name = "ticket_id", nullable=true ,foreignKey=@ForeignKey(name="mesa_id_fk"))
	private Ticket ticket;
	
	@Column(name = "nro_mesa")
	private Integer nroMesa;

	public Mesa() {
		super();
	}

	public Mesa(Boolean abierta, Ticket ticket, Integer nroMesa) {
		super();
		this.abierta = abierta;
		this.ticket = ticket;
		this.nroMesa = nroMesa;
	}

	//Preguntar si es necesario tener definir siempre hasCode y equals
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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
