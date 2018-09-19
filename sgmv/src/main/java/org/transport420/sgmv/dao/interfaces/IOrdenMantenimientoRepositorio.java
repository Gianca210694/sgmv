package org.transport420.sgmv.dao.interfaces;

import java.util.List;

import org.transport420.sgmv.model.OrdenMantenimiento;
import org.transport420.sgmv.resources.beans.OrdenesMantenimientoFilterBean;

public interface IOrdenMantenimientoRepositorio {

	public abstract List<OrdenMantenimiento> listarOrdenesMantenimiento(OrdenesMantenimientoFilterBean filterBean)
			throws Exception;

	public abstract OrdenMantenimiento crearOrdenMantenimiento(OrdenMantenimiento ordenMantenimiento) throws Exception;

	public abstract OrdenMantenimiento obtenerOrdenMantenimiento(int idsgmv_orden_mantenimiento) throws Exception;

	public abstract OrdenMantenimiento editarOrdenMantenimiento(OrdenMantenimiento ordenMantenimiento) throws Exception;

	public abstract void eliminarOrdenMantenimiento(int idsgmv_orden_mantenimiento) throws Exception;

}
