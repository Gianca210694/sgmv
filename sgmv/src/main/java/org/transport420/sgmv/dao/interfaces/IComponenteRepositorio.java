package org.transport420.sgmv.dao.interfaces;

import java.util.List;

import org.transport420.sgmv.model.ComponenteLlanta;
import org.transport420.sgmv.model.ComponentePrincipal;

public interface IComponenteRepositorio {

	public abstract List<ComponentePrincipal> listarComponentesPrincipales() throws Exception;

	public abstract List<ComponenteLlanta> listarComponentesLlantas() throws Exception;
}
