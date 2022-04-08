package com.jal.wholesales.service;
import com.jal.wholesales.model.AbstractValueObject;

public class CategoriaCriteria extends AbstractValueObject{
	private String nombre;
	private Long idCategoriaPadre;
	
	
	public CategoriaCriteria() {
		
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Long getIdCategoriaPadre() {
		return idCategoriaPadre;
	}


	public void setIdCategoriaPadre(Long idCategoriaPadre) {
		this.idCategoriaPadre = idCategoriaPadre;
	}

}
