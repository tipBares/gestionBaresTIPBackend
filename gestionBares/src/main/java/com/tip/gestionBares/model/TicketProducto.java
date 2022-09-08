package com.tip.gestionBares.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "TicketProducto")
@Table(name = "ticketProducto")
@IdClass(value = TicketProductoId.class)
public class TicketProducto {
	
	@Id
	private Long idTicket;

	@Id
	private Long idProducto;

	private int cantidad;

	@ManyToOne(fetch=FetchType.LAZY, cascade =CascadeType.ALL)
	@JoinColumn(name = "idTicket",  referencedColumnName = "id", foreignKey = @ForeignKey(name = "ticketProducto_ticket_id_fk"), insertable = false, updatable = false)
	private Ticket ticket;

	@ManyToOne(fetch=FetchType.EAGER ,cascade = CascadeType.ALL)
	@JoinColumn(name = "idProducto", referencedColumnName = "id", foreignKey = @ForeignKey(name = "ticketProducto_producto_id_fk"), insertable = false, updatable = false)
	private Producto producto;

	public TicketProducto() {

	}

	public TicketProducto(Long idTicket, Long idProducto, int cantidad) {
		super();
		this.idTicket = idTicket;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	public Long getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		TicketProducto that = (TicketProducto) o;
		return Objects.equals(ticket, that.ticket) && Objects.equals(producto, that.producto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ticket, producto);
	}
}
