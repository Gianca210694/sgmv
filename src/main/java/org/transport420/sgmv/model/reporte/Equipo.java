package org.transport420.sgmv.model.reporte;

public class Equipo {

	private String equipo;
	private String inspeccionado_por;
	private String ok;

	public Equipo() {

	}

	public Equipo(String equipo, String inspeccionado_por, String ok) {
		super();
		this.equipo = equipo;
		this.inspeccionado_por = inspeccionado_por;
		this.ok = ok;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public String getInspeccionado_por() {
		return inspeccionado_por;
	}

	public void setInspeccionado_por(String inspeccionado_por) {
		this.inspeccionado_por = inspeccionado_por;
	}

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

}
