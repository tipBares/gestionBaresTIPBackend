package com.tip.gestionBares.dto;

import com.tip.gestionBares.model.TicketProducto;

public class TicketProductoDto {
	private Long idTicket;
	private Long idProducto;
	private int cantidad;
	private String nombreProducto;
	private Double precioProducto;
	
	public TicketProductoDto(TicketProducto ticketProducto) {
		this(ticketProducto.getIdTicket(), ticketProducto.getIdProducto(), ticketProducto.getCantidad());	
		this.nombreProducto = ticketProducto.getProducto().getNombre();
		this.precioProducto = ticketProducto.getProducto().getPrecio();
	}
	public TicketProductoDto(Long idTicket, Long idProducto, int cantidad) {
		super();
		this.idTicket = idTicket;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	
	public Long getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
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
	public Double getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(Double precioProducto) {
		this.precioProducto = precioProducto;
	}
	
	
}
