package org.transport420.sgmv.dao.repositorio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.transport420.sgmv.dao.interfaces.IComponenteRepositorio;
import org.transport420.sgmv.daofactory.MySqlDAOFactory;
import org.transport420.sgmv.model.ComponenteLlanta;
import org.transport420.sgmv.model.ComponentePrincipal;

public class ComponenteRepositorio implements IComponenteRepositorio {

	@Override
	public List<ComponentePrincipal> listarComponentesPrincipales() throws Exception {
		Connection con = null;
		List<ComponentePrincipal> componentesPrincipales = new ArrayList<>();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_listar_componentes_principales()}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				ComponentePrincipal componente = new ComponentePrincipal();
				componente.setIdsgmv_componente_principal(rs.getInt("idsgmv_componente_principal"));
				componente.setTitulo(rs.getString("titulo"));
				componente.setDescripcion(rs.getString("descripcion"));
				componente.setEstado(rs.getInt("estado"));
				componentesPrincipales.add(componente);
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
		return componentesPrincipales;
	}

	@Override
	public List<ComponenteLlanta> listarComponentesLlantas() throws Exception {
		Connection con = null;
		List<ComponenteLlanta> componentesLlantas = new ArrayList<>();
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_listar_componentes_llantas()}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				ComponenteLlanta componente = new ComponenteLlanta();
				componente.setIdsgmv_componente_llanta(rs.getInt("idsgmv_componente_llanta"));
				componente.setTitulo(rs.getString("titulo"));
				componente.setDescripcion(rs.getString("descripcion"));
				componente.setEstado(rs.getInt("estado"));
				componentesLlantas.add(componente);
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
		return componentesLlantas;
	}

}
