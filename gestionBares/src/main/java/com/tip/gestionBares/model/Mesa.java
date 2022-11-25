package com.tip.gestionBares.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name="Mesa")
@Table(name="mesa")
public class Mesa implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "abierta")
	private Boolean abierta;
  
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "id_ticket", nullable=true ,foreignKey=@ForeignKey(name="mesa_id_fk"))
	private Ticket ticket;

	@Column(name = "nro_mesa")
	private Integer nroMesa;

	public Mesa() {
		super();
	}

	public Mesa(Boolean abierta, Integer nroMesa) {
		super();
		this.abierta = abierta;
		this.nroMesa = nroMesa;
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
