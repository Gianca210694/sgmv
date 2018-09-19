package org.transport420.sgmv.daofactory;

import org.transport420.sgmv.dao.interfaces.IComponenteRepositorio;
import org.transport420.sgmv.dao.interfaces.ICostoMantenimientoRepositorio;
import org.transport420.sgmv.dao.interfaces.IEmpleadoRepositorio;
import org.transport420.sgmv.dao.interfaces.IOrdenMantenimientoRepositorio;
import org.transport420.sgmv.dao.interfaces.IReporteFallaRepositorio;
import org.transport420.sgmv.dao.interfaces.ISeguroRepositorio;
import org.transport420.sgmv.dao.interfaces.IUsuarioRepositorio;
import org.transport420.sgmv.dao.interfaces.IVehiculoRepositorio;

public abstract class DAOFactory {

	public static final int MYSQL = 1;
	public static final int SQLSERVER = 2;
	public static final int ORACLE = 3;

	public abstract IUsuarioRepositorio getUsuario();

	public abstract IEmpleadoRepositorio getEmpleado();

	public abstract IVehiculoRepositorio getVehiculo();

	public abstract IReporteFallaRepositorio getReporteFalla();

	public abstract IComponenteRepositorio getComponente();

	public abstract IOrdenMantenimientoRepositorio getOrdenMantenimiento();
	
	public abstract ISeguroRepositorio getSeguro();
	
	public abstract ICostoMantenimientoRepositorio getCostoMantenimiento();

	public static MySqlDAOFactory getDAOFactory(int factory) {
		switch (factory) {
		case MYSQL:
			return new MySqlDAOFactory();
		case ORACLE:
			return null;
		default:
			return null;
		}
	}
}
