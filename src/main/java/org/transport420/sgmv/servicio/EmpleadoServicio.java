package org.transport420.sgmv.servicio;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import org.transport420.sgmv.dao.interfaces.IEmpleadoRepositorio;
import org.transport420.sgmv.daofactory.DAOFactory;
import org.transport420.sgmv.model.Empleado;
import org.transport420.sgmv.model.Usuario;
import org.transport420.sgmv.resources.beans.UsuarioReporteFilterBean;
import org.transport420.sgmv.resources.beans.UsuariosFilterBean;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class EmpleadoServicio {

	IEmpleadoRepositorio empleadoRepositorio = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getEmpleado();

	public List<Empleado> listarEmpleados(UsuariosFilterBean filterBean) {
		try {
			return empleadoRepositorio.listarEmpleados(filterBean);
		} catch (Exception e) {
			return null;
		}
	}

	public List<Empleado> listarAdminJefeOp() {
		try {
			return empleadoRepositorio.listarAdminJefeOp();
		} catch (Exception e) {
			return null;
		}
	}

	public Empleado crearEmpleado(Empleado empleado) {
		try {
			return empleadoRepositorio.crearEmpleado(empleado);
		} catch (Exception e) {
			return null;
		}
	}

	public Empleado obtenerEmpleado(int idsgmv_empleado) {
		try {
			return empleadoRepositorio.obtenerEmpleado(idsgmv_empleado);
		} catch (Exception e) {
			return null;
		}
	}

	public Empleado editarEmpleado(Empleado empleado) {
		try {
			return empleadoRepositorio.editarEmpleado(empleado);
		} catch (Exception e) {
			return null;
		}
	}

	public void cambiarEstado(int idsgmv_empleado, int estado) {
		try {
			empleadoRepositorio.cambiarEstado(idsgmv_empleado, estado);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void cambiarContrasena(Usuario usuario) {
		try {
			empleadoRepositorio.cambiarContrasena(usuario);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void eliminarEmpleado(int idsgmv_empleado) {
		try {
			empleadoRepositorio.eliminarEmpleado(idsgmv_empleado);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public StreamingOutput exportarUsuario(UsuarioReporteFilterBean filterBean) {
		try {

			final List<Empleado> empleados = empleadoRepositorio.exportarEmpleados(filterBean);
			StreamingOutput stream = new StreamingOutput() {
				public void write(OutputStream output) throws IOException, WebApplicationException {
					try {
						WorkbookSettings conf = new WorkbookSettings();
						conf.setEncoding("ISO-8859-1");
						WritableWorkbook workBoook = Workbook.createWorkbook(output, conf);
						WritableSheet sheet = workBoook.createSheet("Usuarios", 0);
						WritableFont h = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
						WritableCellFormat hFormat = new WritableCellFormat(h);
						hFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
						hFormat.setWrap(true);
						int filaCount = 0;
						sheet.addCell(new Label(0, filaCount, "NOMBRES", hFormat));
						sheet.addCell(new Label(1, filaCount, "APELLIDO PATERNO", hFormat));
						sheet.addCell(new Label(2, filaCount, "APELLIDO MATERNO", hFormat));
						sheet.addCell(new Label(3, filaCount, "DNI", hFormat));
						sheet.addCell(new Label(4, filaCount, "ROL", hFormat));
						sheet.addCell(new Label(5, filaCount, "FECHA DE INICIO", hFormat));
						sheet.addCell(new Label(6, filaCount, "USUARIO", hFormat));
						filaCount++;
						for (Empleado empleado : empleados) {
							sheet.addCell(new Label(0, filaCount, empleado.getNombres(), hFormat));
							sheet.addCell(new Label(1, filaCount, empleado.getApe_paterno(), hFormat));
							sheet.addCell(new Label(2, filaCount, empleado.getApe_materno(), hFormat));
							sheet.addCell(new Label(3, filaCount, empleado.getDni(), hFormat));
							sheet.addCell(new Label(4, filaCount, empleado.getRol().getDescripcion(), hFormat));
							sheet.addCell(new Label(5, filaCount, empleado.getFecha_nacimiento(), hFormat));
							sheet.addCell(new Label(6, filaCount, empleado.getUsuario().getUsuario(), hFormat));
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
