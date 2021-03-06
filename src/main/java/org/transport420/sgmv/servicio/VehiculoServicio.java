package org.transport420.sgmv.servicio;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import org.transport420.sgmv.dao.interfaces.IVehiculoRepositorio;
import org.transport420.sgmv.daofactory.DAOFactory;
import org.transport420.sgmv.model.Vehiculo;
import org.transport420.sgmv.resources.beans.VehiculoReporteFilterBean;
import org.transport420.sgmv.resources.beans.VehiculosFilterBean;
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

public class VehiculoServicio {

	IVehiculoRepositorio vehiculoRepositorio = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getVehiculo();

	public List<Vehiculo> listarVehiculos(VehiculosFilterBean filterBean) {
		try {
			return vehiculoRepositorio.listarVehiculos(filterBean);
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			return null;
		}
	}

	public List<Vehiculo> listarRemolques() {
		try {
			return vehiculoRepositorio.listarRemolques();
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			return null;
		}
	}

	public List<Vehiculo> listarSemiremolques() {
		try {
			return vehiculoRepositorio.listarSemiremolques();
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			return null;
		}
	}

	public Vehiculo crearVehiculo(Vehiculo vehiculo) {
		try {
			return vehiculoRepositorio.crearVehiculo(vehiculo);
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			return null;
		}
	}

	public Vehiculo obtenerVehiculo(int idsgmv_vehiculo) {
		try {
			return vehiculoRepositorio.obtenerVehiculo(idsgmv_vehiculo);
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			return null;
		}
	}

	public Vehiculo editarVehiculo(Vehiculo vehiculo) {
		try {
			return vehiculoRepositorio.editarVehiculo(vehiculo);
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			return null;
		}
	}

	public void cambiarEstado(int idsgmv_vehiculo, int estado) {
		try {
			vehiculoRepositorio.cambiarEstado(idsgmv_vehiculo, estado);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public void eliminarVehiculo(int idsgmv_vehiculo) {
		try {
			vehiculoRepositorio.eliminarVehiculo(idsgmv_vehiculo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public StreamingOutput exportarVehiculo(VehiculoReporteFilterBean filterBean) {
		try {

			final List<Vehiculo> vehiculos = vehiculoRepositorio.exportarVehiculos(filterBean);
			StreamingOutput stream = new StreamingOutput() {
				public void write(OutputStream output) throws IOException, WebApplicationException {
					try {
						WorkbookSettings conf = new WorkbookSettings();
						conf.setEncoding("ISO-8859-1");
						WritableWorkbook workBoook = Workbook.createWorkbook(output, conf);
						WritableSheet sheet = workBoook.createSheet("Vehículos", 0);
						WritableFont h = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
						WritableCellFormat hFormat = new WritableCellFormat(h);
						hFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
						hFormat.setWrap(true);
						int filaCount = 0;
						sheet.addCell(new Label(0, filaCount, "PLACA", hFormat));
						sheet.addCell(new Label(1, filaCount, "MARCA", hFormat));
						sheet.addCell(new Label(2, filaCount, "MODELO", hFormat));
						sheet.addCell(new Label(3, filaCount, "CLASE", hFormat));
						sheet.addCell(new Label(4, filaCount, "CONSTANCIA", hFormat));
						sheet.addCell(new Label(5, filaCount, "CATEGORIA", hFormat));
						sheet.addCell(new Label(6, filaCount, "SERIE CHASIS", hFormat));
						sheet.addCell(new Label(7, filaCount, "AÑOS FABRICACIÓN", hFormat));
						sheet.addCell(new Label(8, filaCount, "N° EJES", hFormat));
						sheet.addCell(new Label(9, filaCount, "CARGA ÚTIL", hFormat));
						sheet.addCell(new Label(10, filaCount, "PESO SECO", hFormat));
						sheet.addCell(new Label(11, filaCount, "KM", hFormat));
						sheet.addCell(new Label(12, filaCount, "ESTADO", hFormat));
						filaCount++;
						for (Vehiculo vehiculo : vehiculos) {
							sheet.addCell(new Label(0, filaCount, vehiculo.getPlaca(), hFormat));
							sheet.addCell(new Label(1, filaCount, vehiculo.getMarca(), hFormat));
							sheet.addCell(new Label(2, filaCount, vehiculo.getModelo(), hFormat));
							sheet.addCell(new Label(3, filaCount, vehiculo.getClase(), hFormat));
							sheet.addCell(new Label(4, filaCount, vehiculo.getConstancia(), hFormat));
							sheet.addCell(new Label(5, filaCount, vehiculo.getCategoria(), hFormat));
							sheet.addCell(new Label(6, filaCount, vehiculo.getSerie_chasis(), hFormat));
							sheet.addCell(new Label(7, filaCount, "" + vehiculo.getAnio_produccion(), hFormat));
							sheet.addCell(new Label(8, filaCount, "" + vehiculo.getNumero_ejes(), hFormat));
							sheet.addCell(new Label(9, filaCount, "" + vehiculo.getCarga_util(), hFormat));
							sheet.addCell(new Label(10, filaCount, "" + vehiculo.getPeso_neto(), hFormat));
							sheet.addCell(new Label(11, filaCount, "" + vehiculo.getKilometraje_total(), hFormat));
							sheet.addCell(new Label(12, filaCount, Util.getEstado(vehiculo.getEstado()), hFormat));
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

}
