package com.tip.gestionBares.dto;

import com.tip.gestionBares.model.Mozo;

public class MozoDto {
	private Long id;
	private String nombre;
	private String apellido;
	public MozoDto(String nombre, String apellido) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
	}
	public MozoDto(Mozo mozo) {
		this(mozo.getNombre(),
			 mozo.getApellido());
		this.id = mozo.getId();
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
}
