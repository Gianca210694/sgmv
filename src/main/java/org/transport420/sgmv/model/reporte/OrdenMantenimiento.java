package org.transport420.sgmv.model.reporte;

import java.util.List;

public class OrdenMantenimiento {

	private String cod_mantenimiento_orden;
	private String tipo_mantenimiento;
	private String prioridad;
	private String fecha;
	private String kilometraje;
	private ReporteFalla averia;
	private String placa_remolque;
	private String placa_semiremolque;
	private String observaciones;
	private List<Actividad> actividades;
	private List<Defecto> defectos;
	private List<Equipo> equipos;

	public OrdenMantenimiento() {

	}

	public OrdenMantenimiento(String cod_mantenimiento_orden, String tipo_mantenimiento, String prioridad, String fecha,
			String kilometraje, ReporteFalla averia, String placa_remolque, String placa_semiremolque,
			String observaciones, List<Actividad> actividades, List<Defecto> defectos, List<Equipo> equipos) {
		super();
		this.cod_mantenimiento_orden = cod_mantenimiento_orden;
		this.tipo_mantenimiento = tipo_mantenimiento;
		this.prioridad = prioridad;
		this.fecha = fecha;
		this.kilometraje = kilometraje;
		this.averia = averia;
		this.placa_remolque = placa_remolque;
		this.placa_semiremolque = placa_semiremolque;
		this.observaciones = observaciones;
		this.actividades = actividades;
		this.defectos = defectos;
		this.equipos = equipos;
	}

	public String getCod_mantenimiento_orden() {
		return cod_mantenimiento_orden;
	}

	public void setCod_mantenimiento_orden(String cod_mantenimiento_orden) {
		this.cod_mantenimiento_orden = cod_mantenimiento_orden;
	}

	public String getTipo_mantenimiento() {
		return tipo_mantenimiento;
	}

	public void setTipo_mantenimiento(String tipo_mantenimiento) {
		this.tipo_mantenimiento = tipo_mantenimiento;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(String kilometraje) {
		this.kilometraje = kilometraje;
	}

	public ReporteFalla getAveria() {
		return averia;
	}

	public void setAveria(ReporteFalla averia) {
		this.averia = averia;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	public List<Defecto> getDefectos() {
		return defectos;
	}

	public void setDefectos(List<Defecto> defectos) {
		this.defectos = defectos;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public String getPlaca_remolque() {
		return placa_remolque;
	}

	public void setPlaca_remolque(String placa_remolque) {
		this.placa_remolque = placa_remolque;
	}

	public String getPlaca_semiremolque() {
		return placa_semiremolque;
	}

	public void setPlaca_semiremolque(String placa_semiremolque) {
		this.placa_semiremolque = placa_semiremolque;
	}

}
