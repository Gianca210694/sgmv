package org.transport420.sgmv.servicio;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import org.transport420.sgmv.dao.interfaces.IReporteFallaRepositorio;
import org.transport420.sgmv.daofactory.DAOFactory;
import org.transport420.sgmv.model.ReporteFalla;
import org.transport420.sgmv.resources.beans.FechaFilterBean;
import org.transport420.sgmv.resources.beans.ReporteFallasFilterBean;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class AveriaServicio {

	IReporteFallaRepositorio reporteFallaRepositorio = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getReporteFalla();

	public List<ReporteFalla> listarAverias(ReporteFallasFilterBean filterBean) {
		try {
			return reporteFallaRepositorio.listarAverias(filterBean);
		} catch (Exception e) {
			return null;
		}
	}

	public ReporteFalla crearAveria(ReporteFalla averia) {
		try {
			return reporteFallaRepositorio.crearAveria(averia);
		} catch (Exception e) {
			return null;
		}
	}

	public ReporteFalla obtenerAveria(int idsgmv_reporte_falla) {
		try {
			return reporteFallaRepositorio.obtenerAveria(idsgmv_reporte_falla);
		} catch (Exception e) {
			return null;
		}
	}

	public ReporteFalla editarAveria(ReporteFalla averia) {
		try {
			return reporteFallaRepositorio.editarAveria(averia);
		} catch (Exception e) {
			return null;
		}
	}

	public void eliminarAveria(int idsgmv_reporte_falla) {
		try {
			reporteFallaRepositorio.eliminarAveria(idsgmv_reporte_falla);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public StreamingOutput exportarAveria(FechaFilterBean filterBean) {
		try {

			final List<ReporteFalla> averias = reporteFallaRepositorio.reporteAverias(filterBean);
			StreamingOutput stream = new StreamingOutput() {
				public void write(OutputStream output) throws IOException, WebApplicationException {
					try {
						WorkbookSettings conf = new WorkbookSettings();
						conf.setEncoding("ISO-8859-1");
						WritableWorkbook workBoook = Workbook.createWorkbook(output, conf);
						WritableSheet sheet = workBoook.createSheet("Averías", 0);
						WritableFont h = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
						WritableCellFormat hFormat = new WritableCellFormat(h);
						hFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
						hFormat.setWrap(true);
						int filaCount = 0;
						sheet.addCell(new Label(0, filaCount, "CÓDIGO AVERIA", hFormat));
						sheet.addCell(new Label(1, filaCount, "FECHA", hFormat));
						sheet.addCell(new Label(2, filaCount, "KILOMETRAJE AVERIA", hFormat));
						sheet.addCell(new Label(3, filaCount, "PROCEDENCIA", hFormat));
						sheet.addCell(new Label(4, filaCount, "DESTINO", hFormat));
						sheet.addCell(new Label(5, filaCount, "PLACA TRAILER", hFormat));
						sheet.addCell(new Label(6, filaCount, "KILOMETRAJE TRAILER", hFormat));
						sheet.addCell(new Label(7, filaCount, "PLACA SEMITRAILER", hFormat));
						sheet.addCell(new Label(8, filaCount, "KILOMETRAJE SEMITRAILER", hFormat));
						sheet.addCell(new Label(9, filaCount, "CONDUCTOR", hFormat));
						filaCount++;
						for (ReporteFalla averia : averias) {
							sheet.addCell(new Label(0, filaCount, averia.getCod_reporte(), hFormat));
							sheet.addCell(new Label(1, filaCount, averia.getFecha(), hFormat));
							sheet.addCell(new Label(2, filaCount, "" + averia.getKilometraje(), hFormat));
							sheet.addCell(new Label(3, filaCount, averia.getProcedencia(), hFormat));
							sheet.addCell(new Label(4, filaCount, averia.getDestino(), hFormat));
							sheet.addCell(new Label(5, filaCount, averia.getRemolque().getPlaca(), hFormat));
							sheet.addCell(
									new Label(6, filaCount, "" + averia.getRemolque().getKilometraje_total(), hFormat));
							sheet.addCell(new Label(7, filaCount, averia.getSemiremolque().getPlaca(), hFormat));
							sheet.addCell(new Label(8, filaCount, "" + averia.getSemiremolque().getKilometraje_total(),
									hFormat));
							sheet.addCell(new Label(9, filaCount,
									averia.getConductor().getNombres() + " " + averia.getConductor().getApe_paterno(),
									hFormat));
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
