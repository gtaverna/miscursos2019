package ar.com.alsea.miscursos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ar.com.alsea.miscursos.util.Conexion;
import ar.com.alsea.miscursos.util.Querys;

public class GestorInstructores {

	public ArrayList<String[]> TraerInstructoresPorMarca(String marca) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.TraerInstructoresPorMarca);

		ResultSet rs = pst.executeQuery();

		ArrayList<String[]> at = new ArrayList<String[]>();
		while (rs.next()) {
			String[] t = { rs.getString("id"), rs.getString("nombre"), rs.getString("telefono") };
			at.add(t);
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return at;
	}

	public ArrayList<String[]> TraerInstructoresPorMarcaycurso(String marca, int id_curso) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.TraerInstructoresSeleccionadosPorMarca);
		pst.setInt(1, id_curso);
		ResultSet rs = pst.executeQuery();

		ArrayList<String[]> at = new ArrayList<String[]>();
		while (rs.next()) {
			String[] t = { rs.getString("id"), rs.getString("nombre"), rs.getString("telefono"),
					rs.getString("asignado") };
			at.add(t);
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return at;
	}

	public ArrayList<String[]> TraerInstructoresPorCurso(int id) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.TraerInstructoresPorCurso);
		pst.setInt(1, id);

		ResultSet rs = pst.executeQuery();

		ArrayList<String[]> at = new ArrayList<String[]>();
		while (rs.next()) {
			String[] t = { rs.getString("id"), rs.getString("nombre"), rs.getString("id_curso"),
					rs.getString("telefono") };
			at.add(t);
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return at;
	}

}
