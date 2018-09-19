package org.transport420.sgmv.servicio;

import java.util.List;

import org.transport420.sgmv.dao.interfaces.IOrdenMantenimientoRepositorio;
import org.transport420.sgmv.daofactory.DAOFactory;
import org.transport420.sgmv.model.OrdenMantenimiento;
import org.transport420.sgmv.resources.beans.OrdenesMantenimientoFilterBean;

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

}
