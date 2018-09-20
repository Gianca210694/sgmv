package org.transport420.sgmv.dao.repositorio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.transport420.sgmv.dao.interfaces.IUsuarioRepositorio;
import org.transport420.sgmv.daofactory.MySqlDAOFactory;
import org.transport420.sgmv.model.Modulo;
import org.transport420.sgmv.model.Rol;
import org.transport420.sgmv.model.Usuario;

public class UsuarioRepositorio implements IUsuarioRepositorio {

	@Override
	public Usuario login(Usuario usuario) throws Exception {
		Connection con = null;
		try {
			con = MySqlDAOFactory.obtenerConexion();
			String query = "{CALL sql10257745.sp_login_usuario(?, ?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setString("pUsuario", usuario.getUsuario());
			stmt.setString("pContrasena", usuario.getContrasena());

			stmt.execute();

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				usuario.setIdsgmv_usuario(rs.getInt("idsgmv_usuario"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setIdsgmv_empleado(rs.getInt("idsgmv_empleado"));
				usuario.setEstado(rs.getInt("estado"));
				usuario.setPrimera_vez(rs.getInt("primera_vez"));
				usuario.setContrasena(null);
			}
			rs.close();

			stmt.getMoreResults();

			rs = stmt.getResultSet();
			usuario.setRoles(new ArrayList<Rol>());
			while (rs.next()) {
				Rol rol = new Rol(rs.getInt("idsgmv_rol"), rs.getString("rol"), rs.getString("descripcion"),
						rs.getInt("estado"));
				usuario.getRoles().add(rol);
			}

			stmt.getMoreResults();

			rs = stmt.getResultSet();
			usuario.setModulos(new ArrayList<Modulo>());
			while (rs.next()) {
				Modulo modulo = new Modulo(rs.getInt("idsgmv_modulo"), rs.getString("titulo"),
						rs.getString("descripcion"), rs.getInt("idsgmv_modulo_padre"), rs.getInt("estado"));
				usuario.getModulos().add(modulo);
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
		return usuario;
	}

}
