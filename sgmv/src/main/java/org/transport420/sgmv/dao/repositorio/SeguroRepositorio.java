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

import org.transport420.sgmv.dao.interfaces.ISeguroRepositorio;
import org.transport420.sgmv.daofactory.MySqlDAOFactory;
import org.transport420.sgmv.model.Seguro;
import org.transport420.sgmv.model.Vehiculo;
import org.transport420.sgmv.resources.beans.SegurosFilterBean;

public class SeguroRepositorio implements ISeguroRepositorio {

	@Override
	public List<Seguro> listarSeguros(SegurosFilterBean filterBean) throws Exception {
		Connection con = null;
		List<Seguro> seguros = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sgmv.sp_listar_seguros(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_vehiculo", filterBean.getIdVehiculo());
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				Seguro seguro = new Seguro();
				seguro.setIdsgmv_seguro(rs.getInt("idsgmv_seguro"));
				seguro.setVehiculo(new Vehiculo());
				seguro.getVehiculo().setIdsgmv_vehiculo(rs.getInt("idsgmv_vehiculo"));
				seguro.getVehiculo().setMarca(rs.getString("marca"));
				seguro.getVehiculo().setModelo(rs.getString("modelo"));
				seguro.getVehiculo().setClase(rs.getString("clase"));
				seguro.getVehiculo().setPlaca(rs.getString("placa"));
				seguro.setReferencia(rs.getString("referencia"));
				seguro.setPoliza(rs.getString("poliza"));
				seguro.setPrecio(rs.getFloat("precio"));
				seguro.setOperacion(rs.getString("operacion"));
				seguro.setFecha_inicio(df.format(rs.getDate("fecha_inicio", gmt)));
				seguro.setFecha_fin(df.format(rs.getDate("fecha_fin", gmt)));
				seguro.setEstado(rs.getInt("estado"));
				seguros.add(seguro);
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
		return seguros;
	}

	@Override
	public Seguro crearSeguro(Seguro seguro) throws Exception {
		Connection con = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sgmv.sp_crear_seguro(?, ?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_vehiculo", seguro.getVehiculo().getIdsgmv_vehiculo());
			stmt.setString("pReferencia", seguro.getReferencia());
			stmt.setFloat("pPrecio", seguro.getPrecio());
			stmt.setString("pPoliza", seguro.getPoliza());
			stmt.setString("pOperacion", seguro.getOperacion());
			LocalDate dateIni = LocalDate.parse(seguro.getFecha_inicio(), formatter);
			java.sql.Date sqlDateIni = java.sql.Date.valueOf(dateIni);
			stmt.setDate("pFechaInicio", sqlDateIni);
			LocalDate dateFin = LocalDate.parse(seguro.getFecha_fin(), formatter);
			java.sql.Date sqlDateFin = java.sql.Date.valueOf(dateFin);
			stmt.setDate("pFechaFin", sqlDateFin);
			stmt.registerOutParameter("oIdsgmv_seguro", java.sql.Types.INTEGER);
			stmt.execute();
			seguro.setIdsgmv_seguro(stmt.getInt("oIdsgmv_seguro"));
			seguro.setEstado(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return seguro;
	}

	@Override
	public Seguro obtenerSeguro(int idsgmv_seguro) throws Exception {
		Connection con = null;
		Seguro seguro = new Seguro();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sgmv.sp_obtener_seguro(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_seguro", idsgmv_seguro);
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				seguro.setIdsgmv_seguro(rs.getInt("idsgmv_seguro"));
				seguro.setVehiculo(new Vehiculo());
				seguro.getVehiculo().setIdsgmv_vehiculo(rs.getInt("idsgmv_vehiculo"));
				seguro.getVehiculo().setMarca(rs.getString("marca"));
				seguro.getVehiculo().setModelo(rs.getString("modelo"));
				seguro.getVehiculo().setClase(rs.getString("clase"));
				seguro.getVehiculo().setPlaca(rs.getString("placa"));
				seguro.setReferencia(rs.getString("referencia"));
				seguro.setPoliza(rs.getString("poliza"));
				seguro.setPrecio(rs.getFloat("precio"));
				seguro.setOperacion(rs.getString("operacion"));
				seguro.setFecha_inicio(df.format(rs.getDate("fecha_inicio", gmt)));
				seguro.setFecha_fin(df.format(rs.getDate("fecha_fin", gmt)));
				seguro.setEstado(rs.getInt("estado"));
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
		return seguro;
	}

	@Override
	public Seguro editarSeguro(Seguro seguro) throws Exception {
		Connection con = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sgmv.sp_editar_seguro(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_seguro", seguro.getIdsgmv_seguro());
			stmt.setInt("pIdsgmv_vehiculo", seguro.getVehiculo().getIdsgmv_vehiculo());
			stmt.setString("pReferencia", seguro.getReferencia());
			stmt.setFloat("pPrecio", seguro.getPrecio());
			stmt.setString("pPoliza", seguro.getPoliza());
			stmt.setString("pOperacion", seguro.getOperacion());
			LocalDate dateIni = LocalDate.parse(seguro.getFecha_inicio(), formatter);
			java.sql.Date sqlDateIni = java.sql.Date.valueOf(dateIni);
			stmt.setDate("pFechaInicio", sqlDateIni);
			LocalDate dateFin = LocalDate.parse(seguro.getFecha_fin(), formatter);
			java.sql.Date sqlDateFin = java.sql.Date.valueOf(dateFin);
			stmt.setDate("pFechaFin", sqlDateFin);
			stmt.setInt("pEstado", seguro.getEstado());
			stmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return seguro;
	}

	@Override
	public void eliminarSeguro(int idsgmv_seguro) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sgmv.sp_eliminar_seguro(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_seguro", idsgmv_seguro);
			stmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

}
