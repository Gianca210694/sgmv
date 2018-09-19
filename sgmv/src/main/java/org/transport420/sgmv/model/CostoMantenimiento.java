package org.transport420.sgmv.model;

import java.util.List;

public class CostoMantenimiento {

	private int idsgmv_costo_mantenimiento;
	private String cod_costo_mantenimiento;
	private OrdenMantenimiento ordenMantenimiento;
	private float costo_total;
	private String fecha;
	private Vehiculo vehiculo;
	private float costo_vehiculo;
	private List<CostoMantenimientoDetalle> detalle;

	public CostoMantenimiento() {

	}

	public CostoMantenimiento(int idsgmv_costo_mantenimiento, String cod_costo_mantenimiento,
			OrdenMantenimiento ordenMantenimiento, float costo_total, String fecha, Vehiculo vehiculo,
			float costo_vehiculo, List<CostoMantenimientoDetalle> detalle) {
		super();
		this.idsgmv_costo_mantenimiento = idsgmv_costo_mantenimiento;
		this.cod_costo_mantenimiento = cod_costo_mantenimiento;
		this.ordenMantenimiento = ordenMantenimiento;
		this.costo_total = costo_total;
		this.fecha = fecha;
		this.vehiculo = vehiculo;
		this.costo_vehiculo = costo_vehiculo;
		this.detalle = detalle;
	}

	public int getIdsgmv_costo_mantenimiento() {
		return idsgmv_costo_mantenimiento;
	}

	public void setIdsgmv_costo_mantenimiento(int idsgmv_costo_mantenimiento) {
		this.idsgmv_costo_mantenimiento = idsgmv_costo_mantenimiento;
	}

	public String getCod_costo_mantenimiento() {
		return cod_costo_mantenimiento;
	}

	public void setCod_costo_mantenimiento(String cod_costo_mantenimiento) {
		this.cod_costo_mantenimiento = cod_costo_mantenimiento;
	}

	public OrdenMantenimiento getOrdenMantenimiento() {
		return ordenMantenimiento;
	}

	public void setOrdenMantenimiento(OrdenMantenimiento ordenMantenimiento) {
		this.ordenMantenimiento = ordenMantenimiento;
	}

	public float getCosto_total() {
		return costo_total;
	}

	public void setCosto_total(float costo_total) {
		this.costo_total = costo_total;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public float getCosto_vehiculo() {
		return costo_vehiculo;
	}

	public void setCosto_vehiculo(float costo_vehiculo) {
		this.costo_vehiculo = costo_vehiculo;
	}

	public List<CostoMantenimientoDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<CostoMantenimientoDetalle> detalle) {
		this.detalle = detalle;
	}

}
