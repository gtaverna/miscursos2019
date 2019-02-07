package ar.com.alsea.miscursos.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	public Conexion() { }

	public Connection getConnection() throws Exception {
		return getConnectionProd(); //cambiar por Test
	}
	
	public Connection getConnectionProd() throws Exception {
		String url = "jdbc:sqlserver://172.31.1.14:1433;databaseName=miscursos";
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String userName = "qliksense";
		String password = ".$3n$3";
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url, userName, password);
	}
	
	public Connection getConnectionTest() throws Exception {
		String url = "jdbc:sqlserver://172.31.1.240:1433;databaseName=miscursos-uat";
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String userName = "qliksense";
		String password = ".$3n$3";
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url, userName, password);
	}
	
	public Connection getConnectionTestNuevo() throws Exception {
		String url = "jdbc:sqlserver://172.31.1.219:1433;databaseName=miscursos-uat";
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String userName = "qliksense";
		String password = ".$3n$3";
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url, userName, password);
	}
	
	
	public Connection getConnectionPayrollx() throws Exception {
		String url = "jdbc:sqlserver://172.31.1.23:1433";
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String userName = "web";
		String password = "w3b4ls34";
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url, userName, password);
	}
}
