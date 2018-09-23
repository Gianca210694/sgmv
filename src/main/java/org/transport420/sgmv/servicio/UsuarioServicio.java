package org.transport420.sgmv.servicio;

//import java.util.Map;

import org.transport420.sgmv.dao.interfaces.IUsuarioRepositorio;
import org.transport420.sgmv.daofactory.DAOFactory;
import org.transport420.sgmv.model.Usuario;

public class UsuarioServicio {

	// private Map<Integer, Usuario> usuarios = null;

	public Usuario login(Usuario usuario) {
		IUsuarioRepositorio usuarioRepositorio = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getUsuario();
		try {
			return usuarioRepositorio.login(usuario);
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			return null;
		}
	}

}
