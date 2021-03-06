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

import org.transport420.sgmv.model.Vehiculo;
import org.transport420.sgmv.resources.beans.VehiculoReporteFilterBean;
import org.transport420.sgmv.resources.beans.VehiculosFilterBean;
import org.transport420.sgmv.servicio.VehiculoServicio;

@Path("/vehiculos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VehiculoResource {

	VehiculoServicio vehiculoServicio = new VehiculoServicio();

	@GET
	public List<Vehiculo> listarVehiculos(@BeanParam VehiculosFilterBean filterBean) {
		return vehiculoServicio.listarVehiculos(filterBean);
	}

	@GET
	@Path("remolques")
	public List<Vehiculo> listarRemolques() {
		return vehiculoServicio.listarRemolques();
	}

	@GET
	@Path("semiremolques")
	public List<Vehiculo> listarSemiremolques() {
		return vehiculoServicio.listarSemiremolques();
	}

	@GET
	@Path("exportar")
	@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public Response exportarVehiculo(@BeanParam VehiculoReporteFilterBean filterBean) {
		return Response.ok(vehiculoServicio.exportarVehiculo(filterBean))
				.header("content-disposition", "attachment; filename = vehiculos.xls").build();
	}

	@POST
	public Response crearVehiculo(Vehiculo vehiculo, @Context UriInfo uriInfo) {
		Vehiculo respVehiculo = vehiculoServicio.crearVehiculo(vehiculo);
		String nuevoId = String.valueOf(respVehiculo.getIdsgmv_vehiculo());
		URI uri = uriInfo.getAbsolutePathBuilder().path(nuevoId).build();
		return Response.created(uri).entity(respVehiculo).build();
	}

	@GET
	@Path("/{vehiculoId}")
	public Vehiculo obtenerVehiculo(@PathParam("vehiculoId") int vehiculoId, @Context UriInfo uriInfo) {
		Vehiculo vehiculo = vehiculoServicio.obtenerVehiculo(vehiculoId);
		return vehiculo;
	}

	@PUT
	@Path("/{vehiculoId}")
	public Vehiculo editarVehiculo(@PathParam("vehiculoId") int vehiculoId, Vehiculo vehiculo) {
		vehiculo.setIdsgmv_vehiculo(vehiculoId);
		return vehiculoServicio.editarVehiculo(vehiculo);
	}

	@PUT
	@Path("/{vehiculoId}/{estado}")
	public void cambiarEstado(@PathParam("vehiculoId") int vehiculoId, @PathParam("estado") int estado) {
		vehiculoServicio.cambiarEstado(vehiculoId, estado);
	}


	@DELETE
	@Path("/{vehiculoId}")
	public void eliminarVehiculo(@PathParam("vehiculoId") int vehiculoId) {
		vehiculoServicio.eliminarVehiculo(vehiculoId);
	}

}
