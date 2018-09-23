package org.transport420.sgmv.heroku;

import java.util.List;
import java.util.TimerTask;
import org.transport420.sgmv.model.Seguro;
import org.transport420.sgmv.servicio.SeguroServicio;
import org.transport420.sgmv.util.Util;

public class VencerSegurosTarea extends TimerTask {

	public void run() {
		SeguroServicio segurosServicio = new SeguroServicio();
		List<Seguro> segurosVencidos = segurosServicio.vencerSeguros();
		if (segurosVencidos.size() > 0) {			
			String correos = "", mensaje = "";
			correos = Util.obtenerCorreosAdmins();
			if (segurosVencidos.size() == 1) {
				mensaje = "El siguiente seguro ha vencido:\n\n";
			} else {
				mensaje = "Los siguientes seguros han vencido:\n\n";
			}
			for (Seguro seguro : segurosVencidos) {
				mensaje += "\tPoliza: " + seguro.getPoliza() + ", Placa de vehículo: " + seguro.getVehiculo().getPlaca()
						+ "\n";
			}
			Util.enviarCorreo(correos, "Seguros vencidos", mensaje);
		} else {
			System.out.println("No venció ningun seguro");
		}
	}

}
