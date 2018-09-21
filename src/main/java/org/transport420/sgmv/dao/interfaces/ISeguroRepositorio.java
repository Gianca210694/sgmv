package org.transport420.sgmv.dao.interfaces;

import java.util.List;

import org.transport420.sgmv.model.Seguro;
import org.transport420.sgmv.resources.beans.SeguroReporteFilterBean;
import org.transport420.sgmv.resources.beans.SegurosFilterBean;

public interface ISeguroRepositorio {

	public abstract List<Seguro> listarSeguros(SegurosFilterBean filterBean) throws Exception;

	public abstract Seguro crearSeguro(Seguro seguro) throws Exception;

	public abstract Seguro obtenerSeguro(int idsgmv_seguro) throws Exception;

	public abstract Seguro editarSeguro(Seguro seguro) throws Exception;

	public abstract void eliminarSeguro(int idsgmv_seguro) throws Exception;

	public List<Seguro> exportarSeguros(SeguroReporteFilterBean filterBean) throws Exception;

}
