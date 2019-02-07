package ar.com.alsea.miscursos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ar.com.alsea.miscursos.modelo.Categoria;
import ar.com.alsea.miscursos.util.Conexion;
import ar.com.alsea.miscursos.util.Querys;

public class GestorCategorias {

	public ArrayList<Categoria> TraerCategoriasPorMarca(String marca) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.TraerCategoriasPorMarca);
		pst.setString(1, marca);

		ResultSet rs = pst.executeQuery();

		ArrayList<Categoria> at = new ArrayList<Categoria>();
		while (rs.next()) {
			Categoria t = new Categoria();
			t.setId(rs.getInt("id"));
			t.setIdpayroll(rs.getInt("idpayroll"));
			t.setNombre(rs.getString("nombre"));
			t.setMarca(rs.getString("marca"));
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

	public ArrayList<Categoria> TraerCategoriasPorCurso(int id_curso) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.TraerCategoriasPorCurso);
		pst.setInt(1, id_curso);

		ResultSet rs = pst.executeQuery();

		ArrayList<Categoria> at = new ArrayList<Categoria>();
		while (rs.next()) {
			Categoria t = new Categoria();
			t.setId(rs.getInt("id"));
			t.setIdpayroll(rs.getInt("idpayroll"));
			t.setNombre(rs.getString("nombre"));
			t.setMarca(rs.getString("marca"));
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
