package com.jal.wholesales.service;

import com.jal.wholesales.model.AbstractValueObject;
 
public class ProductoCriteria extends AbstractValueObject {
	 
 
	
	private String nombre;
	private String descripcion;
	private Double precioMin;
	private Double precioMax;
	
	private Long idCategoria;
	private Long idEmpresa;
	private Long idMarca;
	private Long idSeccion;
	private Long idPais;
	
	public ProductoCriteria() {
			
		}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecioMin() {
		return precioMin;
	}

	public void setPrecioMin(Double precioMin) {
		this.precioMin = precioMin;
	}

	public Double getPrecioMax() {
		return precioMax;
	}

	public void setPrecioMax(Double precioMax) {
		this.precioMax = precioMax;
	}

 
	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Long getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	public Long getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(Long idSeccion) {
		this.idSeccion = idSeccion;
	}

	public Long getIdPais() {
		return idPais;
	}

	public void setIdPais(Long idPais) {
		this.idPais = idPais;
	}
 

}
