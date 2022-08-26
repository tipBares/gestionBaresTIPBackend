package com.tip.gestionBares.model;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="Ticket")
@Table(name="ticket")
public class Ticket {
	private static final AtomicInteger count = new AtomicInteger(1); 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne(mappedBy="ticket", cascade=CascadeType.ALL)
	private Mesa mesa;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "mozo_id")
	private Mozo mozo;
	@Column(name = "fecha")
	private LocalDate fecha;
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
	
	public Ticket() {
		super();
	}

	public Ticket(Mesa mesa, Mozo mozo, LocalDate fecha, String nombreBar, String direccionBar,
			Integer nroTicket, Double importeTotal, String metodoDePago) {
		super();
		this.mesa = mesa;
		this.mozo = mozo;
		this.fecha = fecha;
		this.nombreBar = nombreBar;
		this.direccionBar = direccionBar;
		this.nroTicket = count.incrementAndGet();
		this.importeTotal = importeTotal;
		this.metodoDePago = metodoDePago;
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
	
	
}
