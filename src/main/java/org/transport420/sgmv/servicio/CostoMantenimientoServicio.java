package org.transport420.sgmv.servicio;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import org.transport420.sgmv.dao.interfaces.ICostoMantenimientoRepositorio;
import org.transport420.sgmv.daofactory.DAOFactory;
import org.transport420.sgmv.model.CostoMantenimiento;
import org.transport420.sgmv.resources.beans.CostosMantenimientoFilterBean;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CostoMantenimientoServicio {
	ICostoMantenimientoRepositorio costoMantenimientoRepositorio = DAOFactory.getDAOFactory(DAOFactory.MYSQL)
			.getCostoMantenimiento();

	public List<CostoMantenimiento> listarCostosMantenimiento(CostosMantenimientoFilterBean filterBean) {
		try {
			return costoMantenimientoRepositorio.listarCostosMantenimiento(filterBean);
		} catch (Exception e) {
			return null;
		}
	}

	public CostoMantenimiento crearCostoMantenimiento(CostoMantenimiento costoMantenimiento) {
		try {
			return costoMantenimientoRepositorio.crearCostoMantenimiento(costoMantenimiento);
		} catch (Exception e) {
			return null;
		}
	}

	public CostoMantenimiento obtenerCostoMantenimiento(int idsgmv_costo_mantenimiento) {
		try {
			return costoMantenimientoRepositorio.obtenerCostoMantenimiento(idsgmv_costo_mantenimiento);
		} catch (Exception e) {
			return null;
		}
	}

	public CostoMantenimiento editarCostoMantenimiento(CostoMantenimiento costoMantenimiento) {
		try {
			return costoMantenimientoRepositorio.editarCostoMantenimiento(costoMantenimiento);
		} catch (Exception e) {
			return null;
		}
	}

	public void eliminarCostoMantenimiento(int idsgmv_costo_mantenimiento) {
		try {
			costoMantenimientoRepositorio.eliminarCostoMantenimiento(idsgmv_costo_mantenimiento);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public StreamingOutput exportarCosto(CostosMantenimientoFilterBean filterBean) {
		try {

			final List<CostoMantenimiento> costos = costoMantenimientoRepositorio.listarCostosMantenimiento(filterBean);
			StreamingOutput stream = new StreamingOutput() {
				public void write(OutputStream output) throws IOException, WebApplicationException {
					try {
						WorkbookSettings conf = new WorkbookSettings();
						conf.setEncoding("ISO-8859-1");
						WritableWorkbook workBoook = Workbook.createWorkbook(output, conf);
						WritableSheet sheet = workBoook.createSheet("Costos de Mantenimiento", 0);
						WritableFont h = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
						WritableCellFormat hFormat = new WritableCellFormat(h);
						hFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
						hFormat.setWrap(true);
						int filaCount = 0;
						sheet.addCell(new Label(0, filaCount, "MARCA", hFormat));
						sheet.addCell(new Label(1, filaCount, "MODELO", hFormat));
						sheet.addCell(new Label(2, filaCount, "PLACA", hFormat));
						sheet.addCell(new Label(3, filaCount, "CÓDIGO", hFormat));
						sheet.addCell(new Label(4, filaCount, "ORDEN DE MANTENIMIENTO", hFormat));
						sheet.addCell(new Label(5, filaCount, "FECHA", hFormat));
						sheet.addCell(new Label(6, filaCount, "COSTO POR VEHÍCULO", hFormat));
						filaCount++;
						for (CostoMantenimiento costo : costos) {
							sheet.addCell(new Label(0, filaCount, costo.getVehiculo().getMarca(), hFormat));
							sheet.addCell(new Label(1, filaCount, costo.getVehiculo().getModelo(), hFormat));
							sheet.addCell(new Label(2, filaCount, costo.getVehiculo().getPlaca(), hFormat));
							sheet.addCell(new Label(3, filaCount, costo.getCod_costo_mantenimiento(), hFormat));
							sheet.addCell(new Label(4, filaCount, costo.getOrdenMantenimiento().getCod_mantenimiento_orden(), hFormat));
							sheet.addCell(new Label(5, filaCount, costo.getFecha(), hFormat));
							sheet.addCell(new Label(6, filaCount, "" + costo.getCosto_vehiculo(), hFormat));
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
