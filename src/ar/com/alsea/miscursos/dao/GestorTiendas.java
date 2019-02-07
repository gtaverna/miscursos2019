package ar.com.alsea.miscursos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ar.com.alsea.miscursos.util.Conexion;
import ar.com.alsea.miscursos.util.Marcas;
import ar.com.alsea.miscursos.util.Querys;

public class GestorTiendas {


	public ArrayList<String[]> TraerTiendas(String marca) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = null;
		switch (marca) {
		case Marcas.ALSEA:
			return null;
		case Marcas.BK:
			pst = conn.prepareStatement(Querys.TraerTiendasBk);
			break;
		case Marcas.SBX:
			pst = conn.prepareStatement(Querys.TraerTiendasSbx);
			break;
		}

		ResultSet rs = pst.executeQuery();

		ArrayList<String[]> at = new ArrayList<String[]>();
		while (rs.next()) {
			String[] t = { rs.getString("id"), rs.getString("ccpayroll"), rs.getString("nombre") };
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

	public ArrayList<String[]> TraerTiendasPorCurso(String marca, int id_curso) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = null;
		switch (marca) {
		case Marcas.BK:
			pst = conn.prepareStatement(Querys.TraerTiendasBkPorCurso);
			break;
		case Marcas.SBX:
			pst = conn.prepareStatement(Querys.TraerTiendasSbxPorCurso);
			break;
		}

		pst.setInt(1, id_curso);
		ResultSet rs = pst.executeQuery();

		ArrayList<String[]> at = new ArrayList<String[]>();
		while (rs.next()) {
			String[] t = { rs.getString("id"), rs.getString("ccpayroll"), rs.getString("nombre"),
					rs.getString("id_curso"), rs.getString("estado") };
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
