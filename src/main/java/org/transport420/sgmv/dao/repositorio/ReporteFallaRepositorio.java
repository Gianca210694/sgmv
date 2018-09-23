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

import org.transport420.sgmv.dao.interfaces.IReporteFallaRepositorio;
import org.transport420.sgmv.daofactory.MySqlDAOFactory;
import org.transport420.sgmv.model.Empleado;
import org.transport420.sgmv.model.ReporteFalla;
import org.transport420.sgmv.model.ReporteFallaComponenteLlanta;
import org.transport420.sgmv.model.ReporteFallaComponentePrincipal;
import org.transport420.sgmv.model.Vehiculo;
import org.transport420.sgmv.model.reporte.ComponenteLlanta;
import org.transport420.sgmv.model.reporte.ComponentePrincipal;
import org.transport420.sgmv.resources.beans.FechaFilterBean;
import org.transport420.sgmv.resources.beans.ReporteFallasFilterBean;

public class ReporteFallaRepositorio implements IReporteFallaRepositorio {

	@Override
	public List<ReporteFalla> listarAverias(ReporteFallasFilterBean filterBean) throws Exception {
		Connection con = null;
		List<ReporteFalla> reporteFallas = new ArrayList<>();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_listar_reporte_fallas(?, ?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsmgv_tractor", filterBean.getRemolque());
			stmt.setInt("pIdsgmv_semitrailer", filterBean.getSemiremolque());
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				ReporteFalla reporteFalla = new ReporteFalla();
				reporteFalla.setIdsgmv_reporte_falla(rs.getInt("idsgmv_reporte_falla"));
				reporteFalla.setCod_reporte(rs.getString("cod_reporte"));
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
				reporteFalla.setFecha(df.format(rs.getDate("fecha", gmt)));
				reporteFalla.setKilometraje(rs.getFloat("kilometraje"));
				reporteFalla.setProcedencia(rs.getInt("procedencia"));
				reporteFalla.setDestino(rs.getInt("destino"));
				reporteFalla.setObservaciones(rs.getString("observaciones"));
				reporteFalla.setRemolque(new Vehiculo());
				reporteFalla.getRemolque().setIdsgmv_vehiculo(rs.getInt("idsgmv_tractor"));
				reporteFalla.getRemolque().setPlaca(rs.getString("placa_tractor"));
				reporteFalla.setSemiremolque(new Vehiculo());
				reporteFalla.getSemiremolque().setIdsgmv_vehiculo(rs.getInt("idsgmv_semitrailer"));
				reporteFalla.getSemiremolque().setPlaca(rs.getString("placa_semitrailer"));
				reporteFalla.setEmpleado(new Empleado());
				reporteFalla.getEmpleado().setIdsgmv_empleado(rs.getInt("idsgmv_empleado"));
				reporteFalla.getEmpleado().setNombres(rs.getString("nombres_empleado"));
				reporteFalla.getEmpleado().setApe_paterno(rs.getString("ape_pat_empleado"));
				reporteFalla.setConductor(new Empleado());
				reporteFalla.getConductor().setIdsgmv_empleado(rs.getInt("idsgmv_conductor"));
				reporteFalla.getConductor().setNombres(rs.getString("nombres_conductor"));
				reporteFalla.getConductor().setApe_paterno(rs.getString("ape_pat_coductor"));
				reporteFalla.setEstado(rs.getInt("estado"));
				reporteFallas.add(reporteFalla);
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
		return reporteFallas;
	}

	@Override
	public ReporteFalla crearAveria(ReporteFalla reporteFalla) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			con.setAutoCommit(false);
			String queryAveria = "{CALL sql10257745.sp_crear_reporte_falla(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			String queryComponentesPrincipales = "{CALL sql10257745.sp_crear_componente_principal_averia(?, ?, ?)}";
			String queryComponentesLlantas = "{CALL sql10257745.sp_crear_componente_llanta_averia(?, ?, ?)}";
			CallableStatement stmt = con.prepareCall(queryAveria);
			stmt.setFloat("pKilometraje", reporteFalla.getKilometraje());
			stmt.setInt("pProcedencia", reporteFalla.getProcedencia());
			stmt.setInt("pDestino", reporteFalla.getDestino());
			stmt.setString("pObservaciones", reporteFalla.getObservaciones());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
			LocalDate date = LocalDate.parse(reporteFalla.getFecha(), formatter);
			java.sql.Date sqlDate = java.sql.Date.valueOf(date);
			stmt.setDate("pFecha", sqlDate);
			stmt.setInt("pIdsgmv_tractor", reporteFalla.getRemolque().getIdsgmv_vehiculo());
			stmt.setInt("pIdsgmv_semitrailer", reporteFalla.getSemiremolque().getIdsgmv_vehiculo());
			stmt.setInt("pIdsgmv_empleado", reporteFalla.getEmpleado().getIdsgmv_empleado());
			stmt.setInt("pIdsgmv_conductor", reporteFalla.getConductor().getIdsgmv_empleado());
			stmt.registerOutParameter("oIdsgmv_reporte_falla", java.sql.Types.INTEGER);
			stmt.execute();
			reporteFalla.setIdsgmv_reporte_falla(stmt.getInt("oIdsgmv_reporte_falla"));
			reporteFalla.setEstado(1);
			for (int i = 0; i < reporteFalla.getComponentes_principales().size(); i++) {
				ReporteFallaComponentePrincipal comp = reporteFalla.getComponentes_principales().get(i);
				if (comp != null && comp.getIdsgmv_componente_principal() > 0) {
					CallableStatement stmtComponentePrincipal = con.prepareCall(queryComponentesPrincipales);
					stmtComponentePrincipal.setInt("pIdsgmv_reporte_falla", reporteFalla.getIdsgmv_reporte_falla());
					stmtComponentePrincipal.setInt("pIdsgmv_componente_principal",
							comp.getIdsgmv_componente_principal());
					stmtComponentePrincipal.setString("pDescripcion", comp.getDescripcion());
					stmtComponentePrincipal.execute();
				}
			}

			for (int i = 0; i < reporteFalla.getComponentes_llantas().size(); i++) {
				ReporteFallaComponenteLlanta comp = reporteFalla.getComponentes_llantas().get(i);
				if (comp != null && comp.getIdsgmv_componente_llanta() > 0) {
					CallableStatement stmtComponenteLlanta = con.prepareCall(queryComponentesLlantas);
					stmtComponenteLlanta.setInt("pIdsgmv_reporte_falla", reporteFalla.getIdsgmv_reporte_falla());
					stmtComponenteLlanta.setInt("pIdsgmv_componente_llanta", comp.getIdsgmv_componente_llanta());
					stmtComponenteLlanta.setString("pDescripcion", comp.getDescripcion());
					stmtComponenteLlanta.execute();
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
		return reporteFalla;
	}

	@Override
	public ReporteFalla obtenerAveria(int idsgmv_reporte_falla) throws Exception {
		Connection con = null;
		ReporteFalla averia = new ReporteFalla();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_obtener_reporte_falla(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_reporte_falla", idsgmv_reporte_falla);
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				averia.setIdsgmv_reporte_falla(idsgmv_reporte_falla);
				averia.setCod_reporte(rs.getString("cod_reporte"));
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
				averia.setFecha(df.format(rs.getDate("fecha", gmt)));
				averia.setKilometraje(rs.getFloat("kilometraje"));
				averia.setProcedencia(rs.getInt("procedencia"));
				averia.setDestino(rs.getInt("destino"));
				averia.setObservaciones(rs.getString("observaciones"));
				averia.setRemolque(new Vehiculo());
				averia.getRemolque().setIdsgmv_vehiculo(rs.getInt("idsgmv_tractor"));
				averia.getRemolque().setPlaca(rs.getString("placa_tractor"));
				averia.setSemiremolque(new Vehiculo());
				averia.getSemiremolque().setIdsgmv_vehiculo(rs.getInt("idsgmv_semitrailer"));
				averia.getSemiremolque().setPlaca(rs.getString("placa_semitrailer"));
				averia.setEmpleado(new Empleado());
				averia.getEmpleado().setIdsgmv_empleado(rs.getInt("idsgmv_empleado"));
				averia.getEmpleado().setNombres(rs.getString("nombres_empleado"));
				averia.getEmpleado().setApe_paterno(rs.getString("ape_pat_empleado"));
				averia.setConductor(new Empleado());
				averia.getConductor().setIdsgmv_empleado(rs.getInt("idsgmv_conductor"));
				averia.getConductor().setNombres(rs.getString("nombres_conductor"));
				averia.getConductor().setApe_paterno(rs.getString("ape_pat_coductor"));
				averia.setEstado(rs.getInt("estado"));
			}
			rs.close();

			stmt.getMoreResults();

			rs = stmt.getResultSet();
			averia.setComponentes_principales(new ArrayList<ReporteFallaComponentePrincipal>());
			while (rs.next()) {
				ReporteFallaComponentePrincipal comp = new ReporteFallaComponentePrincipal();
				comp.setIdsgmv_reporte_falla(rs.getInt("idsgmv_reporte_falla"));
				comp.setIdsgmv_componente_principal(rs.getInt("idsgmv_componente_principal"));
				comp.setDescripcion(rs.getString("descripcion"));
				averia.getComponentes_principales().add(comp);
			}

			stmt.getMoreResults();

			rs = stmt.getResultSet();
			averia.setComponentes_llantas(new ArrayList<ReporteFallaComponenteLlanta>());
			while (rs.next()) {
				ReporteFallaComponenteLlanta comp = new ReporteFallaComponenteLlanta();
				comp.setIdsgmv_reporte_falla(rs.getInt("idsgmv_reporte_falla"));
				comp.setIdsgmv_componente_llanta(rs.getInt("idsgmv_componente_llanta"));
				comp.setDescripcion(rs.getString("descripcion"));
				averia.getComponentes_llantas().add(comp);
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
		return averia;
	}

	@Override
	public ReporteFalla editarAveria(ReporteFalla reporteFalla) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			con.setAutoCommit(false);
			String query = "{CALL sql10257745.sp_editar_reporte_falla(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			String queryComponentesPrincipales = "{CALL sql10257745.sp_crear_componente_principal_averia(?, ?, ?)}";
			String queryComponentesLlantas = "{CALL sql10257745.sp_crear_componente_llanta_averia(?, ?, ?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_reporte_falla", reporteFalla.getIdsgmv_reporte_falla());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
			LocalDate date = LocalDate.parse(reporteFalla.getFecha(), formatter);
			java.sql.Date sqlDate = java.sql.Date.valueOf(date);
			stmt.setDate("pFecha", sqlDate);
			stmt.setFloat("pKilometraje", reporteFalla.getKilometraje());
			stmt.setInt("pProcedencia", reporteFalla.getProcedencia());
			stmt.setInt("pDestino", reporteFalla.getDestino());
			stmt.setString("pObservaciones", reporteFalla.getObservaciones());
			stmt.setInt("pIdsgmv_tractor", reporteFalla.getRemolque().getIdsgmv_vehiculo());
			stmt.setInt("pIdsgmv_semitrailer", reporteFalla.getSemiremolque().getIdsgmv_vehiculo());
			stmt.setInt("pIdsgmv_empleado", reporteFalla.getEmpleado().getIdsgmv_empleado());
			stmt.setInt("pIdsgmv_conductor", reporteFalla.getConductor().getIdsgmv_empleado());
			stmt.setInt("pEstado", reporteFalla.getEstado());
			stmt.execute();
			for (int i = 0; i < reporteFalla.getComponentes_principales().size(); i++) {
				ReporteFallaComponentePrincipal comp = reporteFalla.getComponentes_principales().get(i);
				if (comp != null && comp.getIdsgmv_componente_principal() > 0) {
					CallableStatement stmtComponentePrincipal = con.prepareCall(queryComponentesPrincipales);
					stmtComponentePrincipal.setInt("pIdsgmv_reporte_falla", reporteFalla.getIdsgmv_reporte_falla());
					stmtComponentePrincipal.setInt("pIdsgmv_componente_principal",
							comp.getIdsgmv_componente_principal());
					stmtComponentePrincipal.setString("pDescripcion", comp.getDescripcion());
					stmtComponentePrincipal.execute();
				}
			}

			for (int i = 0; i < reporteFalla.getComponentes_llantas().size(); i++) {
				ReporteFallaComponenteLlanta comp = reporteFalla.getComponentes_llantas().get(i);
				if (comp != null && comp.getIdsgmv_componente_llanta() > 0) {
					CallableStatement stmtComponenteLlanta = con.prepareCall(queryComponentesLlantas);
					stmtComponenteLlanta.setInt("pIdsgmv_reporte_falla", reporteFalla.getIdsgmv_reporte_falla());
					stmtComponenteLlanta.setInt("pIdsgmv_componente_llanta", comp.getIdsgmv_componente_llanta());
					stmtComponenteLlanta.setString("pDescripcion", comp.getDescripcion());
					stmtComponenteLlanta.execute();
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
		return reporteFalla;
	}

	@Override
	public void eliminarAveria(int idsgmv_reporte_falla) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_eliminar_reporte_falla(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_reporte_falla", idsgmv_reporte_falla);
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
	public List<ReporteFalla> reporteAverias(FechaFilterBean filterBean) throws Exception {
		Connection con = null;
		List<ReporteFalla> reporteFallas = new ArrayList<>();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_exportar_reporte_fallas(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pMeses", filterBean.getMeses());
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				ReporteFalla reporteFalla = new ReporteFalla();
				reporteFalla.setIdsgmv_reporte_falla(rs.getInt("idsgmv_reporte_falla"));
				reporteFalla.setCod_reporte(rs.getString("cod_reporte"));
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
				reporteFalla.setFecha(df.format(rs.getDate("fecha", gmt)));
				reporteFalla.setKilometraje(rs.getFloat("kilometraje"));
				reporteFalla.setProcedencia(rs.getInt("procedencia"));
				reporteFalla.setDestino(rs.getInt("destino"));
				reporteFalla.setObservaciones(rs.getString("observaciones"));
				reporteFalla.setRemolque(new Vehiculo());
				reporteFalla.getRemolque().setIdsgmv_vehiculo(rs.getInt("idsgmv_tractor"));
				reporteFalla.getRemolque().setPlaca(rs.getString("placa_tractor"));
				reporteFalla.getRemolque().setKilometraje_total(rs.getFloat("kilometraje_tractor"));
				reporteFalla.setSemiremolque(new Vehiculo());
				reporteFalla.getSemiremolque().setIdsgmv_vehiculo(rs.getInt("idsgmv_semitrailer"));
				reporteFalla.getSemiremolque().setPlaca(rs.getString("placa_semitrailer"));
				reporteFalla.getSemiremolque().setKilometraje_total(rs.getFloat("kilometraje_semitrailer"));
				reporteFalla.setEmpleado(new Empleado());
				reporteFalla.getEmpleado().setIdsgmv_empleado(rs.getInt("idsgmv_empleado"));
				reporteFalla.getEmpleado().setNombres(rs.getString("nombres_empleado"));
				reporteFalla.getEmpleado().setApe_paterno(rs.getString("ape_pat_empleado"));
				reporteFalla.setConductor(new Empleado());
				reporteFalla.getConductor().setIdsgmv_empleado(rs.getInt("idsgmv_conductor"));
				reporteFalla.getConductor().setNombres(rs.getString("nombres_conductor"));
				reporteFalla.getConductor().setApe_paterno(rs.getString("ape_pat_coductor"));
				reporteFalla.setEstado(rs.getInt("estado"));
				reporteFallas.add(reporteFalla);
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
		return reporteFallas;
	}

	@Override
	public List<ReporteFalla> reporteViajes(int ciudadDestino) throws Exception {
		Connection con = null;
		List<ReporteFalla> reporteFallas = new ArrayList<>();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_exportar_viajes(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pCiudadDestino", ciudadDestino);
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				ReporteFalla reporteFalla = new ReporteFalla();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
				reporteFalla.setFecha(df.format(rs.getDate("fecha", gmt)));
				reporteFalla.setProcedencia(rs.getInt("procedencia"));
				reporteFalla.setDestino(rs.getInt("destino"));
				reporteFalla.setRemolque(new Vehiculo());
				reporteFalla.getRemolque().setIdsgmv_vehiculo(rs.getInt("idsgmv_tractor"));
				reporteFalla.getRemolque().setPlaca(rs.getString("placa_tractor"));
				reporteFalla.setSemiremolque(new Vehiculo());
				reporteFalla.getSemiremolque().setIdsgmv_vehiculo(rs.getInt("idsgmv_semitrailer"));
				reporteFalla.getSemiremolque().setPlaca(rs.getString("placa_semitrailer"));
				reporteFalla.setConductor(new Empleado());
				reporteFalla.getConductor().setIdsgmv_empleado(rs.getInt("idsgmv_conductor"));
				reporteFalla.getConductor().setNombres(rs.getString("nombres"));
				reporteFalla.getConductor().setApe_paterno(rs.getString("ape_paterno"));
				reporteFallas.add(reporteFalla);
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
		return reporteFallas;
	}

	@Override
	public org.transport420.sgmv.model.reporte.ReporteFalla exportarAveriaPDF(int idsgmv_reporte_falla) throws Exception {
		Connection con = null;
		org.transport420.sgmv.model.reporte.ReporteFalla averia = new org.transport420.sgmv.model.reporte.ReporteFalla();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_exportar_averia_pdf(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_reporte_falla", idsgmv_reporte_falla);
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				averia.setCod_reporte(rs.getString("cod_reporte"));
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
				averia.setFecha(df.format(rs.getDate("fecha", gmt)));
				averia.setKilometraje(rs.getFloat("kilometraje"));
				averia.setProcedencia(rs.getInt("procedencia"));
				averia.setRemolque(new Vehiculo());
				averia.getRemolque().setPlaca(rs.getString("placa_tractor"));
				averia.setSemiremolque(new Vehiculo());
				averia.getSemiremolque().setPlaca(rs.getString("placa_semitrailer"));
				averia.setConductor(new Empleado());
				averia.getConductor().setNombres(rs.getString("nombres_conductor"));
				averia.getConductor().setApe_paterno(rs.getString("ape_pat_coductor"));
				averia.setObservaciones(rs.getString("observaciones"));
			}
			rs.close();

			stmt.getMoreResults();

			rs = stmt.getResultSet();
			averia.setComponentes_principales(new ArrayList<ComponentePrincipal>());
			while (rs.next()) {
				ComponentePrincipal comp = new ComponentePrincipal();
				comp.setIdsgmv_componente_principal(rs.getInt("idsgmv_componente_principal"));
				comp.setTitulo(rs.getString("titulo"));
				comp.setDescripcion(rs.getString("descripcion"));
				comp.setMarcar(rs.getInt("marcar"));
				averia.getComponentes_principales().add(comp);
			}

			stmt.getMoreResults();

			rs = stmt.getResultSet();
			averia.setComponentes_llantas(new ArrayList<ComponenteLlanta>());
			while (rs.next()) {
				ComponenteLlanta comp = new ComponenteLlanta();
				comp.setIdsgmv_componente_llanta(rs.getInt("idsgmv_componente_llanta"));
				comp.setTitulo(rs.getString("titulo"));
				comp.setDescripcion(rs.getString("descripcion"));
				comp.setMarcar(rs.getInt("marcar"));
				averia.getComponentes_llantas().add(comp);
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
		return averia;
	}

}
