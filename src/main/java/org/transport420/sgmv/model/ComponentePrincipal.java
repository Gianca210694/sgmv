package org.transport420.sgmv.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ComponentePrincipal {

	private int idsgmv_componente_principal;
	private String titulo;
	private String descripcion;
	private int estado;

	public ComponentePrincipal() {

	}

	public ComponentePrincipal(int idsgmv_componente_principal, String titulo, String descripcion, int estado) {
		super();
		this.idsgmv_componente_principal = idsgmv_componente_principal;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public int getIdsgmv_componente_principal() {
		return idsgmv_componente_principal;
	}

	public void setIdsgmv_componente_principal(int idsgmv_componente_principal) {
		this.idsgmv_componente_principal = idsgmv_componente_principal;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
