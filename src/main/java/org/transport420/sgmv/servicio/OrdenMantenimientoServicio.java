package org.transport420.sgmv.servicio;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import org.transport420.sgmv.dao.interfaces.IOrdenMantenimientoRepositorio;
import org.transport420.sgmv.daofactory.DAOFactory;
import org.transport420.sgmv.model.OrdenMantenimiento;
import org.transport420.sgmv.resources.beans.FechaFilterBean;
import org.transport420.sgmv.resources.beans.OrdenesMantenimientoFilterBean;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

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
							sheet.addCell(new Label(1, filaCount, "" + orden.getTipo_mantenimiento(), hFormat));
							sheet.addCell(new Label(2, filaCount, "" + orden.getPrioridad(), hFormat));
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

}
