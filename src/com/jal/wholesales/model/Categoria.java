package com.jal.wholesales.model;

public class Categoria extends AbstractValueObject{
	private long id;
	private String nombre;
	private long idCategoriaPadre;
	
	public Categoria() {
		
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
	public long getIdCategoriaPadre() {
		return idCategoriaPadre;
	}
	public void setIdCategoriaPadre(long idCategoriaPadre) {
		this.idCategoriaPadre = idCategoriaPadre;
	}

	 
		
	}
	
