package org.transport420.sgmv.dao.interfaces;

import java.util.List;

import org.transport420.sgmv.model.Empleado;
import org.transport420.sgmv.resources.beans.UsuariosFilterBean;

public interface IEmpleadoRepositorio {

	public abstract List<Empleado> listarEmpleados(UsuariosFilterBean filterBean) throws Exception;

	public abstract Empleado crearEmpleado(Empleado empleado) throws Exception;

	public abstract Empleado obtenerEmpleado(int idsgmv_empleado) throws Exception;

	public abstract Empleado editarEmpleado(Empleado empleado) throws Exception;

	public abstract void eliminarEmpleado(int idsgmv_empleado) throws Exception;

}
