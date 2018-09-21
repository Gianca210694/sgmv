package org.transport420.sgmv.heroku;

import java.util.List;
import java.util.TimerTask;
import org.transport420.sgmv.model.Seguro;
import org.transport420.sgmv.servicio.SeguroServicio;

public class VencerSegurosTarea extends TimerTask {

	public void run() {
		SeguroServicio segurosServicio = new SeguroServicio();
		List<Seguro> segurosVencidos = segurosServicio.vencerSeguros();
		if (segurosVencidos.size() > 0 ) {
			System.out.println("Los siguientes seguros han vencido:");
			for (Seguro seguro : segurosVencidos) {
				System.out.println(
						"Poliza: " + seguro.getPoliza() + ", Placa de vehículo: " + seguro.getVehiculo().getPlaca());
			}
		} else {
			System.out.println("No venció ningun seguro");
		}
	}
}
