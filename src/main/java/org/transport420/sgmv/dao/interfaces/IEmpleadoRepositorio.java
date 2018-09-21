package org.transport420.sgmv.dao.interfaces;

import java.util.List;

import org.transport420.sgmv.model.Empleado;
import org.transport420.sgmv.model.Usuario;
import org.transport420.sgmv.resources.beans.UsuarioReporteFilterBean;
import org.transport420.sgmv.resources.beans.UsuariosFilterBean;

public interface IEmpleadoRepositorio {

	public abstract List<Empleado> listarEmpleados(UsuariosFilterBean filterBean) throws Exception;

	public abstract List<Empleado> listarAdminJefeOp() throws Exception;

	public abstract Empleado crearEmpleado(Empleado empleado) throws Exception;

	public abstract Empleado obtenerEmpleado(int idsgmv_empleado) throws Exception;

	public abstract Empleado editarEmpleado(Empleado empleado) throws Exception;

	public abstract void cambiarEstado(int idsgmv_empleado, int estado) throws Exception;

	public void cambiarContrasena(Usuario usuario) throws Exception;

	public abstract void eliminarEmpleado(int idsgmv_empleado) throws Exception;
	
	public List<Empleado> exportarEmpleados(UsuarioReporteFilterBean filterBean) throws Exception;

}
