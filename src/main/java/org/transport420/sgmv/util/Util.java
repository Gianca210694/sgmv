package org.transport420.sgmv.util;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.transport420.sgmv.model.Empleado;
import org.transport420.sgmv.resources.beans.UsuariosFilterBean;
import org.transport420.sgmv.servicio.EmpleadoServicio;

public class Util {

	public static void enviarCorreo(String correos, String asunto, String mensaje) {
		final String username = "Transport420add@gmail.com";
		final String password = "admin_420";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("Transport420add@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correos));
			message.setSubject(asunto);
			message.setText(mensaje);
			Transport.send(message);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getEstado(int estado) {
		switch (estado) {
		case 1:
			return "ACTIVO";
		default:
			return "INACTIVO";
		}
	}
	
	public static String getMoneda(int moneda) {
		switch (moneda) {
		case 1:
			return "SOLES";
		default:
			return "DÓLARES";
		}
	}
	
	public static String getTipoMantenimiento(int tipoMatenimiento) {
		switch (tipoMatenimiento) {
		case 1:
			return "PREVENTIVO";
		default:
			return "CORRECTIVO";
		}
	}
	
	public static String getPrioridad(int prioridad) {
		switch (prioridad) {
		case 1:
			return "NORMAL";
		case 2:
			return "URGENTE";
		default:
			return "EMERGENCIA";
		}
	}
	
	public static String getValorOK(int valor) {
		switch (valor) {
		case 1:
			return "SI";
		default:
			return "NO";
		}
	}
	
	public static String getEjecutadoPor(int ejecutado_por) {
		switch (ejecutado_por) {
		case 1:
			return "Concesionaria";
		default:
			return "Otros Proveedores";
		}
	}
	
	public static String getDpto(int dpto) {
		switch (dpto) {
		case 1:
			return "Amazonas";
		case 2:
			return "Áncash";
		case 3:
			return "Apurímac";
		case 4:
			return "Arequipa";
		case 5:
			return "Ayacucho";
		case 6:
			return "Cajamarca";
		case 7:
			return "Callao";
		case 8:
			return "Cuzco";
		case 9:
			return "Huancavelica";
		case 10:
			return "Huánuco";
		case 11:
			return "Ica";
		case 12:
			return "Junín";
		case 13:
			return "La Libertad";
		case 14:
			return "Lambayeque";
		case 15:
			return "Lima";
		case 16:
			return "Loreto";
		case 17:
			return "Madre de Dios";
		case 18:
			return "Moquegua";
		case 19:
			return "Pasco";
		case 20:
			return "Piura";
		case 21:
			return "Puno";
		case 22:
			return "San Martín";
		case 23:
			return "Tacna";
		case 24:
			return "Tumbes";
		default:
			return "Ucayali";
		}
	}

	public static String obtenerCorreosAdmins() {
		EmpleadoServicio empleadoServicio = new EmpleadoServicio();
		UsuariosFilterBean filterBean = new UsuariosFilterBean("", 1, 1);
		List<Empleado> administradores = empleadoServicio.listarEmpleados(filterBean);
		String correos = "";
		for (Empleado admin : administradores) {
			correos += admin.getEmail() + ",";
		}
		correos = correos.substring(0, correos.length() - 1);
		return correos;
	}
	
}
