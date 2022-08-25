package com.tip.gestionBares.dto;

import java.util.UUID;

import com.tip.gestionBares.model.Mozo;

public class MozoDto {
	private UUID id;
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
}
