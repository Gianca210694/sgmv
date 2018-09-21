package org.transport420.sgmv.dao.repositorio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.transport420.sgmv.dao.interfaces.IEmpleadoRepositorio;
import org.transport420.sgmv.daofactory.MySqlDAOFactory;
import org.transport420.sgmv.model.Empleado;
import org.transport420.sgmv.model.Rol;
import org.transport420.sgmv.model.Usuario;
import org.transport420.sgmv.resources.beans.UsuarioReporteFilterBean;
import org.transport420.sgmv.resources.beans.UsuariosFilterBean;

public class EmpleadoRepositorio implements IEmpleadoRepositorio {

	@Override
	public List<Empleado> listarEmpleados(UsuariosFilterBean filterBean) throws Exception {
		Connection con = null;
		List<Empleado> empleados = new ArrayList<>();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_listar_empleados(?, ?, ?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setString("pDni", filterBean.getDni());
			stmt.setInt("pRol", filterBean.getRol());
			stmt.setInt("pEstado", filterBean.getEstado());
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				Empleado empleado = new Empleado();
				empleado.setIdsgmv_empleado(rs.getInt("idsgmv_empleado"));
				empleado.setNombres(rs.getString("nombres"));
				empleado.setApe_paterno(rs.getString("ape_paterno"));
				empleado.setApe_materno(rs.getString("ape_materno"));
				empleado.setDni(rs.getString("dni"));
				empleado.setSexo(rs.getInt("sexo"));
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
				empleado.setFecha_nacimiento(df.format(rs.getDate("fecha_nacimiento", gmt)));
				empleado.setEmail(rs.getString("email"));
				empleado.setTelefono(rs.getString("telefono"));
				empleado.setBrevete(rs.getString("brevete"));
				empleado.setEstado(rs.getInt("estado"));
				empleado.setUsuario(new Usuario());
				empleado.getUsuario().setIdsgmv_empleado(rs.getInt("idsgmv_usuario"));
				empleado.getUsuario().setUsuario(rs.getString("usuario"));
				empleado.setRol(new Rol());
				empleado.getRol().setIdsgmv_rol(rs.getInt("idsgmv_rol"));
				empleado.getRol().setRol(rs.getString("rol"));
				empleado.getRol().setDescripcion(rs.getString("descripcion"));
				empleados.add(empleado);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return empleados;
	}

	@Override
	public List<Empleado> listarAdminJefeOp() throws Exception {
		Connection con = null;
		List<Empleado> empleados = new ArrayList<>();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_listar_admin_jefe_op()}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				Empleado empleado = new Empleado();
				empleado.setIdsgmv_empleado(rs.getInt("idsgmv_empleado"));
				empleado.setNombres(rs.getString("nombres"));
				empleado.setApe_paterno(rs.getString("ape_paterno"));
				empleado.setApe_materno(rs.getString("ape_materno"));
				empleado.setDni(rs.getString("dni"));
				empleado.setSexo(rs.getInt("sexo"));
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
				empleado.setFecha_nacimiento(df.format(rs.getDate("fecha_nacimiento", gmt)));
				empleado.setEmail(rs.getString("email"));
				empleado.setTelefono(rs.getString("telefono"));
				empleado.setBrevete(rs.getString("brevete"));
				empleado.setEstado(rs.getInt("estado"));
				empleado.setUsuario(new Usuario());
				empleado.getUsuario().setIdsgmv_empleado(rs.getInt("idsgmv_usuario"));
				empleado.getUsuario().setUsuario(rs.getString("usuario"));
				empleado.setRol(new Rol());
				empleado.getRol().setIdsgmv_rol(rs.getInt("idsgmv_rol"));
				empleado.getRol().setRol(rs.getString("rol"));
				empleado.getRol().setDescripcion(rs.getString("descripcion"));
				empleados.add(empleado);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return empleados;
	}

	@Override
	public Empleado crearEmpleado(Empleado empleado) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_crear_empleado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setString("pNombres", empleado.getNombres());
			stmt.setString("pApePaterno", empleado.getApe_paterno());
			stmt.setString("pApeMaterno", empleado.getApe_materno());
			stmt.setString("pDNI", empleado.getDni());
			stmt.setInt("pSexo", empleado.getSexo());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
			LocalDate date = LocalDate.parse(empleado.getFecha_nacimiento(), formatter);
			java.sql.Date sqlDate = java.sql.Date.valueOf(date);
			stmt.setDate("pFechaNacimiento", sqlDate);
			stmt.setString("pEmail", empleado.getEmail());
			stmt.setString("pTelefono", empleado.getTelefono());
			stmt.setString("pBrevete", empleado.getBrevete());
			stmt.setInt("pRol", empleado.getRol().getIdsgmv_rol());
			stmt.registerOutParameter("oIdsgmv_empleado", java.sql.Types.INTEGER);
			stmt.execute();
			empleado.setIdsgmv_empleado(stmt.getInt("oIdsgmv_empleado"));
			empleado.setEstado(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return empleado;
	}

	@Override
	public Empleado obtenerEmpleado(int idsgmv_empleado) throws Exception {
		Connection con = null;
		Empleado empleado = new Empleado();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_obtener_empleado(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_empleado", idsgmv_empleado);
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				empleado.setIdsgmv_empleado(idsgmv_empleado);
				empleado.setNombres(rs.getString("nombres"));
				empleado.setApe_paterno(rs.getString("ape_paterno"));
				empleado.setApe_materno(rs.getString("ape_materno"));
				empleado.setDni(rs.getString("dni"));
				empleado.setSexo(rs.getInt("sexo"));
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
				empleado.setFecha_nacimiento(df.format(rs.getDate("fecha_nacimiento", gmt)));
				empleado.setEmail(rs.getString("email"));
				empleado.setTelefono(rs.getString("telefono"));
				empleado.setBrevete(rs.getString("brevete"));
				empleado.setEstado(rs.getInt("estado"));
				empleado.setUsuario(new Usuario());
				empleado.getUsuario().setIdsgmv_usuario(rs.getInt("idsgmv_usuario"));
				empleado.getUsuario().setUsuario(rs.getString("usuario"));
				empleado.setRol(new Rol());
				empleado.getRol().setIdsgmv_rol(rs.getInt("idsgmv_rol"));
				empleado.getRol().setRol(rs.getString("rol"));
				empleado.getRol().setDescripcion(rs.getString("descripcion"));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return empleado;
	}

	@Override
	public Empleado editarEmpleado(Empleado empleado) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_editar_empleado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_empleado", empleado.getIdsgmv_empleado());
			stmt.setString("pNombres", empleado.getNombres());
			stmt.setString("pApePaterno", empleado.getApe_paterno());
			stmt.setString("pApeMaterno", empleado.getApe_materno());
			stmt.setString("pDNI", empleado.getDni());
			stmt.setInt("pSexo", empleado.getSexo());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
			LocalDate date = LocalDate.parse(empleado.getFecha_nacimiento(), formatter);
			java.sql.Date sqlDate = java.sql.Date.valueOf(date);
			stmt.setDate("pFechaNacimiento", sqlDate);
			stmt.setString("pEmail", empleado.getEmail());
			stmt.setString("pTelefono", empleado.getTelefono());
			stmt.setString("pBrevete", empleado.getBrevete());
			stmt.setInt("pEstado", empleado.getEstado());
			stmt.setInt("pRol", empleado.getRol().getIdsgmv_rol());
			stmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return empleado;
	}

	@Override
	public void cambiarEstado(int idsgmv_empleado, int estado) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_cambiar_estado_usuario(?, ?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_empleado", idsgmv_empleado);
			stmt.setInt("pEstado", estado);
			stmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}


	@Override
	public void cambiarContrasena(Usuario usuario) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_cambiar_contrasena(?, ?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_usuario", usuario.getIdsgmv_usuario());
			stmt.setString("pContrasena", usuario.getContrasena());
			stmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	@Override
	public void eliminarEmpleado(int idsgmv_empleado) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_eliminar_empleado(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_empleado", idsgmv_empleado);
			stmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	@Override
	public List<Empleado> exportarEmpleados(UsuarioReporteFilterBean filterBean) throws Exception {
		Connection con = null;
		List<Empleado> empleados = new ArrayList<>();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_exportar_empleados(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pEstado", filterBean.getEstado());
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				Empleado empleado = new Empleado();
				empleado.setIdsgmv_empleado(rs.getInt("idsgmv_empleado"));
				empleado.setNombres(rs.getString("nombres"));
				empleado.setApe_paterno(rs.getString("ape_paterno"));
				empleado.setApe_materno(rs.getString("ape_materno"));
				empleado.setDni(rs.getString("dni"));
				empleado.setSexo(rs.getInt("sexo"));
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
				empleado.setFecha_nacimiento(df.format(rs.getDate("fecha_nacimiento", gmt)));
				empleado.setEmail(rs.getString("email"));
				empleado.setTelefono(rs.getString("telefono"));
				empleado.setBrevete(rs.getString("brevete"));
				empleado.setEstado(rs.getInt("estado"));
				empleado.setUsuario(new Usuario());
				empleado.getUsuario().setIdsgmv_empleado(rs.getInt("idsgmv_usuario"));
				empleado.getUsuario().setUsuario(rs.getString("usuario"));
				empleado.setRol(new Rol());
				empleado.getRol().setIdsgmv_rol(rs.getInt("idsgmv_rol"));
				empleado.getRol().setRol(rs.getString("rol"));
				empleado.getRol().setDescripcion(rs.getString("descripcion"));
				empleados.add(empleado);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return empleados;
	}

}
