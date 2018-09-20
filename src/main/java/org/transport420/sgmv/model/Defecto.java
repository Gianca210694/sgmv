package org.transport420.sgmv.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Defecto {

	private int idsgmv_defecto;
	private String descripcion;
	private Empleado reportado_ver_por;
	private String fecha_reporte;
	private String fecha_inspeccion;

	public Defecto() {

	}

	public Defecto(int idsgmv_defecto, String descripcion, Empleado reportado_ver_por,
			String fecha_reporte, String fecha_inspeccion) {
		super();
		this.idsgmv_defecto = idsgmv_defecto;
		this.descripcion = descripcion;
		this.reportado_ver_por = reportado_ver_por;
		this.fecha_reporte = fecha_reporte;
		this.fecha_inspeccion = fecha_inspeccion;
	}

	public int getIdsgmv_defecto() {
		return idsgmv_defecto;
	}

	public void setIdsgmv_defecto(int idsgmv_defecto) {
		this.idsgmv_defecto = idsgmv_defecto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Empleado getReportado_ver_por() {
		return reportado_ver_por;
	}

	public void setReportado_ver_por(Empleado reportado_ver_por) {
		this.reportado_ver_por = reportado_ver_por;
	}

	public String getFecha_reporte() {
		return fecha_reporte;
	}

	public void setFecha_reporte(String fecha_reporte) {
		this.fecha_reporte = fecha_reporte;
	}

	public String getFecha_inspeccion() {
		return fecha_inspeccion;
	}

	public void setFecha_inspeccion(String fecha_inspeccion) {
		this.fecha_inspeccion = fecha_inspeccion;
	}

}
