package org.transport420.sgmv.model.reporte;

public class Actividad {

	private String actividad;
	private String ejecutado_por;
	private String fecha_programacion;
	private String fecha_ejecucion;

	public Actividad() {

	}

	public Actividad(String actividad, String ejecutado_por, String fecha_programacion, String fecha_ejecucion) {
		super();
		this.actividad = actividad;
		this.ejecutado_por = ejecutado_por;
		this.fecha_programacion = fecha_programacion;
		this.fecha_ejecucion = fecha_ejecucion;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getEjecutado_por() {
		return ejecutado_por;
	}

	public void setEjecutado_por(String ejecutado_por) {
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
