package com.tip.gestionBares.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="Ticket")
@Table(name="ticket")
public class Ticket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final AtomicInteger count = new AtomicInteger(0); 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne(mappedBy="ticket", cascade=CascadeType.ALL)
	private Mesa mesa;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "mozo_id")
	private Mozo mozo;
	@Column(name = "fecha")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate fecha = LocalDate.now();
	@Column(name = "hora_fecha")
	private LocalTime horaFecha = LocalTime.now();
	@Column(name = "nombre_bar")
	private String nombreBar;
	@Column(name = "direccion_bar")
	private String direccionBar;
	@Column(name = "nro_ticket")
	private Integer nroTicket;
	@Column(name = "importe_total")
	private Double importeTotal;
	@Column(name = "metodo_de_pago")
	private String metodoDePago;
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "idProducto")
	private List<TicketProducto> ticketProductos = new ArrayList<TicketProducto>();
	@Column(name = "descuento")
	private Integer descuento;
	
	
	public Ticket() {
		super();
	}

	public Ticket(Mesa mesa, Mozo mozo, String nombreBar, String direccionBar) {
		super();
		this.mesa = mesa;
		this.mozo = mozo;
		this.nombreBar = nombreBar;
		this.direccionBar = direccionBar;
		this.nroTicket = count.incrementAndGet();
		this.metodoDePago = "Efectivo";
	}

	public LocalTime getHoraFecha() {
		return horaFecha;
	}

	public void setHoraFecha(LocalTime horaFecha) {
		this.horaFecha = horaFecha;
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

	public Integer getDescuento() {
		return descuento;
	}

	public void setDescuento(Integer descuento) {
		this.descuento = descuento;
	}

	public List<TicketProducto> getTicketProductos() {
		return ticketProductos;
	}

	public void setTicketProductos(List<TicketProducto> ticketProductos) {
		this.ticketProductos = ticketProductos;
	}
	
	
}
