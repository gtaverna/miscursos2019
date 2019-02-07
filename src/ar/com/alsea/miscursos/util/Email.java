package ar.com.alsea.miscursos.util;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ar.com.alsea.miscursos.dao.GestorMails;

public class Email implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		TimerTask vodTimer = new EmailTask(servletContextEvent);
		
		Calendar today = Calendar.getInstance();
    	today.set(Calendar.HOUR_OF_DAY, 19);
    	today.set(Calendar.MINUTE, 0);
    	today.set(Calendar.SECOND, 0);

        
        Timer timer = new Timer();
		timer.schedule(vodTimer, today.getTime(), 1000*60*60*24 );
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) { }
	
	class EmailTask extends TimerTask {
	
		ServletContextEvent servletContextEvent;

		public EmailTask(ServletContextEvent servletContextEvent) {
			this.servletContextEvent = servletContextEvent;
		}

	    @Override
	    public void run() {
	        try {
	        	/*System.out.println("Email task iniciado....");
				new GestorMails().Resumen();
	        	System.out.println("....Email task finalizado.");*/
			} catch (Exception e) {
				System.out.println(e);
			}
	    }
	    
	
	}
}
