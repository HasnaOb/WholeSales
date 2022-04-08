package com.jal.wholesales.model;

public class Pedido {

	private long id;
	private String nombre;
	private long idProvincia;
	
	public Pedido() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(long idProvincia) {
		this.idProvincia = idProvincia;
	}
}

