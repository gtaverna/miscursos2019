package ar.com.alsea.miscursos.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Tolerancia implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		TimerTask vodTimer = new VodTimerTask(servletContextEvent);
		Timer timer = new Timer();
		timer.schedule(vodTimer, 1 * 5 * 1000, (24 * 60 * 60 * 1000));

	}

	class VodTimerTask extends TimerTask {
		
		ServletContextEvent servletContextEvent;

		public VodTimerTask(ServletContextEvent servletContextEvent) {
			this.servletContextEvent = servletContextEvent;
		}

		@Override
		public void run() {
		
			System.out.println("Proceso de tolerancia iniciado...");
			try{
				Connection conn = new Conexion().getConnection();
				PreparedStatement pst = conn.prepareStatement(Querys.Tolerancia);
				ResultSet rs = pst.executeQuery();
				
				ArrayList<Integer> cursos = new ArrayList<Integer>();
				while (rs.next()){ 
					cursos.add(rs.getInt("id"));
				}
				pst.close();
				rs.close();
				
				if (!cursos.isEmpty()){
					
					for (Integer curso : cursos){
						
						int total = 0;
						pst = conn.prepareStatement(Querys.Tolerancia2);
						pst.setInt(1, curso);
						rs = pst.executeQuery();
						if (rs.next()){
							total = rs.getInt(1);
						}
						
						//modificado estado de los cursostienda
						pst = conn.prepareStatement(Querys.Tolerancia3);
						pst.setString(1, Info.CursoTiendaNoConfirmado);
						pst.setInt(2, curso);
						pst.setString(3, Info.CursoTiendaNuevo);
						int noconfirmados = pst.executeUpdate();
						pst.close();
						
						pst = conn.prepareStatement(Querys.Tolerancia4);
						pst.setString(1, Info.CursoTiendaConfirmado);
						pst.setInt(2, curso);
						pst.setString(3, Info.CursoTiendaBorrador);
						pst.executeUpdate();
						pst.close();
						
						//modifico el estado del curso
						pst = conn.prepareStatement(Querys.Tolerancia5);
						if (noconfirmados == total)
							pst.setString(1, Info.CursoCancelado);
						else 
							pst.setString(1, Info.CursoConfirmado);
						
						pst.setInt(2, curso);
						pst.executeUpdate();
						pst.close();
					}

				}
				
				conn.close();
				System.out.println("...finalizado proceso Tolerancia.");
			}
			catch (Exception e){
				System.out.println(e);
			}
			
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) { }

}