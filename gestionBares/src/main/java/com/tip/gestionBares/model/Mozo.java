package com.tip.gestionBares.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Mozo")
@Table(name="mozo")
public class Mozo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//El mozo tiene nombre, apellido y id.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellido")
	private String apellido;
	@Column(name = "nick", unique=true)
	private String nick;
	
	public Mozo() {
		super();
	}

	public Mozo(String nombre, String apellido, String nick) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.nick = nick;
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

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	
	
}
