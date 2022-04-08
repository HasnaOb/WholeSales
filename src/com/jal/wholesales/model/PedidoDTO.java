package com.jal.wholesales.model;

import java.util.Date;
import java.util.List;

public class PedidoDTO {
	private Long id;
	private Date fecha_creacion;
	private Double total;
	private Long idTipo_Estado;
	private Long idEmpresa;
	private Long idDireccion;
	private List<LineaPedido> lineas;
	
	public PedidoDTO() {
		
	}

		public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Long getIdTipo_Estado() {
		return idTipo_Estado;
	}

	public void setIdTipo_Estado(Long idTipo_Estado) {
		this.idTipo_Estado = idTipo_Estado;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Long getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}

	public List<LineaPedido> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaPedido> lineas) {
		this.lineas = lineas;
	}

		@Override
	public String toString() {
		return "Pedido={id="+getId()+",idEmpresa="+getIdEmpresa()
			+",total="+getTotal()+"}"
			;
	}

}
