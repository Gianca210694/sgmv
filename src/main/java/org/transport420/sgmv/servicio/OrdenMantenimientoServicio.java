package org.transport420.sgmv.servicio;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import org.transport420.sgmv.dao.interfaces.IOrdenMantenimientoRepositorio;
import org.transport420.sgmv.daofactory.DAOFactory;
import org.transport420.sgmv.model.OrdenMantenimiento;
import org.transport420.sgmv.resources.beans.FechaFilterBean;
import org.transport420.sgmv.resources.beans.OrdenesMantenimientoFilterBean;
import org.transport420.sgmv.util.Util;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class OrdenMantenimientoServicio {

	IOrdenMantenimientoRepositorio ordenMantenimientoRepositorio = DAOFactory.getDAOFactory(DAOFactory.MYSQL)
			.getOrdenMantenimiento();

	public List<OrdenMantenimiento> listarOrdenesMantenimiento(OrdenesMantenimientoFilterBean filterBean) {
		try {
			return ordenMantenimientoRepositorio.listarOrdenesMantenimiento(filterBean);
		} catch (Exception e) {
			return null;
		}
	}

	public OrdenMantenimiento crearOrdenMantenimiento(OrdenMantenimiento ordenMantenimiento) {
		try {
			return ordenMantenimientoRepositorio.crearOrdenMantenimiento(ordenMantenimiento);
		} catch (Exception e) {
			return null;
		}
	}

	public OrdenMantenimiento obtenerOrdenMantenimiento(int idsgmv_orden_mantenimiento) {
		try {
			return ordenMantenimientoRepositorio.obtenerOrdenMantenimiento(idsgmv_orden_mantenimiento);
		} catch (Exception e) {
			return null;
		}
	}

	public OrdenMantenimiento editarOrdenMantenimiento(OrdenMantenimiento ordenMantenimiento) {
		try {
			return ordenMantenimientoRepositorio.editarOrdenMantenimiento(ordenMantenimiento);
		} catch (Exception e) {
			return null;
		}
	}

	public void eliminarOrdenMantenimiento(int idsgmv_orden_mantenimiento) {
		try {
			ordenMantenimientoRepositorio.eliminarOrdenMantenimiento(idsgmv_orden_mantenimiento);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public StreamingOutput exportarOrdenMantenimiento(FechaFilterBean filterBean) {
		try {

			final List<OrdenMantenimiento> ordenes = ordenMantenimientoRepositorio
					.reporteOrdenesMantenimiento(filterBean);
			StreamingOutput stream = new StreamingOutput() {
				public void write(OutputStream output) throws IOException, WebApplicationException {
					try {
						WorkbookSettings conf = new WorkbookSettings();
						conf.setEncoding("ISO-8859-1");
						WritableWorkbook workBoook = Workbook.createWorkbook(output, conf);
						WritableSheet sheet = workBoook.createSheet("Ordenes de Mantenimiento", 0);
						WritableFont h = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
						WritableCellFormat hFormat = new WritableCellFormat(h);
						hFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
						hFormat.setWrap(true);
						int filaCount = 0;
						sheet.addCell(new Label(0, filaCount, "CÓDIGO ORDEN DE MANTENIMIENTO", hFormat));
						sheet.addCell(new Label(1, filaCount, "TIPO DE MANTENIMIENTO", hFormat));
						sheet.addCell(new Label(2, filaCount, "PRIORIDAD", hFormat));
						sheet.addCell(new Label(3, filaCount, "FECHA", hFormat));
						sheet.addCell(new Label(4, filaCount, "KILOMETRAJE", hFormat));
						sheet.addCell(new Label(5, filaCount, "CÓDIGO AVERÍA", hFormat));
						filaCount++;
						for (OrdenMantenimiento orden : ordenes) {
							sheet.addCell(new Label(0, filaCount, orden.getCod_mantenimiento_orden(), hFormat));
							sheet.addCell(new Label(1, filaCount,
									Util.getTipoMantenimiento(orden.getTipo_mantenimiento()), hFormat));
							sheet.addCell(new Label(2, filaCount, Util.getPrioridad(orden.getPrioridad()), hFormat));
							sheet.addCell(new Label(3, filaCount, orden.getFecha(), hFormat));
							sheet.addCell(new Label(4, filaCount, "" + orden.getKilometraje(), hFormat));
							sheet.addCell(new Label(5, filaCount, "" + orden.getAveria().getCod_reporte(), hFormat));
							filaCount++;
						}
						workBoook.write();
						workBoook.close();
					} catch (Exception e) {
						throw new WebApplicationException(e);
					}
				}
			};
			return stream;
		} catch (Exception e) {
			return null;
		}
	}

	public StreamingOutput exportarOrdenMantenimientoPDF(int idsgmv_orden_mantenimiento) {
		try {
			final org.transport420.sgmv.model.reporte.OrdenMantenimiento mantenimiento = ordenMantenimientoRepositorio
					.exportarOrdenMantenimientoPDF(idsgmv_orden_mantenimiento);
			StreamingOutput stream = new StreamingOutput() {
				public void write(OutputStream output) throws IOException, WebApplicationException {
					try {
						String rutaReporte = this.getClass().getClassLoader()
								.getResource("reportes/ReporteMantenimiento.jrxml").getPath();
						JasperReport jr = JasperCompileManager.compileReport(rutaReporte);
						JRDataSource jrDataSource = new JREmptyDataSource();
						Map<String, Object> parametros = new HashMap<String, Object>();

						JRBeanCollectionDataSource actividadesDataSource = new JRBeanCollectionDataSource(
								mantenimiento.getActividades());
						JRBeanCollectionDataSource defectosDataSource = new JRBeanCollectionDataSource(
								mantenimiento.getDefectos());
						JRBeanCollectionDataSource equiposDataSource = new JRBeanCollectionDataSource(
								mantenimiento.getEquipos());

						parametros.put("codMantenimiento", mantenimiento.getCod_mantenimiento_orden());
						parametros.put("prioridad", mantenimiento.getPrioridad());
						parametros.put("fechaMantenimiento", (new Date()).toLocaleString());
						parametros.put("placaTrailer", mantenimiento.getPlaca_remolque());
						parametros.put("placaSemitrailer", mantenimiento.getPlaca_semiremolque());
						parametros.put("fecha", mantenimiento.getFecha());
						parametros.put("kilometraje", "" + mantenimiento.getKilometraje());
						parametros.put("ActividadesDataSource", actividadesDataSource);
						parametros.put("DefectosDataSource", defectosDataSource);
						parametros.put("EquiposDataSource", equiposDataSource);
						parametros.put("observaciones", mantenimiento.getObservaciones());
						parametros.put("tipoMantenimiento", mantenimiento.getTipo_mantenimiento());
						JasperPrint jrPrint = JasperFillManager.fillReport(jr, parametros, jrDataSource);
						JasperExportManager.exportReportToPdfStream(jrPrint, output);
					} catch (Exception e) {
						throw new WebApplicationException(e);
					}
				}
			};
			return stream;
		} catch (Exception e) {
			return null;
		}
	}

}
