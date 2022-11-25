package com.tip.gestionBares.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.tip.gestionBares.model.Mesa;
import com.tip.gestionBares.model.Mozo;
import com.tip.gestionBares.model.Ticket;

public class TicketDto {

	private Long id;
	private Mesa mesa;
	private Mozo mozo;
	private Date fechaCreacion;
	private Date fechaUltimaModificacion;
	private String nombreBar;
	private String direccionBar;
	private Integer nroTicket;
	private Double importeTotal;
	private Double importeFinal;
	private String metodoDePago;
	private List<TicketProductoDto> ticketProductosDto;
	private Integer descuento;
	private Boolean enProceso;
	private Boolean cancelado;

	public TicketDto(String nombreBar, String direccionBar) {
		super();
		this.nombreBar = nombreBar;
		this.direccionBar = direccionBar;
		this.ticketProductosDto = new ArrayList<TicketProductoDto>();
	}
	
	public TicketDto(Ticket ticket) {
		this(
			 ticket.getNombreBar(),
			 ticket.getDireccionBar());
			this.id = ticket.getId();
			this.mozo = ticket.getMozo();
			this.nroTicket = ticket.getNroTicket();
			this.metodoDePago = ticket.getMetodoDePago();
			this.fechaCreacion = ticket.getFechaCreacion();
			this.fechaUltimaModificacion = ticket.getFechaUltimaModificacion();
			this.ticketProductosDto = ticket.getTicketProductos().stream().map(t -> new TicketProductoDto(t)).collect(Collectors.toList());
			this.enProceso = ticket.getEnProceso();
			this.cancelado = ticket.getCancelado();
			this.importeTotal = ticket.getImporteTotal(); 
			this.importeFinal = ticket.getImporteFinal();
			this.descuento = ticket.getDescuento();
			this.mesa = ticket.getMesa();
	}
	
	public List<TicketProductoDto> getTicketProductosDto() {
		return ticketProductosDto;
	}

	public void setTicketProductosDto(List<TicketProductoDto> ticketProductosDto) {
		this.ticketProductosDto = ticketProductosDto;
	}

	public Long getId() {
		return id;
	}

	

	public Boolean getCancelado() {
		return cancelado;
	}

	public void setCancelado(Boolean cancelado) {
		this.cancelado = cancelado;
	}

	public Boolean getEnProceso() {
		return enProceso;
	}

	public void setEnProceso(Boolean enProceso) {
		this.enProceso = enProceso;
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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fecha) {
		this.fechaCreacion = fecha;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
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

	public Integer getDescuento() {
		return descuento;
	}

	public void setDescuento(Integer descuento) {
		this.descuento = descuento;
	}

	public Double getImporteFinal() {
		return importeFinal;
	}

	public void setImporteFinal(Double importeFinal) {
		this.importeFinal = importeFinal;
	}
}
