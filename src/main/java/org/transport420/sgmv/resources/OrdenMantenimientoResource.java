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

import org.transport420.sgmv.model.OrdenMantenimiento;
import org.transport420.sgmv.resources.beans.FechaFilterBean;
import org.transport420.sgmv.resources.beans.OrdenesMantenimientoFilterBean;
import org.transport420.sgmv.servicio.OrdenMantenimientoServicio;

@Path("/ordenesMantenimiento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrdenMantenimientoResource {
	
	OrdenMantenimientoServicio ordenMantenimientoServicio = new OrdenMantenimientoServicio();

	@GET
	public List<OrdenMantenimiento> listarOrdenesMantenimiento(@BeanParam OrdenesMantenimientoFilterBean filterBean) {
		return ordenMantenimientoServicio.listarOrdenesMantenimiento(filterBean);
	}

	@GET
	@Path("exportarPDF/{ordenMantenimientoId}")
	@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public Response exportarOrdenMantenimientoPDF(@PathParam("ordenMantenimientoId") int ordenMantenimientoId) {
		return Response.ok(ordenMantenimientoServicio.exportarOrdenMantenimientoPDF(ordenMantenimientoId))
				.header("content-disposition", "attachment; filename = orden_mantenimiento.pdf").build();
	}

	@GET
	@Path("exportar")
	@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public Response exportarOrdenMantenimiento(@BeanParam FechaFilterBean filterBean) {
		return Response.ok(ordenMantenimientoServicio.exportarOrdenMantenimiento(filterBean))
				.header("content-disposition", "attachment; filename = ordenesMantenimiento.xls").build();
	}

	@POST
	public Response crearOrdenMantenimiento(OrdenMantenimiento ordenMantenimiento, @Context UriInfo uriInfo) {
		OrdenMantenimiento respOrdenMantenimeinto = ordenMantenimientoServicio.crearOrdenMantenimiento(ordenMantenimiento);
		String nuevoId = String.valueOf(respOrdenMantenimeinto.getIdsgmv_orden_mantenimiento());
		URI uri = uriInfo.getAbsolutePathBuilder().path(nuevoId).build();
		return Response.created(uri).entity(respOrdenMantenimeinto).build();
	}

	@GET
	@Path("/{ordenMantenimientoId}")
	public OrdenMantenimiento obtenerOrdenMantenimiento(@PathParam("ordenMantenimientoId") int ordenMantenimientoId, @Context UriInfo uriInfo) {
		OrdenMantenimiento ordenMantenimiento = ordenMantenimientoServicio.obtenerOrdenMantenimiento(ordenMantenimientoId);
		return ordenMantenimiento;
	}

	@PUT
	@Path("/{ordenMantenimientoId}")
	public OrdenMantenimiento editarAveria(@PathParam("ordenMantenimientoId") int ordenMantenimientoId, OrdenMantenimiento ordenMantenimiento) {
		ordenMantenimiento.setIdsgmv_orden_mantenimiento(ordenMantenimientoId);
		return ordenMantenimientoServicio.editarOrdenMantenimiento(ordenMantenimiento);
	}

	@DELETE
	@Path("/{ordenMantenimientoId}")
	public void eliminarOrdenMantenimiento(@PathParam("ordenMantenimientoId") int ordenMantenimientoId) {
		ordenMantenimientoServicio.eliminarOrdenMantenimiento(ordenMantenimientoId);
	}
}
