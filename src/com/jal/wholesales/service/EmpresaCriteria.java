package com.jal.wholesales.service;

import com.jal.wholesales.model.AbstractValueObject;


public class EmpresaCriteria extends AbstractValueObject{
	
	private String nombre;
	private Long idTipoEmpresa;
	
	
	 

	public EmpresaCriteria() {
		
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public Long getIdTipoEmpresa() {
		return idTipoEmpresa;
	}




	public void setIdTipoEmpresa(Long idTipoEmpresa) {
		this.idTipoEmpresa = idTipoEmpresa;
	}

}
