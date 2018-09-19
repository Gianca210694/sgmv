package org.transport420.sgmv.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Modulo {

	private int idsgmv_modulo;
	private String titulo;
	private String descripcion;
	private int idsgmv_modulo_padre;
	private int estado;

	public Modulo() {

	}

	public Modulo(int idsgmv_modulo, String titulo, String descripcion, int idsgmv_modulo_padre, int estado) {
		super();
		this.idsgmv_modulo = idsgmv_modulo;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.idsgmv_modulo_padre = idsgmv_modulo_padre;
		this.estado = estado;
	}

	public int getIdsgmv_modulo() {
		return idsgmv_modulo;
	}

	public void setIdsgmv_modulo(int idsgmv_modulo) {
		this.idsgmv_modulo = idsgmv_modulo;
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

	public int getIdsgmv_modulo_padre() {
		return idsgmv_modulo_padre;
	}

	public void setIdsgmv_modulo_padre(int idsgmv_modulo_padre) {
		this.idsgmv_modulo_padre = idsgmv_modulo_padre;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
