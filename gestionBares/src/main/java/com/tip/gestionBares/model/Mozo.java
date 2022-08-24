package com.tip.gestionBares.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Mozo")
@Table(name="mozo")
public class Mozo {
	//El mozo tiene nombre, apellido y id.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellido")
	private String apellido;
	
	public Mozo() {
		super();
	}

	public Mozo(String nombre, String apellido) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
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
