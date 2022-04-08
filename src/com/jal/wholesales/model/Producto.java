package com.jal.wholesales.model;

public class Producto extends AbstractValueObject{
	
		private long id;
		private String nombre;
		private String descripcion;
		private Long idPais;
		private double precio;
		private Long idCategoria;
		private Long idSeccion;
		private Long idMarca;
		private Long idEmpresa;
		
		public Producto() {
			
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
		public Long getIdPais() {
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
		public Long getIdCategoria() {
			return idCategoria;
		}
		public void setIdCategoria(long idCategoria) {
			this.idCategoria = idCategoria;
		}
		public Long getIdSeccion() {
			return idSeccion;
		}
		public void setIdSeccion(long idSeccion) {
			this.idSeccion = idSeccion;
		}
		public Long getIdMarca() {
			return idMarca;
		}
		public void setIdMarca(long idMarca) {
			this.idMarca = idMarca;
		}
		public Long getIdEmpresa() {
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
