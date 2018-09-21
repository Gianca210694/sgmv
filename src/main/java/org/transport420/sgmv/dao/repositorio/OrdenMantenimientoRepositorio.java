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

import org.transport420.sgmv.dao.interfaces.IOrdenMantenimientoRepositorio;
import org.transport420.sgmv.daofactory.MySqlDAOFactory;
import org.transport420.sgmv.model.Actividad;
import org.transport420.sgmv.model.Defecto;
import org.transport420.sgmv.model.Empleado;
import org.transport420.sgmv.model.Equipo;
import org.transport420.sgmv.model.OrdenMantenimiento;
import org.transport420.sgmv.model.ReporteFalla;
import org.transport420.sgmv.resources.beans.FechaFilterBean;
import org.transport420.sgmv.resources.beans.OrdenesMantenimientoFilterBean;

public class OrdenMantenimientoRepositorio implements IOrdenMantenimientoRepositorio {

	@Override
	public List<OrdenMantenimiento> listarOrdenesMantenimiento(OrdenesMantenimientoFilterBean filterBean)
			throws Exception {
		Connection con = null;
		List<OrdenMantenimiento> ordenesMantenimiento = new ArrayList<>();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_listar_ordenes_mantenimiento(?, ?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsmgv_tractor", filterBean.getRemolque());
			stmt.setInt("pIdsgmv_semitrailer", filterBean.getSemiremolque());
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				OrdenMantenimiento ordenMantenimiento = new OrdenMantenimiento();
				ordenMantenimiento.setIdsgmv_orden_mantenimiento(rs.getInt("idsgmv_orden_mantenimiento"));
				ordenMantenimiento.setCod_mantenimiento_orden(rs.getString("cod_mantenimiento_orden"));
				ordenMantenimiento.setTipo_mantenimiento(rs.getInt("tipo_mantenimiento"));
				ordenMantenimiento.setPrioridad(rs.getInt("prioridad"));
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
				ordenMantenimiento.setFecha(df.format(rs.getDate("fecha", gmt)));
				ordenMantenimiento.setKilometraje(rs.getFloat("kilometraje"));
				ordenMantenimiento.setAveria(new ReporteFalla());
				ordenMantenimiento.getAveria().setIdsgmv_reporte_falla(rs.getInt("idsgmv_reporte_falla"));
				ordenMantenimiento.getAveria().setCod_reporte(rs.getString("cod_reporte"));
				ordenMantenimiento.setEmpleado(new Empleado());
				ordenMantenimiento.getEmpleado().setIdsgmv_empleado(rs.getInt("idsgmv_empleado"));
				ordenMantenimiento.getEmpleado().setNombres(rs.getString("nombres_empleado"));
				ordenMantenimiento.getEmpleado().setApe_paterno(rs.getString("ape_pat_empleado"));
				ordenMantenimiento.setEstado(rs.getInt("estado"));
				ordenMantenimiento.setObservaciones(rs.getString("observaciones"));
				ordenesMantenimiento.add(ordenMantenimiento);
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
		return ordenesMantenimiento;
	}

	@Override
	public OrdenMantenimiento crearOrdenMantenimiento(OrdenMantenimiento ordenMantenimiento) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			con.setAutoCommit(false);
			String queryOrdenMantenimiento = "{CALL sql10257745.sp_crear_orden_mantenimiento(?, ?, ?, ?, ?, ?, ?, ?)}";
			String queryActividades = "{CALL sql10257745.sp_crear_orden_mantenimiento_actividad(?, ?, ?, ?, ?, ?)}";
			String queryDefectos = "{CALL sql10257745.sp_crear_orden_mantenimiento_defecto(?, ?, ?, ?, ?, ?)}";
			String queryEquipos = "{CALL sql10257745.sp_crear_orden_mantenimiento_equipo(?, ?, ?, ?, ?)}";

			CallableStatement stmt = con.prepareCall(queryOrdenMantenimiento);
			stmt.setInt("pTipoMantenimiento", ordenMantenimiento.getTipo_mantenimiento());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
			LocalDate date = LocalDate.parse(ordenMantenimiento.getFecha(), formatter);
			java.sql.Date sqlDate = java.sql.Date.valueOf(date);
			stmt.setDate("pFecha", sqlDate);
			stmt.setFloat("pKilometraje", ordenMantenimiento.getKilometraje());
			stmt.setInt("pPrioridad", ordenMantenimiento.getPrioridad());
			stmt.setInt("pIdsgmv_reporte_falla", ordenMantenimiento.getAveria().getIdsgmv_reporte_falla());
			stmt.setInt("pIdsgmv_empleado", ordenMantenimiento.getEmpleado().getIdsgmv_empleado());
			stmt.setString("pObservaciones", ordenMantenimiento.getObservaciones());
			stmt.registerOutParameter("oIdsgmv_orden_mantenimiento", java.sql.Types.INTEGER);
			stmt.execute();
			ordenMantenimiento.setIdsgmv_orden_mantenimiento(stmt.getInt("oIdsgmv_orden_mantenimiento"));
			ordenMantenimiento.setEstado(1);

			for (int i = 0; i < ordenMantenimiento.getActividades().size(); i++) {
				Actividad act = ordenMantenimiento.getActividades().get(i);
				if (act != null) {
					CallableStatement stmtActividad = con.prepareCall(queryActividades);
					stmtActividad.setInt("pIdsgmv_orden_mantenimiento",
							ordenMantenimiento.getIdsgmv_orden_mantenimiento());
					stmtActividad.setString("pDescripcion", act.getDescripcion());
					stmtActividad.setInt("pEjecutadoPor", act.getEjecutado_por().getIdsgmv_empleado());
					LocalDate dateProg = LocalDate.parse(act.getFecha_programacion(), formatter);
					java.sql.Date sqlDateProg = java.sql.Date.valueOf(dateProg);
					stmtActividad.setDate("pFechaProgramacion", sqlDateProg);
					LocalDate dateEjec = LocalDate.parse(act.getFecha_ejecucion(), formatter);
					java.sql.Date sqlDateEjec = java.sql.Date.valueOf(dateEjec);
					stmtActividad.setDate("pFechaEjecucion", sqlDateEjec);
					stmtActividad.registerOutParameter("oIdsgmv_actividad", java.sql.Types.INTEGER);
					stmtActividad.execute();
				}
			}

			for (int i = 0; i < ordenMantenimiento.getDefectos().size(); i++) {
				Defecto def = ordenMantenimiento.getDefectos().get(i);
				if (def != null) {
					CallableStatement stmtDefecto = con.prepareCall(queryDefectos);
					stmtDefecto.setInt("pIdsgmv_orden_mantenimiento",
							ordenMantenimiento.getIdsgmv_orden_mantenimiento());
					stmtDefecto.setString("pDescripcion", def.getDescripcion());
					stmtDefecto.setInt("pReportadoVerPor", def.getReportado_ver_por().getIdsgmv_empleado());
					LocalDate dateRept = LocalDate.parse(def.getFecha_reporte(), formatter);
					java.sql.Date sqlDateRept = java.sql.Date.valueOf(dateRept);
					stmtDefecto.setDate("pFechaReporte", sqlDateRept);
					LocalDate dateInsp = LocalDate.parse(def.getFecha_inspeccion(), formatter);
					java.sql.Date sqlDateInsp = java.sql.Date.valueOf(dateInsp);
					stmtDefecto.setDate("pFechaInspeccion", sqlDateInsp);
					stmtDefecto.registerOutParameter("oIdsgmv_defecto", java.sql.Types.INTEGER);
					stmtDefecto.execute();
				}
			}

			for (int i = 0; i < ordenMantenimiento.getEquipos().size(); i++) {
				Equipo eqp = ordenMantenimiento.getEquipos().get(i);
				if (eqp != null) {
					CallableStatement stmtEquipo = con.prepareCall(queryEquipos);
					stmtEquipo.setInt("pIdsgmv_orden_mantenimiento",
							ordenMantenimiento.getIdsgmv_orden_mantenimiento());
					stmtEquipo.setString("pDescripcion", eqp.getDescripcion());
					stmtEquipo.setInt("pInspeccionadoPor", eqp.getInspeccionado_por().getIdsgmv_empleado());
					stmtEquipo.setInt("pOk", eqp.getOk());
					stmtEquipo.registerOutParameter("oIdsgmv_equipo", java.sql.Types.INTEGER);
					stmtEquipo.execute();
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
		return ordenMantenimiento;
	}

	@Override
	public OrdenMantenimiento obtenerOrdenMantenimiento(int idsgmv_orden_mantenimiento) throws Exception {
		Connection con = null;
		OrdenMantenimiento ordenMantenimiento = new OrdenMantenimiento();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_obtener_orden_mantenimiento(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_orden_mantenimiento", idsgmv_orden_mantenimiento);
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				ordenMantenimiento.setIdsgmv_orden_mantenimiento(idsgmv_orden_mantenimiento);
				ordenMantenimiento.setCod_mantenimiento_orden(rs.getString("cod_mantenimiento_orden"));
				ordenMantenimiento.setTipo_mantenimiento(rs.getInt("tipo_mantenimiento"));
				ordenMantenimiento.setPrioridad(rs.getInt("prioridad"));
				ordenMantenimiento.setFecha(df.format(rs.getDate("fecha", gmt)));
				ordenMantenimiento.setKilometraje(rs.getFloat("kilometraje"));
				ordenMantenimiento.setAveria(new ReporteFalla());
				ordenMantenimiento.getAveria().setIdsgmv_reporte_falla(rs.getInt("idsgmv_reporte_falla"));
				ordenMantenimiento.getAveria().setCod_reporte(rs.getString("cod_reporte"));
				ordenMantenimiento.setEmpleado(new Empleado());
				ordenMantenimiento.getEmpleado().setIdsgmv_empleado(rs.getInt("idsgmv_empleado"));
				ordenMantenimiento.getEmpleado().setNombres(rs.getString("nombres_empleado"));
				ordenMantenimiento.getEmpleado().setApe_paterno(rs.getString("ape_pat_empleado"));
				ordenMantenimiento.setEstado(rs.getInt("estado"));
				ordenMantenimiento.setObservaciones(rs.getString("observaciones"));
			}
			rs.close();

			stmt.getMoreResults();

			rs = stmt.getResultSet();
			ordenMantenimiento.setActividades(new ArrayList<Actividad>());
			while (rs.next()) {
				Actividad act = new Actividad();
				act.setIdsgmv_actividad(rs.getInt("idsgmv_actividad"));
				act.setDescripcion(rs.getString("descripcion"));
				act.setEjecutado_por(new Empleado());
				act.getEjecutado_por().setIdsgmv_empleado(rs.getInt("ejecutado_por"));
				act.getEjecutado_por().setNombres(rs.getString("nombres"));
				act.getEjecutado_por().setApe_paterno(rs.getString("ape_paterno"));
				act.setFecha_programacion(df.format(rs.getDate("fecha_programacion", gmt)));
				act.setFecha_ejecucion(df.format(rs.getDate("fecha_ejecucion", gmt)));
				ordenMantenimiento.getActividades().add(act);
			}

			stmt.getMoreResults();

			rs = stmt.getResultSet();
			ordenMantenimiento.setDefectos(new ArrayList<Defecto>());
			while (rs.next()) {
				Defecto def = new Defecto();
				def.setIdsgmv_defecto(rs.getInt("idsgmv_defecto"));
				def.setDescripcion(rs.getString("descripcion"));
				def.setReportado_ver_por(new Empleado());
				def.getReportado_ver_por().setIdsgmv_empleado(rs.getInt("reportado_ver_por"));
				def.getReportado_ver_por().setNombres(rs.getString("nombres"));
				def.getReportado_ver_por().setApe_paterno(rs.getString("ape_paterno"));
				def.setFecha_reporte(df.format(rs.getDate("fecha_reporte", gmt)));
				def.setFecha_inspeccion(df.format(rs.getDate("fecha_inspeccion", gmt)));
				ordenMantenimiento.getDefectos().add(def);
			}

			stmt.getMoreResults();

			rs = stmt.getResultSet();
			ordenMantenimiento.setEquipos(new ArrayList<Equipo>());
			while (rs.next()) {
				Equipo eqp = new Equipo();
				eqp.setIdsgmv_equipo(rs.getInt("idsgmv_equipo"));
				eqp.setDescripcion(rs.getString("descripcion"));
				eqp.setInspeccionado_por(new Empleado());
				eqp.getInspeccionado_por().setIdsgmv_empleado(rs.getInt("inspeccionado_por"));
				eqp.getInspeccionado_por().setNombres(rs.getString("nombres"));
				eqp.getInspeccionado_por().setApe_paterno(rs.getString("ape_paterno"));
				eqp.setOk(rs.getInt("ok"));
				ordenMantenimiento.getEquipos().add(eqp);
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
		return ordenMantenimiento;
	}

	@Override
	public OrdenMantenimiento editarOrdenMantenimiento(OrdenMantenimiento ordenMantenimiento) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			con.setAutoCommit(false);
			String query = "{CALL sql10257745.sp_editar_orden_mantenimiento(?, ?, ?, ?, ?, ?, ?, ?)}";
			String queryActividades = "{CALL sql10257745.sp_crear_orden_mantenimiento_actividad(?, ?, ?, ?, ?, ?)}";
			String queryDefectos = "{CALL sql10257745.sp_crear_orden_mantenimiento_defecto(?, ?, ?, ?, ?, ?)}";
			String queryEquipos = "{CALL sql10257745.sp_crear_orden_mantenimiento_equipo(?, ?, ?, ?, ?)}";

			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_orden_mantenimiento", ordenMantenimiento.getIdsgmv_orden_mantenimiento());
			stmt.setInt("pTipoMantenimiento", ordenMantenimiento.getTipo_mantenimiento());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
			LocalDate date = LocalDate.parse(ordenMantenimiento.getFecha(), formatter);
			java.sql.Date sqlDate = java.sql.Date.valueOf(date);
			stmt.setDate("pFecha", sqlDate);
			stmt.setFloat("pKilometraje", ordenMantenimiento.getKilometraje());
			stmt.setInt("pPrioridad", ordenMantenimiento.getPrioridad());
			stmt.setInt("pIdsgmv_empleado", ordenMantenimiento.getEmpleado().getIdsgmv_empleado());
			stmt.setInt("pEstado", ordenMantenimiento.getEstado());
			stmt.setString("pObservaciones", ordenMantenimiento.getObservaciones());
			stmt.execute();

			for (int i = 0; i < ordenMantenimiento.getActividades().size(); i++) {
				Actividad act = ordenMantenimiento.getActividades().get(i);
				if (act != null) {
					CallableStatement stmtActividad = con.prepareCall(queryActividades);
					stmtActividad.setInt("pIdsgmv_orden_mantenimiento",
							ordenMantenimiento.getIdsgmv_orden_mantenimiento());
					stmtActividad.setString("pDescripcion", act.getDescripcion());
					stmtActividad.setInt("pEjecutadoPor", act.getEjecutado_por().getIdsgmv_empleado());
					LocalDate dateProg = LocalDate.parse(act.getFecha_programacion(), formatter);
					java.sql.Date sqlDateProg = java.sql.Date.valueOf(dateProg);
					stmtActividad.setDate("pFechaProgramacion", sqlDateProg);
					LocalDate dateEjec = LocalDate.parse(act.getFecha_ejecucion(), formatter);
					java.sql.Date sqlDateEjec = java.sql.Date.valueOf(dateEjec);
					stmtActividad.setDate("pFechaEjecucion", sqlDateEjec);
					stmtActividad.registerOutParameter("oIdsgmv_actividad", java.sql.Types.INTEGER);
					stmtActividad.execute();
				}
			}

			for (int i = 0; i < ordenMantenimiento.getDefectos().size(); i++) {
				Defecto def = ordenMantenimiento.getDefectos().get(i);
				if (def != null) {
					CallableStatement stmtDefecto = con.prepareCall(queryDefectos);
					stmtDefecto.setInt("pIdsgmv_orden_mantenimiento",
							ordenMantenimiento.getIdsgmv_orden_mantenimiento());
					stmtDefecto.setString("pDescripcion", def.getDescripcion());
					stmtDefecto.setInt("pReportadoVerPor", def.getReportado_ver_por().getIdsgmv_empleado());
					LocalDate dateRept = LocalDate.parse(def.getFecha_reporte(), formatter);
					java.sql.Date sqlDateRept = java.sql.Date.valueOf(dateRept);
					stmtDefecto.setDate("pFechaReporte", sqlDateRept);
					LocalDate dateInsp = LocalDate.parse(def.getFecha_inspeccion(), formatter);
					java.sql.Date sqlDateInsp = java.sql.Date.valueOf(dateInsp);
					stmtDefecto.setDate("pFechaInspeccion", sqlDateInsp);
					stmtDefecto.registerOutParameter("oIdsgmv_defecto", java.sql.Types.INTEGER);
					stmtDefecto.execute();
				}
			}

			for (int i = 0; i < ordenMantenimiento.getEquipos().size(); i++) {
				Equipo eqp = ordenMantenimiento.getEquipos().get(i);
				if (eqp != null) {
					CallableStatement stmtEquipo = con.prepareCall(queryEquipos);
					stmtEquipo.setInt("pIdsgmv_orden_mantenimiento",
							ordenMantenimiento.getIdsgmv_orden_mantenimiento());
					stmtEquipo.setString("pDescripcion", eqp.getDescripcion());
					stmtEquipo.setInt("pInspeccionadoPor", eqp.getInspeccionado_por().getIdsgmv_empleado());
					stmtEquipo.setInt("pOk", eqp.getOk());
					stmtEquipo.registerOutParameter("oIdsgmv_equipo", java.sql.Types.INTEGER);
					stmtEquipo.execute();
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
		return ordenMantenimiento;
	}

	@Override
	public void eliminarOrdenMantenimiento(int idsgmv_orden_mantenimiento) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_eliminar_orden_mantenimiento(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_orden_mantenimiento", idsgmv_orden_mantenimiento);
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
	public List<OrdenMantenimiento> reporteOrdenesMantenimiento(FechaFilterBean filterBean)
			throws Exception {
		Connection con = null;
		List<OrdenMantenimiento> ordenesMantenimiento = new ArrayList<>();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_exportar_ordenes_mantenimiento(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pMeses", filterBean.getMeses());
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				OrdenMantenimiento ordenMantenimiento = new OrdenMantenimiento();
				ordenMantenimiento.setIdsgmv_orden_mantenimiento(rs.getInt("idsgmv_orden_mantenimiento"));
				ordenMantenimiento.setCod_mantenimiento_orden(rs.getString("cod_mantenimiento_orden"));
				ordenMantenimiento.setTipo_mantenimiento(rs.getInt("tipo_mantenimiento"));
				ordenMantenimiento.setPrioridad(rs.getInt("prioridad"));
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));
				ordenMantenimiento.setFecha(df.format(rs.getDate("fecha", gmt)));
				ordenMantenimiento.setKilometraje(rs.getFloat("kilometraje"));
				ordenMantenimiento.setAveria(new ReporteFalla());
				ordenMantenimiento.getAveria().setIdsgmv_reporte_falla(rs.getInt("idsgmv_reporte_falla"));
				ordenMantenimiento.getAveria().setCod_reporte(rs.getString("cod_reporte"));
				ordenMantenimiento.setEmpleado(new Empleado());
				ordenMantenimiento.getEmpleado().setIdsgmv_empleado(rs.getInt("idsgmv_empleado"));
				ordenMantenimiento.getEmpleado().setNombres(rs.getString("nombres_empleado"));
				ordenMantenimiento.getEmpleado().setApe_paterno(rs.getString("ape_pat_empleado"));
				ordenMantenimiento.setEstado(rs.getInt("estado"));
				ordenMantenimiento.setObservaciones(rs.getString("observaciones"));
				ordenesMantenimiento.add(ordenMantenimiento);
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
		return ordenesMantenimiento;
	}

}
