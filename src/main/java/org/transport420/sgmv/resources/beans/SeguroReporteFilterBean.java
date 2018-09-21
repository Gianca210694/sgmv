package org.transport420.sgmv.resources.beans;

import javax.ws.rs.QueryParam;

public class SeguroReporteFilterBean {
	private @QueryParam("estado") int estado;

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}
