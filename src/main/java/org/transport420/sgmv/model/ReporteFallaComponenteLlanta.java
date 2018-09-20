package org.transport420.sgmv.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReporteFallaComponenteLlanta {

	private int idsgmv_reporte_falla;
	private int idsgmv_componente_llanta;
	private String descripcion;

	public ReporteFallaComponenteLlanta() {

	}

	public ReporteFallaComponenteLlanta(int idsgmv_reporte_falla, int idsgmv_componente_llanta, String descripcion) {
		super();
		this.idsgmv_reporte_falla = idsgmv_reporte_falla;
		this.idsgmv_componente_llanta = idsgmv_componente_llanta;
		this.descripcion = descripcion;
	}

	public int getIdsgmv_reporte_falla() {
		return idsgmv_reporte_falla;
	}

	public void setIdsgmv_reporte_falla(int idsgmv_reporte_falla) {
		this.idsgmv_reporte_falla = idsgmv_reporte_falla;
	}

	public int getIdsgmv_componente_llanta() {
		return idsgmv_componente_llanta;
	}

	public void setIdsgmv_componente_llanta(int idsgmv_componente_llanta) {
		this.idsgmv_componente_llanta = idsgmv_componente_llanta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
