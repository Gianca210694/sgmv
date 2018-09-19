package org.transport420.sgmv.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Seguro {

	private int idsgmv_seguro;
	private Vehiculo vehiculo;
	private String referencia;
	private String poliza;
	private float precio;
	private String operacion;
	private String fecha_inicio;
	private String fecha_fin;
	private int estado;

	public Seguro() {

	}

	public Seguro(int idsgmv_seguro, Vehiculo vehiculo, String referencia, String poliza, float precio,
			String operacion, String fecha_inicio, String fecha_fin, int estado) {
		super();
		this.idsgmv_seguro = idsgmv_seguro;
		this.vehiculo = vehiculo;
		this.referencia = referencia;
		this.poliza = poliza;
		this.precio = precio;
		this.operacion = operacion;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.estado = estado;
	}

	public int getIdsgmv_seguro() {
		return idsgmv_seguro;
	}

	public void setIdsgmv_seguro(int idsgmv_seguro) {
		this.idsgmv_seguro = idsgmv_seguro;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getPoliza() {
		return poliza;
	}

	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
