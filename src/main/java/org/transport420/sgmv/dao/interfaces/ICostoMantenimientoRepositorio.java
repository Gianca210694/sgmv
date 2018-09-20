package org.transport420.sgmv.dao.interfaces;

import java.util.List;

import org.transport420.sgmv.model.CostoMantenimiento;
import org.transport420.sgmv.resources.beans.CostosMantenimientoFilterBean;

public interface ICostoMantenimientoRepositorio {

	public abstract List<CostoMantenimiento> listarCostosMantenimiento(CostosMantenimientoFilterBean filterBean)
			throws Exception;

	public abstract CostoMantenimiento crearCostoMantenimiento(CostoMantenimiento costoMantenimiento) throws Exception;

	public abstract CostoMantenimiento obtenerCostoMantenimiento(int idsgmv_costo_mantenimiento) throws Exception;

	public abstract CostoMantenimiento editarCostoMantenimiento(CostoMantenimiento costoMantenimiento) throws Exception;

	public abstract void eliminarCostoMantenimiento(int idsgmv_costo_mantenimiento) throws Exception;

}
