package org.transport420.sgmv.resources.beans;

import javax.ws.rs.QueryParam;

public class UsuariosFilterBean {

	private @QueryParam("dni") String dni;
	private @QueryParam("rol") int rol;
	private @QueryParam("estado") int estado;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}
