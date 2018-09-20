package org.transport420.sgmv.resources.beans;

import javax.ws.rs.QueryParam;

public class VehiculosFilterBean {

	private @QueryParam("placa") String placa;
	private @QueryParam("clase") String clase;
	private @QueryParam("estado") int estado;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
