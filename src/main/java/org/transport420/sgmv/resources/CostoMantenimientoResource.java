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

import org.transport420.sgmv.model.CostoMantenimiento;
import org.transport420.sgmv.resources.beans.CostosMantenimientoFilterBean;
import org.transport420.sgmv.resources.beans.FechaFilterBean;
import org.transport420.sgmv.servicio.CostoMantenimientoServicio;

@Path("/costosMantenimiento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CostoMantenimientoResource {

	CostoMantenimientoServicio costoMantenimientoServicio = new CostoMantenimientoServicio();

	@GET
	public List<CostoMantenimiento> listarCostosMantenimiento(@BeanParam CostosMantenimientoFilterBean filterBean) {
		return costoMantenimientoServicio.listarCostosMantenimiento(filterBean);
	}

	@GET
	@Path("exportar")
	@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public Response exportarCostoMantenimiento(@BeanParam FechaFilterBean filterBean) {
		return Response.ok(costoMantenimientoServicio.exportarCostoMantenimiento(filterBean))
				.header("content-disposition", "attachment; filename = costosMantenimiento.xls").build();
	}

	@POST
	public Response crearCostoMantenimiento(CostoMantenimiento costoMantenimiento, @Context UriInfo uriInfo) {
		CostoMantenimiento respCostoMantenimiento = costoMantenimientoServicio
				.crearCostoMantenimiento(costoMantenimiento);
		String nuevoId = String.valueOf(respCostoMantenimiento.getIdsgmv_costo_mantenimiento());
		URI uri = uriInfo.getAbsolutePathBuilder().path(nuevoId).build();
		return Response.created(uri).entity(respCostoMantenimiento).build();
	}

	@GET
	@Path("/{costoMantenimientoId}")
	public CostoMantenimiento obtenerCostoMantenimiento(@PathParam("costoMantenimientoId") int costoMantenimientoId,
			@Context UriInfo uriInfo) {
		CostoMantenimiento costoMantenimiento = costoMantenimientoServicio
				.obtenerCostoMantenimiento(costoMantenimientoId);
		return costoMantenimiento;
	}

	@PUT
	@Path("/{costoMantenimientoId}")
	public CostoMantenimiento editarCostoMantenimiento(@PathParam("costoMantenimientoId") int costoMantenimientoId,
			CostoMantenimiento costoMantenimiento) {
		costoMantenimiento.setIdsgmv_costo_mantenimiento(costoMantenimientoId);
		return costoMantenimientoServicio.editarCostoMantenimiento(costoMantenimiento);
	}

	@DELETE
	@Path("/{costoMantenimientoId}")
	public void eliminarCostoMantenimiento(@PathParam("costoMantenimientoId") int costoMantenimientoId) {
		costoMantenimientoServicio.eliminarCostoMantenimiento(costoMantenimientoId);
	}
}
