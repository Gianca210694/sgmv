package org.transport420.sgmv.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.transport420.sgmv.model.Seguro;
import org.transport420.sgmv.resources.beans.SeguroReporteFilterBean;
import org.transport420.sgmv.resources.beans.SegurosFilterBean;
import org.transport420.sgmv.servicio.SeguroServicio;

@Path("/seguros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SeguroResource {

	SeguroServicio seguroServicio = new SeguroServicio();

	@GET
	public List<Seguro> listarSeguros(@BeanParam SegurosFilterBean filterBean) {
		return seguroServicio.listarSeguros(filterBean);
	}
	
	@GET
	@Path("exportar")
	@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public Response exportarSeguro(@BeanParam SeguroReporteFilterBean filterBean) {
		return Response.ok(seguroServicio.exportarSeguro(filterBean)).header("content-disposition","attachment; filename = seguros.xls").build();
	}

	@POST
	public Response crearSeguro(Seguro seguro, @Context UriInfo uriInfo) {
		Seguro respSeguro = seguroServicio.crearSeguro(seguro);
		String nuevoId = String.valueOf(respSeguro.getIdsgmv_seguro());
		URI uri = uriInfo.getAbsolutePathBuilder().path(nuevoId).build();
		return Response.created(uri).entity(respSeguro).build();
	}

	@GET
	@Path("/{seguroId}")
	public Seguro obtenerSeguro(@PathParam("seguroId") int seguroId, @Context UriInfo uriInfo) {
		Seguro seguro = seguroServicio.obtenerSeguro(seguroId);
		return seguro;
	}

	@PUT
	@Path("/{seguroId}")
	public Seguro editarSeguro(@PathParam("seguroId") int seguroId, Seguro seguro) {
		seguro.setIdsgmv_seguro(seguroId);
		return seguroServicio.editarSeguro(seguro);
	}

	@DELETE
	@Path("/{seguroId}")
	public void eliminarSeguro(@PathParam("seguroId") int seguroId) {
		seguroServicio.eliminarSeguro(seguroId);
	}
}
