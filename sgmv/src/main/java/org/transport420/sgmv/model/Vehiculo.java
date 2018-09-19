package org.transport420.sgmv.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vehiculo {

	private int idsgmv_vehiculo;
	private String placa;
	private String marca;
	private String modelo;
	private String clase;
	private String constancia;
	private String categoria;
	private int numero_ejes;
	private String serie_chasis;
	private int anio_produccion;
	private float carga_util;
	private float peso_neto;
	private float kilometraje_total;
	private int estado;

	public Vehiculo() {

	}

	public Vehiculo(int idsgmv_vehiculo, String placa, String marca, String modelo, String clase, String constancia,
			String categoria, int numero_ejes, String serie_chasis, int anio_produccion, float carga_util,
			float peso_neto, float kilometraje_total, int estado) {
		super();
		this.idsgmv_vehiculo = idsgmv_vehiculo;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.clase = clase;
		this.constancia = constancia;
		this.categoria = categoria;
		this.numero_ejes = numero_ejes;
		this.serie_chasis = serie_chasis;
		this.anio_produccion = anio_produccion;
		this.carga_util = carga_util;
		this.peso_neto = peso_neto;
		this.kilometraje_total = kilometraje_total;
		this.estado = estado;
	}

	public int getIdsgmv_vehiculo() {
		return idsgmv_vehiculo;
	}

	public void setIdsgmv_vehiculo(int idsgmv_vehiculo) {
		this.idsgmv_vehiculo = idsgmv_vehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getConstancia() {
		return constancia;
	}

	public void setConstancia(String constancia) {
		this.constancia = constancia;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getNumero_ejes() {
		return numero_ejes;
	}

	public void setNumero_ejes(int numero_ejes) {
		this.numero_ejes = numero_ejes;
	}

	public String getSerie_chasis() {
		return serie_chasis;
	}

	public void setSerie_chasis(String serie_chasis) {
		this.serie_chasis = serie_chasis;
	}

	public int getAnio_produccion() {
		return anio_produccion;
	}

	public void setAnio_produccion(int anio_produccion) {
		this.anio_produccion = anio_produccion;
	}

	public float getCarga_util() {
		return carga_util;
	}

	public void setCarga_util(float carga_util) {
		this.carga_util = carga_util;
	}

	public float getPeso_neto() {
		return peso_neto;
	}

	public void setPeso_neto(float peso_neto) {
		this.peso_neto = peso_neto;
	}

	public float getKilometraje_total() {
		return kilometraje_total;
	}

	public void setKilometraje_total(float kilometraje_total) {
		this.kilometraje_total = kilometraje_total;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
