package org.transport420.sgmv.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrdenMantenimiento {

	private int idsgmv_orden_mantenimiento;
	private String cod_mantenimiento_orden;
	private int tipo_mantenimiento;
	private String fecha;
	private float kilometraje;
	private int prioridad;
	private ReporteFalla averia;
	private Empleado empleado;
	private String observaciones;
	private int estado;
	private List<Actividad> actividades;
	private List<Defecto> defectos;
	private List<Equipo> equipos;

	public OrdenMantenimiento() {

	}

	public OrdenMantenimiento(int idsgmv_orden_mantenimiento, String cod_mantenimiento_orden, int tipo_mantenimiento,
			String fecha, float kilometraje, int prioridad, ReporteFalla averia, Empleado empleado,
			String observaciones, int estado, List<Actividad> actividades, List<Defecto> defectos,
			List<Equipo> equipos) {
		super();
		this.idsgmv_orden_mantenimiento = idsgmv_orden_mantenimiento;
		this.cod_mantenimiento_orden = cod_mantenimiento_orden;
		this.tipo_mantenimiento = tipo_mantenimiento;
		this.fecha = fecha;
		this.kilometraje = kilometraje;
		this.prioridad = prioridad;
		this.averia = averia;
		this.empleado = empleado;
		this.observaciones = observaciones;
		this.estado = estado;
		this.actividades = actividades;
		this.defectos = defectos;
		this.equipos = equipos;
	}

	public int getIdsgmv_orden_mantenimiento() {
		return idsgmv_orden_mantenimiento;
	}

	public void setIdsgmv_orden_mantenimiento(int idsgmv_orden_mantenimiento) {
		this.idsgmv_orden_mantenimiento = idsgmv_orden_mantenimiento;
	}

	public String getCod_mantenimiento_orden() {
		return cod_mantenimiento_orden;
	}

	public void setCod_mantenimiento_orden(String cod_mantenimiento_orden) {
		this.cod_mantenimiento_orden = cod_mantenimiento_orden;
	}

	public int getTipo_mantenimiento() {
		return tipo_mantenimiento;
	}

	public void setTipo_mantenimiento(int tipo_mantenimiento) {
		this.tipo_mantenimiento = tipo_mantenimiento;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public float getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(float kilometraje) {
		this.kilometraje = kilometraje;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public ReporteFalla getAveria() {
		return averia;
	}

	public void setAveria(ReporteFalla averia) {
		this.averia = averia;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
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

}
