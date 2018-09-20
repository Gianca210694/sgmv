package org.transport420.sgmv.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Empleado {

	private int idsgmv_empleado;
	private String nombres;
	private String ape_paterno;
	private String ape_materno;
	private String dni;
	private int sexo;
	private String fecha_nacimiento;
	private String email;
	private String telefono;
	private int estado;
	private Usuario usuario;
	private Rol rol;
	private String brevete;

	public Empleado() {

	}

	public Empleado(int idsgmv_empleado, String nombres, String ape_paterno, String ape_materno, String dni, int sexo,
			String fecha_nacimiento, String email, String telefono, int estado, Usuario usuario, Rol rol, String brevete) {
		super();
		this.idsgmv_empleado = idsgmv_empleado;
		this.nombres = nombres;
		this.ape_paterno = ape_paterno;
		this.ape_materno = ape_materno;
		this.dni = dni;
		this.sexo = sexo;
		this.fecha_nacimiento = fecha_nacimiento;
		this.email = email;
		this.telefono = telefono;
		this.estado = estado;
		this.usuario = usuario;
		this.rol = rol;
		this.brevete = brevete;
	}

	public int getIdsgmv_empleado() {
		return idsgmv_empleado;
	}

	public void setIdsgmv_empleado(int idsgmv_empleado) {
		this.idsgmv_empleado = idsgmv_empleado;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApe_paterno() {
		return ape_paterno;
	}

	public void setApe_paterno(String ape_paterno) {
		this.ape_paterno = ape_paterno;
	}

	public String getApe_materno() {
		return ape_materno;
	}

	public void setApe_materno(String ape_materno) {
		this.ape_materno = ape_materno;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getBrevete() {
		return brevete;
	}

	public void setBrevete(String brevete) {
		this.brevete = brevete;
	}

}
