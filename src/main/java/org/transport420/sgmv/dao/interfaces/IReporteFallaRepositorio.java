package org.transport420.sgmv.dao.interfaces;

import java.util.List;

import org.transport420.sgmv.model.ReporteFalla;
import org.transport420.sgmv.resources.beans.ReporteFallasFilterBean;

public interface IReporteFallaRepositorio {

	public abstract List<ReporteFalla> listarAverias(ReporteFallasFilterBean filterBean) throws Exception;

	public abstract ReporteFalla crearAveria(ReporteFalla reporteFalla) throws Exception;

	public abstract ReporteFalla obtenerAveria(int idsgmv_reporte_falla) throws Exception;

	public abstract ReporteFalla editarAveria(ReporteFalla reporteFalla) throws Exception;

	public abstract void eliminarAveria(int idsgmv_reporte_falla) throws Exception;

}
