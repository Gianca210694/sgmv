package org.transport420.sgmv.model.reporte;

public class ComponentePrincipal {

	private int idsgmv_componente_principal;
	private String titulo;
	private String descripcion;
	private int marcar;

	public ComponentePrincipal() {

	}

	public ComponentePrincipal(int idsgmv_componente_principal, String titulo, String descripcion, int marcar) {
		super();
		this.idsgmv_componente_principal = idsgmv_componente_principal;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.marcar = marcar;
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

	public int getMarcar() {
		return marcar;
	}

	public void setMarcar(int marcar) {
		this.marcar = marcar;
	}

}
