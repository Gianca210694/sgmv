package org.transport420.sgmv.resources;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.transport420.sgmv.resources.beans.ReporteFallasFilterBean;
import org.transport420.sgmv.servicio.AveriaServicio;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Path("/averias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AveriaResource {

	AveriaServicio averiaServicio = new AveriaServicio();

	@GET
	public List<ReporteFalla> listarAverias(@BeanParam ReporteFallasFilterBean filterBean) {
		try {
			String rutaReporte = this.getClass().getClassLoader().getResource("reportes/ReporteAveria.jrxml").getPath();
			JasperReport jr = JasperCompileManager.compileReport(rutaReporte);
			JRDataSource jrDataSource = new JREmptyDataSource();
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("codAveria", "RF00001");
			parametros.put("versionAveria", "1.00");
			parametros.put("fechaAveria", "17/09/2018");
			JasperPrint jrPrint = JasperFillManager.fillReport(jr, parametros, jrDataSource);
			JasperExportManager.exportReportToPdfFile(jrPrint, "/Users/Giancarlo/Desktop/Plantillas/reporte.pdf");
		} catch (Exception e) {
			System.out.println("error jasper: " + e.getMessage());
		}
		return averiaServicio.listarAverias(filterBean);
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