package org.transport420.sgmv.servicio;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import org.transport420.sgmv.dao.interfaces.ISeguroRepositorio;
import org.transport420.sgmv.daofactory.DAOFactory;
import org.transport420.sgmv.model.Seguro;
import org.transport420.sgmv.resources.beans.SeguroReporteFilterBean;
import org.transport420.sgmv.resources.beans.SegurosFilterBean;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class SeguroServicio {

	ISeguroRepositorio seguroRepositorio = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getSeguro();

	public List<Seguro> listarSeguros(SegurosFilterBean filterBean) {
		try {
			return seguroRepositorio.listarSeguros(filterBean);
		} catch (Exception e) {
			return null;
		}
	}

	public Seguro crearSeguro(Seguro seguro) {
		try {
			return seguroRepositorio.crearSeguro(seguro);
		} catch (Exception e) {
			return null;
		}
	}

	public Seguro obtenerSeguro(int idsgmv_seguro) {
		try {
			return seguroRepositorio.obtenerSeguro(idsgmv_seguro);
		} catch (Exception e) {
			return null;
		}
	}

	public Seguro editarSeguro(Seguro seguro) {
		try {
			return seguroRepositorio.editarSeguro(seguro);
		} catch (Exception e) {
			return null;
		}
	}

	public void eliminarSeguro(int idsgmv_seguro) {
		try {
			seguroRepositorio.eliminarSeguro(idsgmv_seguro);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public StreamingOutput exportarSeguro(SeguroReporteFilterBean filterBean) {
		try {

			final List<Seguro> seguros = seguroRepositorio.exportarSeguros(filterBean);
			StreamingOutput stream = new StreamingOutput() {
				public void write(OutputStream output) throws IOException, WebApplicationException {
					try {
						WorkbookSettings conf = new WorkbookSettings();
						conf.setEncoding("ISO-8859-1");
						WritableWorkbook workBoook = Workbook.createWorkbook(output, conf);
						WritableSheet sheet = workBoook.createSheet("Seguros", 0);
						WritableFont h = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
						WritableCellFormat hFormat = new WritableCellFormat(h);
						hFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
						hFormat.setWrap(true);
						int filaCount = 0;
						sheet.addCell(new Label(0, filaCount, "MARCA", hFormat));
						sheet.addCell(new Label(1, filaCount, "MODELO", hFormat));
						sheet.addCell(new Label(2, filaCount, "CLASE", hFormat));
						sheet.addCell(new Label(3, filaCount, "PLACA", hFormat));
						sheet.addCell(new Label(4, filaCount, "REFERENCIA", hFormat));
						sheet.addCell(new Label(5, filaCount, "POLIZA", hFormat));
						sheet.addCell(new Label(6, filaCount, "OPERACIÓN", hFormat));
						sheet.addCell(new Label(7, filaCount, "FECHA INICIO", hFormat));
						sheet.addCell(new Label(8, filaCount, "FECHA FIN", hFormat));
						filaCount++;
						for (Seguro seguro : seguros) {
							sheet.addCell(new Label(0, filaCount, seguro.getVehiculo().getMarca(), hFormat));
							sheet.addCell(new Label(1, filaCount, seguro.getVehiculo().getModelo(), hFormat));
							sheet.addCell(new Label(2, filaCount, seguro.getVehiculo().getClase(), hFormat));
							sheet.addCell(new Label(3, filaCount, seguro.getVehiculo().getPlaca(), hFormat));
							sheet.addCell(new Label(4, filaCount, seguro.getReferencia(), hFormat));
							sheet.addCell(new Label(5, filaCount, seguro.getPoliza(), hFormat));
							sheet.addCell(new Label(6, filaCount, seguro.getOperacion(), hFormat));
							sheet.addCell(new Label(7, filaCount, seguro.getFecha_inicio(), hFormat));
							sheet.addCell(new Label(8, filaCount, seguro.getFecha_fin(), hFormat));
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

	public List<Seguro> vencerSeguros() {
		try {
			return seguroRepositorio.vencerSeguros();
		} catch (Exception e) {
			return null;
		}
	}
}
