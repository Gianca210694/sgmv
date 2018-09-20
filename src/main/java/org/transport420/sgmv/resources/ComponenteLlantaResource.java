package org.transport420.sgmv.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.transport420.sgmv.model.ComponenteLlanta;
import org.transport420.sgmv.servicio.ComponenteServicio;

@Path("/componentesLlantas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComponenteLlantaResource {

	ComponenteServicio componenteServicio = new ComponenteServicio();

	@GET
	public List<ComponenteLlanta> listarComponentesLlantas() {
		return componenteServicio.listarComponentesLlantas();
	}

}
