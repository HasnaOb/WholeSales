package com.jal.wholesales.service;

import com.jal.wholesales.model.AbstractValueObject;

public class MarcaCriteria extends AbstractValueObject {
	
	private String nombre;
	
	
	public MarcaCriteria() {
		
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
