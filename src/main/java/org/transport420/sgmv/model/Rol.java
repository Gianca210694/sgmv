package org.transport420.sgmv.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rol {

	private int idsgmv_rol;
	private String rol;
	private String descripcion;
	private int estado;

	public Rol() {

	}

	public Rol(int idsgmv_rol, String rol, String descripcion, int estado) {
		super();
		this.idsgmv_rol = idsgmv_rol;
		this.rol = rol;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public int getIdsgmv_rol() {
		return idsgmv_rol;
	}

	public void setIdsgmv_rol(int idsgmv_rol) {
		this.idsgmv_rol = idsgmv_rol;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
