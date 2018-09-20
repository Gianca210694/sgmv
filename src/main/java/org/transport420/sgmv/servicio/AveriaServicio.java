package org.transport420.sgmv.servicio;

import java.util.List;

import org.transport420.sgmv.dao.interfaces.IReporteFallaRepositorio;
import org.transport420.sgmv.daofactory.DAOFactory;
import org.transport420.sgmv.model.ReporteFalla;
import org.transport420.sgmv.resources.beans.ReporteFallasFilterBean;

public class AveriaServicio {

	IReporteFallaRepositorio reporteFallaRepositorio = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getReporteFalla();

	public List<ReporteFalla> listarAverias(ReporteFallasFilterBean filterBean) {
		try {
			return reporteFallaRepositorio.listarAverias(filterBean);
		} catch (Exception e) {
			return null;
		}
	}

	public ReporteFalla crearAveria(ReporteFalla averia) {
		try {
			return reporteFallaRepositorio.crearAveria(averia);
		} catch (Exception e) {
			return null;
		}
	}

	public ReporteFalla obtenerAveria(int idsgmv_reporte_falla) {
		try {
			return reporteFallaRepositorio.obtenerAveria(idsgmv_reporte_falla);
		} catch (Exception e) {
			return null;
		}
	}

	public ReporteFalla editarAveria(ReporteFalla averia) {
		try {
			return reporteFallaRepositorio.editarAveria(averia);
		} catch (Exception e) {
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

}
