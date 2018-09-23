package org.transport420.sgmv.dao.interfaces;

import java.util.List;

import org.transport420.sgmv.model.Vehiculo;
import org.transport420.sgmv.resources.beans.VehiculoReporteFilterBean;
import org.transport420.sgmv.resources.beans.VehiculosFilterBean;

public interface IVehiculoRepositorio {

	public abstract List<Vehiculo> listarVehiculos(VehiculosFilterBean filterBean) throws Exception;

	public abstract List<Vehiculo> listarRemolques() throws Exception;

	public abstract List<Vehiculo> listarSemiremolques() throws Exception;

	public abstract Vehiculo crearVehiculo(Vehiculo vehiculo) throws Exception;

	public abstract Vehiculo obtenerVehiculo(int idsgmv_vehiculo) throws Exception;

	public abstract Vehiculo editarVehiculo(Vehiculo vehiculo) throws Exception;

	public void cambiarEstado(int idsgmv_vehiculo, int estado) throws Exception;

	public abstract void eliminarVehiculo(int idsgmv_vehiculo) throws Exception;

	public List<Vehiculo> exportarVehiculos(VehiculoReporteFilterBean filterBean) throws Exception;

}
