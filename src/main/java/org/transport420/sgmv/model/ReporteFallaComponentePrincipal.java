package org.transport420.sgmv.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReporteFallaComponentePrincipal {

	private int idsgmv_reporte_falla;
	private int idsgmv_componente_principal;
	private String descripcion;

	public ReporteFallaComponentePrincipal() {

	}

	public ReporteFallaComponentePrincipal(int idsgmv_reporte_falla, int idsgmv_componente_principal,
			String descripcion) {
		super();
		this.idsgmv_reporte_falla = idsgmv_reporte_falla;
		this.idsgmv_componente_principal = idsgmv_componente_principal;
		this.descripcion = descripcion;
	}

	public int getIdsgmv_reporte_falla() {
		return idsgmv_reporte_falla;
	}

	public void setIdsgmv_reporte_falla(int idsgmv_reporte_falla) {
		this.idsgmv_reporte_falla = idsgmv_reporte_falla;
	}

	public int getIdsgmv_componente_principal() {
		return idsgmv_componente_principal;
	}

	public void setIdsgmv_componente_principal(int idsgmv_componente_principal) {
		this.idsgmv_componente_principal = idsgmv_componente_principal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
