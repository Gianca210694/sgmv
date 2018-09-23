package org.transport420.sgmv.model.reporte;

public class ComponenteLlanta {

	private int idsgmv_componente_llanta;
	private String titulo;
	private String descripcion;
	private int marcar;

	public ComponenteLlanta() {

	}

	public ComponenteLlanta(int idsgmv_componente_llanta, String titulo, String descripcion, int marcar) {
		super();
		this.idsgmv_componente_llanta = idsgmv_componente_llanta;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.marcar = marcar;
	}

	public int getIdsgmv_componente_llanta() {
		return idsgmv_componente_llanta;
	}

	public void setIdsgmv_componente_llanta(int idsgmv_componente_llanta) {
		this.idsgmv_componente_llanta = idsgmv_componente_llanta;
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
