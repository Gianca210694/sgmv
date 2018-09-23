package org.transport420.sgmv.servicio;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import org.transport420.sgmv.dao.interfaces.IReporteFallaRepositorio;
import org.transport420.sgmv.dao.interfaces.IVehiculoRepositorio;
import org.transport420.sgmv.daofactory.DAOFactory;
import org.transport420.sgmv.model.ReporteFalla;
import org.transport420.sgmv.model.Vehiculo;
import org.transport420.sgmv.resources.beans.FechaFilterBean;
import org.transport420.sgmv.resources.beans.ReporteFallasFilterBean;
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

public class AveriaServicio {

	IReporteFallaRepositorio reporteFallaRepositorio = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getReporteFalla();
	IVehiculoRepositorio vehiculoRepositorio = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getVehiculo();

	public List<ReporteFalla> listarAverias(ReporteFallasFilterBean filterBean) {
		try {
			return reporteFallaRepositorio.listarAverias(filterBean);
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			return null;
		}
	}

	public ReporteFalla crearAveria(ReporteFalla averia) {
		try {
			Vehiculo remolque = vehiculoRepositorio.obtenerVehiculo(averia.getRemolque().getIdsgmv_vehiculo());
			String mensaje = "", cantidadKM = "";
			float nuevoKm = remolque.getKilometraje_total() + averia.getKilometraje();
			int multiploNuevoKm = 0, multiploKmVehiculo = 0;
			if (remolque.getMarca().equals("SCANIA")) {
				multiploKmVehiculo = (int) (remolque.getKilometraje_total() / 15000);
				multiploNuevoKm = (int) (nuevoKm / 15000);
				cantidadKM = "15mil";
			} else if (remolque.getMarca().equals("VOLVO")) {
				multiploKmVehiculo = (int) (remolque.getKilometraje_total() / 15000);
				multiploNuevoKm = (int) (nuevoKm / 30000);
				cantidadKM = "30mil";
			}

			if (multiploNuevoKm > multiploKmVehiculo) {
				mensaje = "Matenimiento Preventivo:\n\nSu vehículo " + remolque.getMarca() + " " + remolque.getPlaca()
						+ " llego a los " + cantidadKM + " Kilometros\r\n"
						+ "Realizar el mantenimiento preventivo respectivo de:\r\n" + "	- Cambio de Aceite\r\n"
						+ "	- Filtro\r\n" + "	- Revision de Frenos";
				Util.enviarCorreo(Util.obtenerCorreosAdmins(), "Realizar Mantenimiento de Vehículo", mensaje);
			}
			return reporteFallaRepositorio.crearAveria(averia);
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			return null;
		}
	}

	public ReporteFalla obtenerAveria(int idsgmv_reporte_falla) {
		try {
			return reporteFallaRepositorio.obtenerAveria(idsgmv_reporte_falla);
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			return null;
		}
	}

	public ReporteFalla editarAveria(ReporteFalla averia) {
		try {
			Vehiculo remolque = vehiculoRepositorio.obtenerVehiculo(averia.getRemolque().getIdsgmv_vehiculo());
			ReporteFalla averiaAntes = reporteFallaRepositorio.obtenerAveria(averia.getIdsgmv_reporte_falla());
			float diferencia, nuevoKm;
			String mensaje = "", cantidadKM = "";
			diferencia = averia.getKilometraje() - averiaAntes.getKilometraje();
			nuevoKm = remolque.getKilometraje_total() + diferencia;

			int multiploNuevoKm = 0, multiploKmVehiculo = 0;
			if (remolque.getMarca().equals("SCANIA")) {
				multiploKmVehiculo = (int) (remolque.getKilometraje_total() / 15000);
				multiploNuevoKm = (int) (nuevoKm / 15000);
				cantidadKM = "15mil";
			} else if (remolque.getMarca().equals("VOLVO")) {
				multiploKmVehiculo = (int) (remolque.getKilometraje_total() / 15000);
				multiploNuevoKm = (int) (nuevoKm / 30000);
				cantidadKM = "30mil";
			}

			if (multiploNuevoKm > multiploKmVehiculo) {
				mensaje = "Matenimiento Preventivo:\n\nSu vehículo " + remolque.getMarca() + " " + remolque.getPlaca()
						+ " llego a los " + cantidadKM + " Kilometros\r\n"
						+ "Realizar el mantenimiento preventivo respectivo de:\r\n" + "	- Cambio de Aceite\r\n"
						+ "	- Filtro\r\n" + "	- Revision de Frenos";
				Util.enviarCorreo(Util.obtenerCorreosAdmins(), "Realizar Mantenimiento de Vehículo", mensaje);
			}
			return reporteFallaRepositorio.editarAveria(averia);
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
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
							sheet.addCell(new Label(3, filaCount, Util.getDpto(averia.getProcedencia()), hFormat));
							sheet.addCell(new Label(4, filaCount, Util.getDpto(averia.getDestino()), hFormat));
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
						System.out.println("error: " + e.getMessage());
						throw new WebApplicationException(e);
					}
				}
			};
			return stream;
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			return null;
		}
	}

	public StreamingOutput exportarViajes(int ciudadDestino) {
		try {
			System.out.println("ciudadDestino: " + ciudadDestino);
			final List<ReporteFalla> averias = reporteFallaRepositorio.reporteViajes(ciudadDestino);
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
						sheet.addCell(new Label(0, filaCount, "PLACA REMOLQUE", hFormat));
						sheet.addCell(new Label(1, filaCount, "PLACA SEMIREMOLQUE", hFormat));
						sheet.addCell(new Label(2, filaCount, "PROCEDENCIA", hFormat));
						sheet.addCell(new Label(3, filaCount, "DESTINO", hFormat));
						sheet.addCell(new Label(4, filaCount, "FECHA", hFormat));
						sheet.addCell(new Label(5, filaCount, "CONDUCTOR", hFormat));
						filaCount++;
						for (ReporteFalla averia : averias) {
							sheet.addCell(new Label(0, filaCount, averia.getRemolque().getPlaca(), hFormat));
							sheet.addCell(new Label(1, filaCount, averia.getSemiremolque().getPlaca(), hFormat));
							sheet.addCell(new Label(2, filaCount, Util.getDpto(averia.getProcedencia()), hFormat));
							sheet.addCell(new Label(3, filaCount, Util.getDpto(averia.getDestino()), hFormat));
							sheet.addCell(new Label(4, filaCount, averia.getFecha(), hFormat));
							sheet.addCell(new Label(5, filaCount,
									averia.getConductor().getNombres() + " " + averia.getConductor().getApe_paterno(),
									hFormat));
							filaCount++;
						}
						workBoook.write();
						workBoook.close();
					} catch (Exception e) {
						System.out.println("error: " + e.getMessage());
						throw new WebApplicationException(e);
					}
				}
			};
			return stream;
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			return null;
		}
	}

	public StreamingOutput exportarAveriaPDF(int idsgmv_reporte_falla) {
		try {
			final org.transport420.sgmv.model.reporte.ReporteFalla averia = reporteFallaRepositorio.exportarAveriaPDF(idsgmv_reporte_falla);
			StreamingOutput stream = new StreamingOutput() {
				public void write(OutputStream output) throws IOException, WebApplicationException {
					try {
						String rutaReporte = this.getClass().getClassLoader().getResource("reportes/ReporteAveria.jrxml").getPath();
						JasperReport jr = JasperCompileManager.compileReport(rutaReporte);
						JRDataSource jrDataSource = new JREmptyDataSource();
						Map<String, Object> parametros = new HashMap<String, Object>();
						
						JRBeanCollectionDataSource componentesDataSource = new JRBeanCollectionDataSource(averia.getComponentes_principales());
						JRBeanCollectionDataSource componentesLlantasDataSource = new JRBeanCollectionDataSource(averia.getComponentes_llantas());
						
						parametros.put("codAveria", averia.getCod_reporte());
						parametros.put("versionAveria", "1.00");
						parametros.put("fechaAveria", (new Date()).toLocaleString());
						parametros.put("placaTrailer", averia.getRemolque().getPlaca());
						parametros.put("placaSemitrailer", averia.getSemiremolque().getPlaca());
						parametros.put("conductor", averia.getConductor().getNombres() + " " + averia.getConductor().getApe_paterno());
						parametros.put("fecha", averia.getFecha());
						parametros.put("kilometraje", "" + averia.getKilometraje());
						parametros.put("procedencia", Util.getDpto(averia.getProcedencia()));
						parametros.put("ComponentesDataSource", componentesDataSource);
						parametros.put("ComponentesLlantasDataSource", componentesLlantasDataSource);
						parametros.put("observaciones", averia.getObservaciones());
						JasperPrint jrPrint = JasperFillManager.fillReport(jr, parametros, jrDataSource);
						JasperExportManager.exportReportToPdfStream(jrPrint, output);
						//JasperExportManager.exportReportToPdfFile(jrPrint, "/Users/Giancarlo/Desktop/Plantillas/reporte.pdf");
					} catch (Exception e) {
						System.out.println("jasperError: " + e.getMessage());
						throw new WebApplicationException(e);
					}
				}
			};
			return stream;
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			return null;
		}
	}

}
