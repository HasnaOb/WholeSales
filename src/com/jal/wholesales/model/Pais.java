package com.jal.wholesales.model;

public class Pais{
	private long id;
	private String nombre;
	public Pais() {		
	}
	
	public Pais(long id, String nombre) {
		setId(id);
		setNombre(nombre);
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

	@Override
	public String toString() {
		return "Pais={id="+getId()+",nombre="+getNombre()+"}";
	}
}
