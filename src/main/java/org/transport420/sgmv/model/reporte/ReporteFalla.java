package org.transport420.sgmv.model.reporte;

import java.util.List;

import org.transport420.sgmv.model.Empleado;
import org.transport420.sgmv.model.Vehiculo;

public class ReporteFalla {

	private String cod_reporte;
	private String fecha;
	private float kilometraje;
	private int procedencia;
	private Vehiculo remolque;
	private Vehiculo semiremolque;
	private Empleado conductor;
	private String observaciones;
	private List<ComponentePrincipal> componentes_principales;
	private List<ComponenteLlanta> componentes_llantas;

	public ReporteFalla() {

	}

	public ReporteFalla(String cod_reporte, String fecha, float kilometraje, int procedencia, Vehiculo remolque,
			Vehiculo semiremolque, Empleado conductor, String observaciones,
			List<ComponentePrincipal> componentes_principales, List<ComponenteLlanta> componentes_llantas) {
		super();
		this.cod_reporte = cod_reporte;
		this.fecha = fecha;
		this.kilometraje = kilometraje;
		this.procedencia = procedencia;
		this.remolque = remolque;
		this.semiremolque = semiremolque;
		this.conductor = conductor;
		this.observaciones = observaciones;
		this.componentes_principales = componentes_principales;
		this.componentes_llantas = componentes_llantas;
	}

	public String getCod_reporte() {
		return cod_reporte;
	}

	public void setCod_reporte(String cod_reporte) {
		this.cod_reporte = cod_reporte;
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

	public int getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(int procedencia) {
		this.procedencia = procedencia;
	}

	public Vehiculo getRemolque() {
		return remolque;
	}

	public void setRemolque(Vehiculo remolque) {
		this.remolque = remolque;
	}

	public Vehiculo getSemiremolque() {
		return semiremolque;
	}

	public void setSemiremolque(Vehiculo semiremolque) {
		this.semiremolque = semiremolque;
	}

	public Empleado getConductor() {
		return conductor;
	}

	public void setConductor(Empleado conductor) {
		this.conductor = conductor;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public List<ComponentePrincipal> getComponentes_principales() {
		return componentes_principales;
	}

	public void setComponentes_principales(List<ComponentePrincipal> componentes_principales) {
		this.componentes_principales = componentes_principales;
	}

	public List<ComponenteLlanta> getComponentes_llantas() {
		return componentes_llantas;
	}

	public void setComponentes_llantas(List<ComponenteLlanta> componentes_llantas) {
		this.componentes_llantas = componentes_llantas;
	}

}
