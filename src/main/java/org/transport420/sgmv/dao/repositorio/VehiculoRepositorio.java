package org.transport420.sgmv.dao.repositorio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.transport420.sgmv.dao.interfaces.IVehiculoRepositorio;
import org.transport420.sgmv.daofactory.MySqlDAOFactory;
import org.transport420.sgmv.model.Vehiculo;
import org.transport420.sgmv.resources.beans.VehiculoReporteFilterBean;
import org.transport420.sgmv.resources.beans.VehiculosFilterBean;

public class VehiculoRepositorio implements IVehiculoRepositorio {

	@Override
	public List<Vehiculo> listarVehiculos(VehiculosFilterBean filterBean) throws Exception {
		Connection con = null;
		List<Vehiculo> vehiculos = new ArrayList<>();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_listar_vehiculos(?, ?, ?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setString("pPlaca", filterBean.getPlaca());
			stmt.setString("pClase", filterBean.getClase());
			stmt.setInt("pEstado", filterBean.getEstado());
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				Vehiculo vehiculo = new Vehiculo();
				vehiculo.setIdsgmv_vehiculo(rs.getInt("idsgmv_vehiculo"));
				vehiculo.setPlaca(rs.getString("placa"));
				vehiculo.setMarca(rs.getString("marca"));
				vehiculo.setModelo(rs.getString("modelo"));
				vehiculo.setClase(rs.getString("clase"));
				vehiculo.setConstancia(rs.getString("constancia"));
				vehiculo.setCategoria(rs.getString("categoria"));
				vehiculo.setNumero_ejes(rs.getInt("numero_ejes"));
				vehiculo.setSerie_chasis(rs.getString("serie_chasis"));
				vehiculo.setAnio_produccion(rs.getInt("anio_produccion"));
				vehiculo.setCarga_util(rs.getFloat("carga_util"));
				vehiculo.setPeso_neto(rs.getFloat("peso_neto"));
				vehiculo.setKilometraje_total(rs.getFloat("km_total"));
				vehiculo.setEstado(rs.getInt("estado"));
				vehiculos.add(vehiculo);
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
		return vehiculos;
	}

	@Override
	public List<Vehiculo> listarRemolques() throws Exception {
		Connection con = null;
		List<Vehiculo> vehiculos = new ArrayList<>();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_listar_remolque()}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				Vehiculo vehiculo = new Vehiculo();
				vehiculo.setIdsgmv_vehiculo(rs.getInt("idsgmv_vehiculo"));
				vehiculo.setPlaca(rs.getString("placa"));
				vehiculo.setMarca(rs.getString("marca"));
				vehiculo.setModelo(rs.getString("modelo"));
				vehiculo.setClase(rs.getString("clase"));
				vehiculo.setConstancia(rs.getString("constancia"));
				vehiculo.setCategoria(rs.getString("categoria"));
				vehiculo.setNumero_ejes(rs.getInt("numero_ejes"));
				vehiculo.setSerie_chasis(rs.getString("serie_chasis"));
				vehiculo.setAnio_produccion(rs.getInt("anio_produccion"));
				vehiculo.setCarga_util(rs.getFloat("carga_util"));
				vehiculo.setPeso_neto(rs.getFloat("peso_neto"));
				vehiculo.setKilometraje_total(rs.getFloat("km_total"));
				vehiculo.setEstado(rs.getInt("estado"));
				vehiculos.add(vehiculo);
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
		return vehiculos;
	}

	@Override
	public List<Vehiculo> listarSemiremolques() throws Exception {
		Connection con = null;
		List<Vehiculo> vehiculos = new ArrayList<>();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_listar_semiremolque()}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				Vehiculo vehiculo = new Vehiculo();
				vehiculo.setIdsgmv_vehiculo(rs.getInt("idsgmv_vehiculo"));
				vehiculo.setPlaca(rs.getString("placa"));
				vehiculo.setMarca(rs.getString("marca"));
				vehiculo.setModelo(rs.getString("modelo"));
				vehiculo.setClase(rs.getString("clase"));
				vehiculo.setConstancia(rs.getString("constancia"));
				vehiculo.setCategoria(rs.getString("categoria"));
				vehiculo.setNumero_ejes(rs.getInt("numero_ejes"));
				vehiculo.setSerie_chasis(rs.getString("serie_chasis"));
				vehiculo.setAnio_produccion(rs.getInt("anio_produccion"));
				vehiculo.setCarga_util(rs.getFloat("carga_util"));
				vehiculo.setPeso_neto(rs.getFloat("peso_neto"));
				vehiculo.setKilometraje_total(rs.getFloat("km_total"));
				vehiculo.setEstado(rs.getInt("estado"));
				vehiculos.add(vehiculo);
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
		return vehiculos;
	}

	@Override
	public Vehiculo crearVehiculo(Vehiculo vehiculo) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_crear_vehiculo(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setString("pPlaca", vehiculo.getPlaca());
			stmt.setString("pMarca", vehiculo.getMarca());
			stmt.setString("pModelo", vehiculo.getModelo());
			stmt.setString("pClase", vehiculo.getClase());
			stmt.setString("pConstancia", vehiculo.getConstancia());
			stmt.setString("pCategoria", vehiculo.getCategoria());
			stmt.setInt("pNumeroEjes", vehiculo.getNumero_ejes());
			stmt.setString("pSerieChasis", vehiculo.getSerie_chasis());
			stmt.setInt("pAnioProduccion", vehiculo.getAnio_produccion());
			stmt.setFloat("pCargaUtil", vehiculo.getCarga_util());
			stmt.setFloat("pPesoNeto", vehiculo.getPeso_neto());
			stmt.setFloat("pKmTotal", vehiculo.getKilometraje_total());
			stmt.registerOutParameter("oIdsgmv_vehiculo", java.sql.Types.INTEGER);
			stmt.execute();
			vehiculo.setIdsgmv_vehiculo(stmt.getInt("oIdsgmv_vehiculo"));
			vehiculo.setEstado(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return vehiculo;
	}

	@Override
	public Vehiculo obtenerVehiculo(int idsgmv_vehiculo) throws Exception {
		Connection con = null;
		Vehiculo vehiculo = new Vehiculo();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_obtener_vehiculo(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_vehiculo", idsgmv_vehiculo);
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				vehiculo.setIdsgmv_vehiculo(rs.getInt("idsgmv_vehiculo"));
				vehiculo.setPlaca(rs.getString("placa"));
				vehiculo.setMarca(rs.getString("marca"));
				vehiculo.setModelo(rs.getString("modelo"));
				vehiculo.setClase(rs.getString("clase"));
				vehiculo.setConstancia(rs.getString("constancia"));
				vehiculo.setCategoria(rs.getString("categoria"));
				vehiculo.setNumero_ejes(rs.getInt("numero_ejes"));
				vehiculo.setSerie_chasis(rs.getString("serie_chasis"));
				vehiculo.setAnio_produccion(rs.getInt("anio_produccion"));
				vehiculo.setCarga_util(rs.getFloat("carga_util"));
				vehiculo.setPeso_neto(rs.getFloat("peso_neto"));
				vehiculo.setKilometraje_total(rs.getFloat("km_total"));
				vehiculo.setEstado(rs.getInt("estado"));
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
		return vehiculo;
	}

	@Override
	public Vehiculo editarVehiculo(Vehiculo vehiculo) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_editar_vehiculo(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_vehiculo", vehiculo.getIdsgmv_vehiculo());
			stmt.setString("pPlaca", vehiculo.getPlaca());
			stmt.setString("pMarca", vehiculo.getMarca());
			stmt.setString("pModelo", vehiculo.getModelo());
			stmt.setString("pClase", vehiculo.getClase());
			stmt.setString("pConstancia", vehiculo.getConstancia());
			stmt.setString("pCategoria", vehiculo.getCategoria());
			stmt.setInt("pNumeroEjes", vehiculo.getNumero_ejes());
			stmt.setString("pSerieChasis", vehiculo.getSerie_chasis());
			stmt.setInt("pAnioProduccion", vehiculo.getAnio_produccion());
			stmt.setFloat("pCargaUtil", vehiculo.getCarga_util());
			stmt.setFloat("pPesoNeto", vehiculo.getPeso_neto());
			stmt.setFloat("pKmTotal", vehiculo.getKilometraje_total());
			stmt.setInt("pEstado", vehiculo.getEstado());
			stmt.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return vehiculo;
	}

	@Override
	public void cambiarEstado(int idsgmv_vehiculo, int estado) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_cambiar_estado_vehiculo(?, ?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_vehiculo", idsgmv_vehiculo);
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
	public void eliminarVehiculo(int idsgmv_vehiculo) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_eliminar_vehiculo(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pIdsgmv_vehiculo", idsgmv_vehiculo);
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
	public List<Vehiculo> exportarVehiculos(VehiculoReporteFilterBean filterBean) throws Exception {
		Connection con = null;
		List<Vehiculo> vehiculos = new ArrayList<>();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_exportar_vehiculos(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setInt("pEstado", filterBean.getEstado());
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				Vehiculo vehiculo = new Vehiculo();
				vehiculo.setIdsgmv_vehiculo(rs.getInt("idsgmv_vehiculo"));
				vehiculo.setPlaca(rs.getString("placa"));
				vehiculo.setMarca(rs.getString("marca"));
				vehiculo.setModelo(rs.getString("modelo"));
				vehiculo.setClase(rs.getString("clase"));
				vehiculo.setConstancia(rs.getString("constancia"));
				vehiculo.setCategoria(rs.getString("categoria"));
				vehiculo.setNumero_ejes(rs.getInt("numero_ejes"));
				vehiculo.setSerie_chasis(rs.getString("serie_chasis"));
				vehiculo.setAnio_produccion(rs.getInt("anio_produccion"));
				vehiculo.setCarga_util(rs.getFloat("carga_util"));
				vehiculo.setPeso_neto(rs.getFloat("peso_neto"));
				vehiculo.setKilometraje_total(rs.getFloat("km_total"));
				vehiculo.setEstado(rs.getInt("estado"));
				vehiculos.add(vehiculo);
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
		return vehiculos;
	}

}
