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

@Entity(name="Mesa")
@Table(name="mesa")
public class Mesa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "abierta")
	private Boolean abierta;
	//@OneToOne
	//@JoinColumn(name = "ticket_id", nullable=true ,foreignKey=@ForeignKey(name="mesa_id_fk"))
	@Column(name = "id_ticket")
	private Long idTicket;
	
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
