package com.jal.wholesales.model;

public class Direccion {
	private long id;
	private long idEmpresa;
	private long codigoPostal;
	private long idLocalidad;
	private String calleNum;
	
	public Direccion() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public long getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(long codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public long getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	public String getCalleNum() {
		return calleNum;
	}
	public void setCalleNum(String calleNum) {
		this.calleNum = calleNum;
	}}
	
	