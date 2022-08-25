package com.tip.gestionBares.dto;

import java.util.UUID;

import com.tip.gestionBares.model.Producto;

public class ProductoDto {
	
	private UUID id;
	
	private String nombre;
	
	private Double precio;
	
	private String categoria;
	
	private String descripcion;

	public ProductoDto(String nombre, Double precio, String categoria, String descripcion) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
		this.descripcion = descripcion;
	}
	
	public ProductoDto(Producto producto) {
		this(producto.getNombre(),
			 producto.getPrecio(),
			 producto.getCategoria(),
			 producto.getDescripcion());
		this.id = producto.getId();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
