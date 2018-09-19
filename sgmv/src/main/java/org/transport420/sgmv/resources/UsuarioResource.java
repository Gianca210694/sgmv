package org.transport420.sgmv.resources;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.SecureRandom;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.JSONObject;
import org.transport420.sgmv.model.Usuario;
import org.transport420.sgmv.servicio.UsuarioServicio;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

	UsuarioServicio usuarioServicio = new UsuarioServicio();
	
	@POST
	public Response login(Usuario usuario, @Context UriInfo uriInfo) {
		
		try {
			String url = "https://www.google.com/recaptcha/api/siteverify?"
	                + "secret=" + "6LeOgmAUAAAAAGbz8tUeJ48BHc43va0ZvMyKLhWj"
	                + "&response=" + usuario.getCaptcha_response();
	        InputStream res = new URL(url).openStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(res, Charset.forName("UTF-8")));

	        StringBuilder sb = new StringBuilder();
	        int cp;
	        while ((cp = rd.read()) != -1) {
	            sb.append((char) cp);
	        }
	        String jsonText = sb.toString();
	        res.close();

	        JSONObject json = new JSONObject(jsonText);
	        
	        if (json.getBoolean("success")) {
	        	SecureRandom random = new SecureRandom();
	        	byte bytes[] = new byte[20];
	        	random.nextBytes(bytes);	        	
	    		Usuario respUsuario = usuarioServicio.login(usuario);
	    		respUsuario.setToken(bytes.toString());
	    		URI uri = uriInfo.getAbsolutePathBuilder().build();
	    		return Response.created(uri).entity(respUsuario).build();
	        } else {
	        	Usuario u = new Usuario();
	        	u.setMensaje_respuesta("Ha fallado la validación captcha");
	    		return Response.noContent().entity(u).build();
	        }
		} catch (Exception e) {
        	Usuario u = new Usuario();
        	u.setMensaje_respuesta(e.getMessage());
    		return Response.noContent().entity(u).build();
		}
	}
	
}
