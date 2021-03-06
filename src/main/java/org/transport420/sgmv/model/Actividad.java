package org.transport420.sgmv.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Actividad {

	private int idsgmv_actividad;
	private String descripcion;
	private int ejecutado_por;
	private String fecha_programacion;
	private String fecha_ejecucion;

	public Actividad() {

	}

	public Actividad(int idsgmv_actividad, String descripcion, int ejecutado_por,
			String fecha_programacion, String fecha_ejecucion) {
		super();
		this.idsgmv_actividad = idsgmv_actividad;
		this.descripcion = descripcion;
		this.ejecutado_por = ejecutado_por;
		this.fecha_programacion = fecha_programacion;
		this.fecha_ejecucion = fecha_ejecucion;
	}

	public int getIdsgmv_actividad() {
		return idsgmv_actividad;
	}

	public void setIdsgmv_actividad(int idsgmv_actividad) {
		this.idsgmv_actividad = idsgmv_actividad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEjecutado_por() {
		return ejecutado_por;
	}

	public void setEjecutado_por(int ejecutado_por) {
		this.ejecutado_por = ejecutado_por;
	}

	public String getFecha_programacion() {
		return fecha_programacion;
	}

	public void setFecha_programacion(String fecha_programacion) {
		this.fecha_programacion = fecha_programacion;
	}

	public String getFecha_ejecucion() {
		return fecha_ejecucion;
	}

	public void setFecha_ejecucion(String fecha_ejecucion) {
		this.fecha_ejecucion = fecha_ejecucion;
	}

}
