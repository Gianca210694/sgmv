package org.transport420.sgmv.servicio;

import java.util.List;

import org.transport420.sgmv.dao.interfaces.IComponenteRepositorio;
import org.transport420.sgmv.daofactory.DAOFactory;
import org.transport420.sgmv.model.ComponenteLlanta;
import org.transport420.sgmv.model.ComponentePrincipal;

public class ComponenteServicio {

	IComponenteRepositorio componenteRepositorio = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getComponente();

	public List<ComponentePrincipal> listarComponentesPrincipales() {
		try {
			return componenteRepositorio.listarComponentesPrincipales();
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			return null;
		}
	}

	public List<ComponenteLlanta> listarComponentesLlantas() {
		try {
			return componenteRepositorio.listarComponentesLlantas();
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			return null;
		}
	}

}
