package ar.com.alsea.miscursos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.com.alsea.miscursos.modelo.Curso;
import ar.com.alsea.miscursos.util.Conexion;
import ar.com.alsea.miscursos.util.Querys;

public class GestorMails {
	
	private static String HOST = "192.168.30.203";
	private static String FROM = "MisCursos@alsea.com.ar";
	Properties properties;
	
	String email_Resumen = "<html> <head> </head> <body> <table border=0 cellspacing=10 cellpadding=10> <tr> <td> <h1>MisCursos</h1> <p>Usted tiene [cursosPendientes] curso(s) pendiente(s). Por favor, confirme su asistencia.</p> </td> </tr> <tr> <td> <p>En la fecha han finalizado(s) [cursosFinalizados] curso(s). Podes ver los resultados en la herramienta.</p> </td> </tr> <tr> <td> <p>Usted tiene confirmado para mañana [cursosManana] curso(s). Para más detalle ingrese a:</p> </td> </tr> <tr> <td style='background:#5B9BD5;text-align: center'> <b><a href='http://miscursos.alsea.com.ar' style='color:white;text-decoration:none;'>miscursos.alsea.com.ar</a></b> </td> </tr> </table> <br><br><br> </body> </html>";
	String email_Cancelado = "<html> <head> </head> <body > <table border=0 cellspacing=10 cellpadding=10 style='font-family:sans-serif'> <tr> <td> <h1>MisCursos</h1> <p>Estimados, el curso [cId] - [cNombre] del día [cFecha] fue CANCELADO.</p> </td> </tr> <tr> <td> <p>Por favor, comunique a todos los colaboradores confirmados para el curso.</p> </td> </tr> <tr> <td style='background:#5B9BD5;text-align: center'> <b><a href='http://miscursos.alsea.com.ar' style='color:white;text-decoration:none;'>miscursos.alsea.com.ar</a></b> </td> </tr> </table> <br><br><br> </body> </html>";
	String email_Recordatorio = "<html> <head> </head> <body > <table border=0 cellspacing=10 cellpadding=10 style='font-family:sans-serif'> <tr> <td> <h1>MisCursos</h1> <p>Estimados, el curso [cId] - [cNombre] del día [cFecha] necesita de su confirmación.</p> </td> </tr> <tr> <td> <p>Por favor, ingrese a la herramienta y confirme asistencia.</p> </td> </tr> <tr> <td style='background:#5B9BD5;text-align: center'> <b><a href='http://miscursos.alsea.com.ar' style='color:white;text-decoration:none;'>miscursos.alsea.com.ar</a></b> </td> </tr> </table> <br><br><br> </body> </html>";
				
	public void CursoCancelado(Curso c) throws Exception {
		GestorUsuarios gu = new GestorUsuarios();
		Vector<String> mails = new Vector<String>();
		
		for (int tienda : c.getTiendas()) 
			mails.add( gu.TraerMail( tienda ) );
		
		for (int instr : c.getInstructores())
			mails.add( gu.TraerMail( instr ) );
		
		String cuerpo = email_Cancelado;
		cuerpo = cuerpo.replace("[cNombre]", c.getNombre());
		cuerpo = cuerpo.replace("[cFecha]", c.getFecha());
		cuerpo = cuerpo.replace("[cId]", c.getId().toString() );
		
		enviarEmail(mails, "MisCursos - Curso Cancelado", cuerpo);
		
	}

	public void Recordatorio(Curso c) throws Exception {
		GestorUsuarios gu = new GestorUsuarios();
		Vector<String> mails = new Vector<String>();
				
		for (int tienda : c.getTiendas()) 
			mails.add( gu.TraerMail(tienda,c.getId()) );
		
		String cuerpo = email_Recordatorio;
		cuerpo = cuerpo.replace("[cNombre]", c.getNombre());
		cuerpo = cuerpo.replace("[cFecha]", c.getFecha());
		cuerpo = cuerpo.replace("[cId]", c.getId().toString() );

		enviarEmail(mails, "MisCursos - Curso pendiente", cuerpo);
		
	}
	
	public void Resumen() throws Exception {
		String query = Querys.db+"SELECT p.id, p.email, ISNULL(p.pendiente,0) 'pendiente', ISNULL(f.finalizado,0) 'finalizado', ISNULL(m.manana,0) 'manana' FROM ( SELECT u.id, u.email, COUNT(tc.estado) 'pendiente' FROM alsea.dbo.Usuarios_aplicaciones u INNER JOIN tienda_curso tc ON tc.id_tienda = u.id WHERE u.app_nivel = 10 AND u.app_id = 8 AND tc.estado != 'FINALIZADO' AND tc.estado != 'CONFIRMADO' GROUP BY u.id, u.email ) p LEFT JOIN ( SELECT u.id, COUNT(tc.estado) 'finalizado' FROM alsea.dbo.Usuarios_aplicaciones u INNER JOIN tienda_curso tc ON tc.id_tienda = u.id INNER JOIN cursos c ON c.id = tc.id_curso WHERE u.app_nivel = 10 AND u.app_id = 8 AND tc.estado = 'FINALIZADO' AND CAST(c.fecha AS DATE) = CAST(GETDATE() AS DATE) GROUP BY u.id ) f ON f.id = p.id LEFT JOIN ( SELECT u.id, COUNT(tc.estado) 'manana' FROM alsea.dbo.Usuarios_aplicaciones u INNER JOIN tienda_curso tc ON tc.id_tienda = u.id INNER JOIN cursos c ON c.id = tc.id_curso WHERE u.app_nivel = 10 AND u.app_id = 8 AND tc.estado = 'CONFIRMADO' AND CAST(c.fecha AS DATE) = CAST(GETDATE()+1 AS DATE) GROUP BY u.id ) m ON m.id = p.id";
		Connection conn = new Conexion().getConnection();

		PreparedStatement pst = conn.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			String cuerpo = email_Resumen;
			cuerpo = cuerpo.replace("[cursosPendientes]", rs.getString("pendiente"));
			cuerpo = cuerpo.replace("[cursosFinalizados]", rs.getString("finalizado"));
			cuerpo = cuerpo.replace("[cursosManana]", rs.getString("manana"));
			
			Vector<String> mails = new Vector<String>();
			//mails.add( "sandro.desouza@alsea.com.ar" );
			mails.add( rs.getString("email") );

			enviarEmail(mails, "MisCursos - Resumen Diário", cuerpo);
		}		
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public GestorMails() {
		// Get system properties
		properties = System.getProperties();
		// Setup mail server
		properties.setProperty("mail.smtp.host", HOST);
	}

	public void enviarEmail(Vector<String> emails, String asunto, String cuerpo) throws AddressException, MessagingException {
		/*// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		// Create a default MimeMessage object.
		MimeMessage message = new MimeMessage(session);

		// Set From: header field of the header.
		message.setFrom(new InternetAddress(FROM));

		// Set To: header field of the header.
		for (String email : emails)
			message.addRecipient(Message.RecipientType.TO, new InternetAddress( email.trim() ));

		// Set Subject: header field
		message.setSubject(asunto);

		// Send the actual HTML message, as big as you like
		message.setContent(cuerpo, "text/html; charset=ISO-8859-1");

		// Send message
		Transport.send(message);*/

	}

}
