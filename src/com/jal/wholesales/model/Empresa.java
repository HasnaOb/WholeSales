package com.jal.wholesales.model;

import java.util.List;

public class Empresa extends AbstractValueObject {
	
	private long id;
	private String nombre;
	private String cif;
	private Long idTipoEmpresa;
	private String nombreUsuario;
	private String email;
	private String contrasena;
	private String contrasenaEncriptada;
	private List<Direccion> direcciones;
	
	
	
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
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public Long getIdTipoEmpresa() {
		return idTipoEmpresa;
	}
	public void setIdTipoEmpresa(Long idTipoEmpresa) {
		this.idTipoEmpresa = idTipoEmpresa;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getContrasenaEncriptada() {
		return contrasenaEncriptada;
	}
	public void setContrasenaEncriptada(String contrasenaEncriptada) {
		this.contrasenaEncriptada = contrasenaEncriptada;
	}
	public List<Direccion> getDirecciones() {
		return direcciones;
	}
	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	 
	
 

	 
	
	
}
