package org.transport420.sgmv.dao.repositorio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.transport420.sgmv.dao.interfaces.ICostoMantenimientoRepositorio;
import org.transport420.sgmv.daofactory.MySqlDAOFactory;
import org.transport420.sgmv.model.CostoMantenimiento;
import org.transport420.sgmv.model.CostoMantenimientoDetalle;
import org.transport420.sgmv.model.OrdenMantenimiento;
import org.transport420.sgmv.model.Vehiculo;
import org.transport420.sgmv.resources.beans.CostosMantenimientoFilterBean;
import org.transport420.sgmv.resources.beans.FechaFilterBean;

public class CostoMantenimientoRepositorio implements ICostoMantenimientoRepositorio {

	@Override
	public List<CostoMantenimiento> listarCostosMantenimiento(CostosMantenimientoFilterBean filterBean)
			throws Exception {
		Connection con = null;
		List<CostoMantenimiento> costosMantenimiento = new ArrayList<>();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_listar_costos_mantenimiento(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsmgv_vehiculo", filterBean.getVehiculo());
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				CostoMantenimiento costoMantenimiento = new CostoMantenimiento();
				costoMantenimiento.setIdsgmv_costo_mantenimiento(rs.getInt("idsgmv_costo_mantenimiento"));
				costoMantenimiento.setCod_costo_mantenimiento(rs.getString("cod_costo_mantenimiento"));
				costoMantenimiento.setOrdenMantenimiento(new OrdenMantenimiento());
				costoMantenimiento.getOrdenMantenimiento()
						.setIdsgmv_orden_mantenimiento(rs.getInt("idsgmv_orden_mantenimiento"));
				costoMantenimiento.getOrdenMantenimiento()
						.setCod_mantenimiento_orden(rs.getString("cod_mantenimiento_orden"));
				costoMantenimiento.setMoneda(rs.getInt("moneda"));
				costoMantenimiento.setCosto_total(rs.getFloat("costo_total"));
				costoMantenimiento.setVehiculo(new Vehiculo());
				costoMantenimiento.getVehiculo().setPlaca(rs.getString("placa"));
				costoMantenimiento.getVehiculo().setMarca(rs.getString("marca"));
				costoMantenimiento.getVehiculo().setModelo(rs.getString("modelo"));
				costoMantenimiento.setCosto_vehiculo(rs.getFloat("costo_vehiculo"));
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
				costoMantenimiento.setFecha(df.format(rs.getDate("fecha", gmt)));
				costosMantenimiento.add(costoMantenimiento);
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
		return costosMantenimiento;
	}

	@Override
	public CostoMantenimiento crearCostoMantenimiento(CostoMantenimiento costoMantenimiento) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			con.setAutoCommit(false);
			String queryCostoMantenimiento = "{CALL sql10257745.sp_crear_costo_mantenimiento(?, ?, ?, ?, ?)}";
			String queryDetalle = "{CALL sql10257745.sp_crear_costo_mantenimiento_detalle(?, ?, ?, ?, ?)}";

			CallableStatement stmt = con.prepareCall(queryCostoMantenimiento);
			stmt.setInt("pIdsgmv_orden_mantenimiento",
					costoMantenimiento.getOrdenMantenimiento().getIdsgmv_orden_mantenimiento());
			stmt.setInt("pMoneda", costoMantenimiento.getMoneda());
			stmt.setFloat("pCostoTotal", costoMantenimiento.getCosto_total());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
			LocalDate date = LocalDate.parse(costoMantenimiento.getFecha(), formatter);
			java.sql.Date sqlDate = java.sql.Date.valueOf(date);
			stmt.setDate("pFecha", sqlDate);
			stmt.registerOutParameter("oIdsgmv_costo_mantenimiento", java.sql.Types.INTEGER);
			stmt.execute();
			costoMantenimiento.setIdsgmv_costo_mantenimiento(stmt.getInt("oIdsgmv_costo_mantenimiento"));

			for (int i = 0; i < costoMantenimiento.getDetalle().size(); i++) {
				CostoMantenimientoDetalle det = costoMantenimiento.getDetalle().get(i);
				if (det != null) {
					CallableStatement stmtDetalle = con.prepareCall(queryDetalle);
					stmtDetalle.setInt("pIdsgmv_costo_mantenimiento",
							costoMantenimiento.getIdsgmv_costo_mantenimiento());
					stmtDetalle.setInt("pTipoVehiculo", det.getTipo_vehiculo());
					stmtDetalle.setString("pActividad", det.getActividad());
					stmtDetalle.setString("pComprobante", det.getComprobante());
					stmtDetalle.setFloat("pCosto", det.getCosto());
					stmtDetalle.execute();
				}
			}

			con.commit();
		} catch (Exception e) {
			try {
				if (con != null)
					con.rollback();
			} catch (SQLException sqlE) {
				System.out.println(sqlE.getMessage());
			}
			System.out.println(e.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return costoMantenimiento;
	}

	@Override
	public CostoMantenimiento obtenerCostoMantenimiento(int idsgmv_costo_mantenimiento) throws Exception {
		Connection con = null;
		CostoMantenimiento costoMantenimiento = new CostoMantenimiento();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_obtener_costo_mantenimiento(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_costo_mantenimiento", idsgmv_costo_mantenimiento);
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				costoMantenimiento.setIdsgmv_costo_mantenimiento(idsgmv_costo_mantenimiento);
				costoMantenimiento.setCod_costo_mantenimiento(rs.getString("cod_costo_mantenimiento"));
				costoMantenimiento.setOrdenMantenimiento(new OrdenMantenimiento());
				costoMantenimiento.getOrdenMantenimiento()
						.setIdsgmv_orden_mantenimiento(rs.getInt("idsgmv_orden_mantenimiento"));
				costoMantenimiento.getOrdenMantenimiento()
						.setCod_mantenimiento_orden(rs.getString("cod_mantenimiento_orden"));
				costoMantenimiento.setMoneda(rs.getInt("moneda"));
				costoMantenimiento.setCosto_total(rs.getFloat("costo_total"));
				costoMantenimiento.setFecha(df.format(rs.getDate("fecha", gmt)));
			}
			rs.close();

			stmt.getMoreResults();

			rs = stmt.getResultSet();
			costoMantenimiento.setDetalle(new ArrayList<CostoMantenimientoDetalle>());
			while (rs.next()) {
				CostoMantenimientoDetalle det = new CostoMantenimientoDetalle();
				det.setIdsgmv_costo_mantenimiento(rs.getInt("idsgmv_costo_mantenimiento"));
				det.setVehiculo(new Vehiculo());
				det.setTipo_vehiculo(rs.getInt("tipo_vehiculo"));
				;
				det.setActividad(rs.getString("actividad"));
				det.setComprobante(rs.getString("comprobante"));
				det.setCosto(rs.getFloat("costo"));
				costoMantenimiento.getDetalle().add(det);
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
		return costoMantenimiento;
	}

	@Override
	public CostoMantenimiento editarCostoMantenimiento(CostoMantenimiento costoMantenimiento) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			con.setAutoCommit(false);
			String query = "{CALL sql10257745.sp_editar_costo_mantenimiento(?, ?, ?, ?, ?)}";
			String queryDetalle = "{CALL sql10257745.sp_crear_costo_mantenimiento_detalle(?, ?, ?, ?, ?)}";

			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_costo_mantenimiento", costoMantenimiento.getIdsgmv_costo_mantenimiento());
			stmt.setInt("pIdsgmv_orden_mantenimiento",
					costoMantenimiento.getOrdenMantenimiento().getIdsgmv_orden_mantenimiento());
			stmt.setInt("pMoneda", costoMantenimiento.getMoneda());
			stmt.setFloat("pCosto_total", costoMantenimiento.getCosto_total());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
			LocalDate date = LocalDate.parse(costoMantenimiento.getFecha(), formatter);
			java.sql.Date sqlDate = java.sql.Date.valueOf(date);
			stmt.setDate("pFecha", sqlDate);
			stmt.execute();

			for (int i = 0; i < costoMantenimiento.getDetalle().size(); i++) {
				CostoMantenimientoDetalle det = costoMantenimiento.getDetalle().get(i);
				if (det != null) {
					CallableStatement stmtDetalle = con.prepareCall(queryDetalle);
					stmtDetalle.setInt("pIdsgmv_costo_mantenimiento",
							costoMantenimiento.getIdsgmv_costo_mantenimiento());
					stmtDetalle.setInt("pTipoVehiculo", det.getTipo_vehiculo());
					stmtDetalle.setString("pActividad", det.getActividad());
					stmtDetalle.setString("pComprobante", det.getComprobante());
					stmtDetalle.setFloat("pCosto", det.getCosto());
					stmtDetalle.execute();
				}
			}
			con.commit();
		} catch (Exception e) {
			try {
				if (con != null)
					con.rollback();
			} catch (SQLException sqlE) {
				System.out.println(sqlE.getMessage());
			}
			System.out.println(e.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return costoMantenimiento;
	}

	@Override
	public void eliminarCostoMantenimiento(int idsgmv_costo_mantenimiento) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_eliminar_costo_mantenimiento(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_costo_mantenimiento", idsgmv_costo_mantenimiento);
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
	public List<CostoMantenimiento> reporteCostosMantenimiento(FechaFilterBean filterBean)
			throws Exception {
		Connection con = null;
		List<CostoMantenimiento> costosMantenimiento = new ArrayList<>();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_exportar_costos_mantenimiento(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pMeses", filterBean.getMeses());
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				CostoMantenimiento costoMantenimiento = new CostoMantenimiento();
				costoMantenimiento.setIdsgmv_costo_mantenimiento(rs.getInt("idsgmv_costo_mantenimiento"));
				costoMantenimiento.setCod_costo_mantenimiento(rs.getString("cod_costo_mantenimiento"));
				costoMantenimiento.setOrdenMantenimiento(new OrdenMantenimiento());
				costoMantenimiento.getOrdenMantenimiento()
						.setIdsgmv_orden_mantenimiento(rs.getInt("idsgmv_orden_mantenimiento"));
				costoMantenimiento.getOrdenMantenimiento()
						.setCod_mantenimiento_orden(rs.getString("cod_mantenimiento_orden"));
				costoMantenimiento.setMoneda(rs.getInt("moneda"));
				costoMantenimiento.setCosto_total(rs.getFloat("costo_total"));
				costoMantenimiento.setVehiculo(new Vehiculo());
				costoMantenimiento.getVehiculo().setPlaca(rs.getString("placa"));
				costoMantenimiento.getVehiculo().setMarca(rs.getString("marca"));
				costoMantenimiento.getVehiculo().setModelo(rs.getString("modelo"));
				costoMantenimiento.setCosto_vehiculo(rs.getFloat("costo_vehiculo"));
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
				costoMantenimiento.setFecha(df.format(rs.getDate("fecha", gmt)));
				costosMantenimiento.add(costoMantenimiento);
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
		return costosMantenimiento;
	}

}
