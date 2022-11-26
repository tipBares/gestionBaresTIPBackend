package com.tip.gestionBares.dto;

import com.tip.gestionBares.model.Categoria;

public class CategoriaDto {

	private Long id;
	private String nombre;
	
	public CategoriaDto() {}    
	 
	public CategoriaDto(String nombre) {
		super();
		this.nombre = nombre;
	}

	public CategoriaDto(Categoria categoria) {
		this(categoria.getNombre());
		this.id = categoria.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
