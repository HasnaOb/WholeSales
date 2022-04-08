package com.jal.wholesales.model;

public class ProductoDTO extends AbstractValueObject {
	private long id;
	private String nombre;
	private String descripcion;
	private long idPais;
	private double precio;
	private long idCategoria;
	private long idSeccion;
	private long idMarca;
	private long idEmpresa;
	
	public ProductoDTO() {
		
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public long getIdPais() {
		return idPais;
	}
	public void setIdPais(long idPais) {
		this.idPais = idPais;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public long getIdSeccion() {
		return idSeccion;
	}
	public void setIdSeccion(long idSeccion) {
		this.idSeccion = idSeccion;
	}
	public long getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(long idMarca) {
		this.idMarca = idMarca;
	}
	public long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	@Override
	public String toString() {
		return "Pais={id="+getId()+",nombre="+getNombre()+"}";
	}

	 

}
