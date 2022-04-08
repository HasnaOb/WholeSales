package com.jal.wholesales.model;

import java.util.Date;

public class LineaPedido {
	private long id;
	private double precio; 
	private long unidades;
	private double total;
	private Date fechaCreacion;
	private long idPedido;
	private long idProducto;
	
	public LineaPedido() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public long getUnidades() {
		return unidades;
	}

	public void setUnidades(long unidades) {
		this.unidades = unidades;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fecha_creacion) {
		this.fechaCreacion = fecha_creacion;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
 

	
	
	

}
