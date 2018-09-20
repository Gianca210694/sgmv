package org.transport420.sgmv.daofactory;

import java.sql.DriverManager;

import org.transport420.sgmv.dao.interfaces.IComponenteRepositorio;
import org.transport420.sgmv.dao.interfaces.ICostoMantenimientoRepositorio;
import org.transport420.sgmv.dao.interfaces.IEmpleadoRepositorio;
import org.transport420.sgmv.dao.interfaces.IOrdenMantenimientoRepositorio;
import org.transport420.sgmv.dao.interfaces.IReporteFallaRepositorio;
import org.transport420.sgmv.dao.interfaces.ISeguroRepositorio;
import org.transport420.sgmv.dao.interfaces.IUsuarioRepositorio;
import org.transport420.sgmv.dao.interfaces.IVehiculoRepositorio;
import org.transport420.sgmv.dao.repositorio.ComponenteRepositorio;
import org.transport420.sgmv.dao.repositorio.CostoMantenimientoRepositorio;
import org.transport420.sgmv.dao.repositorio.EmpleadoRepositorio;
import org.transport420.sgmv.dao.repositorio.OrdenMantenimientoRepositorio;
import org.transport420.sgmv.dao.repositorio.ReporteFallaRepositorio;
import org.transport420.sgmv.dao.repositorio.SeguroRepositorio;
import org.transport420.sgmv.dao.repositorio.UsuarioRepositorio;
import org.transport420.sgmv.dao.repositorio.VehiculoRepositorio;

import java.sql.Connection;

public class MySqlDAOFactory extends DAOFactory {

	public static Connection obtenerConexion() {

		Connection conexion = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10257745?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String usuario = "sql10257745";
			String password = "ENbnil9pM7";

			conexion = DriverManager.getConnection(url, usuario, password);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return conexion;
	}

	@Override
	public IUsuarioRepositorio getUsuario() {
		return new UsuarioRepositorio();
	}

	@Override
	public IEmpleadoRepositorio getEmpleado() {
		return new EmpleadoRepositorio();
	}

	@Override
	public IVehiculoRepositorio getVehiculo() {
		return new VehiculoRepositorio();
	}

	@Override
	public IReporteFallaRepositorio getReporteFalla() {
		return new ReporteFallaRepositorio();
	}

	@Override
	public IComponenteRepositorio getComponente() {
		return new ComponenteRepositorio();
	}

	@Override
	public IOrdenMantenimientoRepositorio getOrdenMantenimiento() {
		return new OrdenMantenimientoRepositorio();
	}

	@Override
	public ISeguroRepositorio getSeguro() {
		return new SeguroRepositorio();
	}

	@Override
	public ICostoMantenimientoRepositorio getCostoMantenimiento() {
		return new CostoMantenimientoRepositorio();
	}

}
