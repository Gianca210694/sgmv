package org.transport420.sgmv.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reporte {

	private byte[] arreglo;

	public Reporte(byte[] arreglo) {
		super();
		this.arreglo = arreglo;
	}

	public byte[] getArreglo() {
		return arreglo;
	}

	public void setArreglo(byte[] arreglo) {
		this.arreglo = arreglo;
	}

}
