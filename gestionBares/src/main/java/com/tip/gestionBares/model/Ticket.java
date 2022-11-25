package com.tip.gestionBares.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="Ticket")
@Table(name="ticket")
public class Ticket implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final AtomicInteger count = new AtomicInteger(0); 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Mesa mesa;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "mozo_id")
	private Mozo mozo;
	
	@Column(name = "fecha_creacion")
	@Basic
	@javax.persistence.Temporal(TemporalType.TIMESTAMP)
	private java.util.Date fechaCreacion = new Date(System.currentTimeMillis());
	
	@Column(name = "fecha_ultima_modificacion")
	@Basic
	@javax.persistence.Temporal(TemporalType.TIMESTAMP)
	private java.util.Date fechaUltimaModificacion = new Date(System.currentTimeMillis());
	
	@Column(name = "nombre_bar")
	private String nombreBar;
	
	@Column(name = "direccion_bar")
	private String direccionBar;
	
	@Column(name = "nro_ticket")
	private Integer nroTicket;
	
	@Column(name = "importe_total")
	private Double importeTotal;
	
	@Column(name = "importe_final")
	private Double importeFinal;
	
	@Column(name = "metodo_de_pago")
	private String metodoDePago;

	@JsonIgnore
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "ticket")
	private List<TicketProducto> ticketProductos = new ArrayList<TicketProducto>();
	
	@Column(name = "descuento")
	private Integer descuento;
	
	@Column(name = "en_proceso")
	private Boolean enProceso;
	
	@Column(name = "cancelado")
	private Boolean cancelado;
	
	public Ticket() {
		super();
	}

	public Ticket(String nombreBar, String direccionBar) {
		super();
		this.nombreBar = nombreBar;
		this.direccionBar = direccionBar;
		this.nroTicket = count.incrementAndGet();
		this.metodoDePago = "Efectivo";
		this.cancelado = false;
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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fecha) {
		this.fechaCreacion = fecha;
	}
	
	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fecha) {
		this.fechaUltimaModificacion = fecha;
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

	public Double getImporteFinal() {
		return importeFinal;
	}

	public void setImporteFinal(Double importeFinal) {
		this.importeFinal = importeFinal;
	}
	
}
