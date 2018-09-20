package org.transport420.sgmv.dao.interfaces;

import org.transport420.sgmv.model.Usuario;

public interface IUsuarioRepositorio {

	public abstract Usuario login(Usuario usuario) throws Exception;
	
}
