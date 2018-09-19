package org.transport420.sgmv.resources.beans;

import javax.ws.rs.QueryParam;

public class OrdenesMantenimientoFilterBean {

	private @QueryParam("remolque") int remolque;
	private @QueryParam("semiremolque") int semiremolque;

	public int getRemolque() {
		return remolque;
	}

	public void setRemolque(int remolque) {
		this.remolque = remolque;
	}

	public int getSemiremolque() {
		return semiremolque;
	}

	public void setSemiremolque(int semiremolque) {
		this.semiremolque = semiremolque;
	}
	
}
