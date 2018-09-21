package org.transport420.sgmv.resources.beans;

import javax.ws.rs.QueryParam;

public class FechaFilterBean {
	private @QueryParam("meses") int meses;

	public int getMeses() {
		return meses;
	}

	public void setMeses(int meses) {
		this.meses = meses;
	}
}
