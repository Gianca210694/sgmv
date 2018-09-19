package org.transport420.sgmv.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario {

	private int idsgmv_usuario;
	private String usuario;
	private String contrasena;
	private int idsgmv_empleado;
	private int estado;
	private String token;
	private String captcha_response;
	private String mensaje_respuesta;
	private List<Rol> roles;
	private List<Modulo> modulos;

	public Usuario() {

	}

	public Usuario(int idsgmv_usuario, String usuario, String contrasena, int idsgmv_empleado, int estado, String token,
			String captcha_response, String mensaje_respuesta, List<Rol> roles, List<Modulo> modulos) {
		super();
		this.idsgmv_usuario = idsgmv_usuario;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.idsgmv_empleado = idsgmv_empleado;
		this.estado = estado;
		this.token = token;
		this.captcha_response = captcha_response;
		this.mensaje_respuesta = mensaje_respuesta;
		this.roles = roles;
		this.modulos = modulos;
	}

	public int getIdsgmv_usuario() {
		return idsgmv_usuario;
	}

	public void setIdsgmv_usuario(int idsgmv_usuario) {
		this.idsgmv_usuario = idsgmv_usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getIdsgmv_empleado() {
		return idsgmv_empleado;
	}

	public void setIdsgmv_empleado(int idsgmv_empleado) {
		this.idsgmv_empleado = idsgmv_empleado;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCaptcha_response() {
		return captcha_response;
	}

	public void setCaptcha_response(String captcha_response) {
		this.captcha_response = captcha_response;
	}

	public String getMensaje_respuesta() {
		return mensaje_respuesta;
	}

	public void setMensaje_respuesta(String mensaje_respuesta) {
		this.mensaje_respuesta = mensaje_respuesta;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

}
