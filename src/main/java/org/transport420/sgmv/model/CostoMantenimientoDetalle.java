package org.transport420.sgmv.model;

public class CostoMantenimientoDetalle {

	private int idsgmv_costo_mantenimiento;
	private int tipo_vehiculo;
	private Vehiculo vehiculo;
	private String actividad;
	private String comprobante;
	private float costo;

	public CostoMantenimientoDetalle() {

	}

	public CostoMantenimientoDetalle(int idsgmv_costo_mantenimiento, int tipo_vehiculo, Vehiculo vehiculo,
			String actividad, String comprobante, float costo) {
		super();
		this.idsgmv_costo_mantenimiento = idsgmv_costo_mantenimiento;
		this.tipo_vehiculo = tipo_vehiculo;
		this.vehiculo = vehiculo;
		this.actividad = actividad;
		this.comprobante = comprobante;
		this.costo = costo;
	}

	public int getIdsgmv_costo_mantenimiento() {
		return idsgmv_costo_mantenimiento;
	}

	public void setIdsgmv_costo_mantenimiento(int idsgmv_costo_mantenimiento) {
		this.idsgmv_costo_mantenimiento = idsgmv_costo_mantenimiento;
	}

	public int getTipo_vehiculo() {
		return tipo_vehiculo;
	}

	public void setTipo_vehiculo(int tipo_vehiculo) {
		this.tipo_vehiculo = tipo_vehiculo;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

}
