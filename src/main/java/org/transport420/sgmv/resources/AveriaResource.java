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

import org.transport420.sgmv.model.ReporteFalla;
import org.transport420.sgmv.resources.beans.FechaFilterBean;
import org.transport420.sgmv.resources.beans.ReporteFallasFilterBean;
import org.transport420.sgmv.servicio.AveriaServicio;

@Path("/averias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AveriaResource {

	AveriaServicio averiaServicio = new AveriaServicio();

	@GET
	public List<ReporteFalla> listarAverias(@BeanParam ReporteFallasFilterBean filterBean) {
		return averiaServicio.listarAverias(filterBean);
	}

	@GET
	@Path("exportarPDF/{idAveria}")
	@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public Response exportarAveriaPDF(@PathParam("idAveria") int idAveria) {
		return Response.ok(averiaServicio.exportarAveriaPDF(idAveria))
				.header("content-disposition", "attachment; filename = averia.pdf").build();
	}

	@GET
	@Path("exportar")
	@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public Response exportarAveria(@BeanParam FechaFilterBean filterBean) {
		return Response.ok(averiaServicio.exportarAveria(filterBean))
				.header("content-disposition", "attachment; filename = averias.xls").build();
	}

	@GET
	@Path("exportarViajes/{ciudadDestino}")
	@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public Response exportarViajes(@PathParam("ciudadDestino") int ciudadDestino) {
		return Response.ok(averiaServicio.exportarViajes(ciudadDestino))
				.header("content-disposition", "attachment; filename = viajes.xls").build();
	}

	@POST
	public Response crearAveria(ReporteFalla averia, @Context UriInfo uriInfo) {
		ReporteFalla respAveria = averiaServicio.crearAveria(averia);
		String nuevoId = String.valueOf(respAveria.getIdsgmv_reporte_falla());
		URI uri = uriInfo.getAbsolutePathBuilder().path(nuevoId).build();
		return Response.created(uri).entity(respAveria).build();
	}

	@GET
	@Path("/{averiaId}")
	public ReporteFalla obtenerAveria(@PathParam("averiaId") int averiaId, @Context UriInfo uriInfo) {
		ReporteFalla averia = averiaServicio.obtenerAveria(averiaId);
		return averia;
	}

	@PUT
	@Path("/{averiaId}")
	public ReporteFalla editarAveria(@PathParam("averiaId") int averiaId, ReporteFalla averia) {
		averia.setIdsgmv_reporte_falla(averiaId);
		return averiaServicio.editarAveria(averia);
	}

	@DELETE
	@Path("/{averiaId}")
	public void eliminarAveria(@PathParam("averiaId") int averiaId) {
		averiaServicio.eliminarAveria(averiaId);
	}

}
