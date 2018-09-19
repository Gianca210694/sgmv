package org.transport420.sgmv.resources.beans;

import javax.ws.rs.QueryParam;

public class SegurosFilterBean {

	private @QueryParam("idVehiculo") int idVehiculo;

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

}
