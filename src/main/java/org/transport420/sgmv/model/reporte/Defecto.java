package org.transport420.sgmv.model.reporte;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Defecto {

	private String defecto;
	private String reportado_verificado_por;
	private String fecha_reporte;
	private String fecha_inspeccion;

	public Defecto() {

	}

	public Defecto(String defecto, String reportado_verificado_por, String fecha_reporte, String fecha_inspeccion) {
		super();
		this.defecto = defecto;
		this.reportado_verificado_por = reportado_verificado_por;
		this.fecha_reporte = fecha_reporte;
		this.fecha_inspeccion = fecha_inspeccion;
	}

	public String getDefecto() {
		return defecto;
	}

	public void setDefecto(String defecto) {
		this.defecto = defecto;
	}

	public String getReportado_verificado_por() {
		return reportado_verificado_por;
	}

	public void setReportado_verificado_por(String reportado_verificado_por) {
		this.reportado_verificado_por = reportado_verificado_por;
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
