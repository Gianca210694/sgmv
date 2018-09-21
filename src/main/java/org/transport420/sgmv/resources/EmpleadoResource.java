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

import org.transport420.sgmv.model.Empleado;
import org.transport420.sgmv.resources.beans.UsuarioReporteFilterBean;
import org.transport420.sgmv.resources.beans.UsuariosFilterBean;
import org.transport420.sgmv.servicio.EmpleadoServicio;

@Path("/empleados")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmpleadoResource {

	EmpleadoServicio empleadoServicio = new EmpleadoServicio();

	@GET
	public List<Empleado> listarEmpleados(@BeanParam UsuariosFilterBean filterBean) {
		return empleadoServicio.listarEmpleados(filterBean);
	}

	@GET
	@Path("admins")
	public List<Empleado> listarAdminJefeOp() {
		return empleadoServicio.listarAdminJefeOp();
	}
	
	@GET
	@Path("exportar")
	@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public Response exportarVehiculo(@BeanParam UsuarioReporteFilterBean filterBean) {
		return Response.ok(empleadoServicio.exportarUsuario(filterBean)).header("content-disposition","attachment; filename = usuarios.xls").build();
	}

	@POST
	public Response crearEmpleado(Empleado empleado, @Context UriInfo uriInfo) {
		Empleado respEmpleado = empleadoServicio.crearEmpleado(empleado);
		String nuevoId = String.valueOf(respEmpleado.getIdsgmv_empleado());
		URI uri = uriInfo.getAbsolutePathBuilder().path(nuevoId).build();
		return Response.created(uri).entity(respEmpleado).build();
	}

	@GET
	@Path("/{empleadoId}")
	public Empleado obtenerEmpleado(@PathParam("empleadoId") int empleadoId, @Context UriInfo uriInfo) {
		Empleado empleado = empleadoServicio.obtenerEmpleado(empleadoId);
		return empleado;
	}

	@PUT
	@Path("/{empleadoId}")
	public Empleado editarEmpleado(@PathParam("empleadoId") int empleadoId, Empleado empleado) {
		empleado.setIdsgmv_empleado(empleadoId);
		return empleadoServicio.editarEmpleado(empleado);
	}

	@PUT
	@Path("/{empleadoId}/{estado}")
	public void cambiarEstado(@PathParam("empleadoId") int empleadoId, @PathParam("estado") int estado) {
		empleadoServicio.cambiarEstado(empleadoId, estado);
	}


	@DELETE
	@Path("/{empleadoId}")
	public void eliminarEmpleado(@PathParam("empleadoId") int empleadoId) {
		empleadoServicio.eliminarEmpleado(empleadoId);
	}

}
