package org.transport420.sgmv.resources.beans;

import javax.ws.rs.QueryParam;

public class CostosMantenimientoFilterBean {

	private @QueryParam("vehiculo") int vehiculo;

	public int getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(int vehiculo) {
		this.vehiculo = vehiculo;
	}

}
