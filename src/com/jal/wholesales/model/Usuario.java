package com.jal.wholesales.model;

import java.util.Date;

public class Usuario {
 
		private long id;
		private String nombre;
		private Date fechaNacimiento;
		private String email;
		public Usuario() {
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
		public Date getFechaNacimiento() {
			return fechaNacimiento;
		}
		public void setFechaNacimiento(Date fechaNacimiento) {
			this.fechaNacimiento = fechaNacimiento;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
	 
		
}