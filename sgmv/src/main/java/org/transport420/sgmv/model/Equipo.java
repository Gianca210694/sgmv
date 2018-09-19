package org.transport420.sgmv.model;

public class Equipo {

	private int idsgmv_equipo;
	private String descripcion;
	private String inspeccionado_por;
	private int ok;

	public Equipo() {

	}

	public Equipo(int idsgmv_equipo, String descripcion, String inspeccionado_por, int ok) {
		super();
		this.idsgmv_equipo = idsgmv_equipo;
		this.descripcion = descripcion;
		this.inspeccionado_por = inspeccionado_por;
		this.ok = ok;
	}

	public int getIdsgmv_equipo() {
		return idsgmv_equipo;
	}

	public void setIdsgmv_equipo(int idsgmv_equipo) {
		this.idsgmv_equipo = idsgmv_equipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getInspeccionado_por() {
		return inspeccionado_por;
	}

	public void setInspeccionado_por(String insepeccionado_por) {
		this.inspeccionado_por = insepeccionado_por;
	}

	public int getOk() {
		return ok;
	}

	public void setOk(int ok) {
		this.ok = ok;
	}

}
