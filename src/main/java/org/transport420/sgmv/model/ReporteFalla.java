package org.transport420.sgmv.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReporteFalla {

	private int idsgmv_reporte_falla;
	private String cod_reporte;
	private String fecha;
	private float kilometraje;
	private String procedencia;
	private String destino;
	private String observaciones;
	private Vehiculo remolque;
	private Vehiculo semiremolque;
	private Empleado empleado;
	private Empleado conductor;
	private int estado;
	private List<ReporteFallaComponentePrincipal> componentes_principales;
	private List<ReporteFallaComponenteLlanta> componentes_llantas;

	public ReporteFalla() {

	}

	public ReporteFalla(int idsgmv_reporte_falla, String cod_reporte, String fecha, float kilometraje,
			String procedencia, String destino, String observaciones, Vehiculo remolque, Vehiculo semiremolque, Empleado empleado,
			Empleado conductor, int estado, List<ReporteFallaComponentePrincipal> componentes_principales,
			List<ReporteFallaComponenteLlanta> componentes_llantas) {
		super();
		this.idsgmv_reporte_falla = idsgmv_reporte_falla;
		this.cod_reporte = cod_reporte;
		this.fecha = fecha;
		this.kilometraje = kilometraje;
		this.procedencia = procedencia;
		this.destino = destino;
		this.observaciones = observaciones;
		this.remolque = remolque;
		this.semiremolque = semiremolque;
		this.empleado = empleado;
		this.conductor = conductor;
		this.estado = estado;
		this.componentes_principales = componentes_principales;
		this.componentes_llantas = componentes_llantas;
	}

	public int getIdsgmv_reporte_falla() {
		return idsgmv_reporte_falla;
	}

	public void setIdsgmv_reporte_falla(int idsgmv_reporte_falla) {
		this.idsgmv_reporte_falla = idsgmv_reporte_falla;
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

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Empleado getConductor() {
		return conductor;
	}

	public void setConductor(Empleado conductor) {
		this.conductor = conductor;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public List<ReporteFallaComponentePrincipal> getComponentes_principales() {
		return componentes_principales;
	}

	public void setComponentes_principales(List<ReporteFallaComponentePrincipal> componentes_principales) {
		this.componentes_principales = componentes_principales;
	}

	public List<ReporteFallaComponenteLlanta> getComponentes_llantas() {
		return componentes_llantas;
	}

	public void setComponentes_llantas(List<ReporteFallaComponenteLlanta> componentes_llantas) {
		this.componentes_llantas = componentes_llantas;
	}

}
