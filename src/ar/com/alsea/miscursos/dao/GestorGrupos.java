package ar.com.alsea.miscursos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ar.com.alsea.miscursos.modelo.Autocomplete;
import ar.com.alsea.miscursos.modelo.GruposCurso;
import ar.com.alsea.miscursos.util.Conexion;
import ar.com.alsea.miscursos.util.Querys;

public class GestorGrupos {

	public ArrayList<GruposCurso> TraerGrupos(String marca) throws Exception {
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.TraerGrupos);
		pst.setString(1, marca);

		ResultSet rs = pst.executeQuery();

		ArrayList<GruposCurso> lg = new ArrayList<GruposCurso>();
		while (rs.next()) {
			GruposCurso gc = new GruposCurso();
			gc.setId(rs.getInt("id"));
			gc.setNombre(rs.getString("nombre"));
			gc.setMarca(rs.getString("marca"));
			gc.setActivo(rs.getBoolean("activo"));
			lg.add(gc);
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lg;
	}

	public ArrayList<Autocomplete> TraerGruposPorIdyNombre(String term, String marca) throws Exception {
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.TraerGruposIDyNombre);
		pst.setString(1, marca);
		pst.setString(2, "%" + term + "%");
		ResultSet rs = pst.executeQuery();

		ArrayList<Autocomplete> lg = new ArrayList<Autocomplete>();
		while (rs.next()) {
			Autocomplete gc = new Autocomplete();
			gc.setValue(rs.getInt("id"));
			gc.setLabel(rs.getString("nombre"));
			lg.add(gc);
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lg;
	}

	public GruposCurso TraerGrupo(int id) throws Exception {
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.TraerGrupo);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			GruposCurso gc = new GruposCurso();
			gc.setId(rs.getInt("id"));
			gc.setNombre(rs.getString("nombre"));
			gc.setActivo(rs.getBoolean("activo"));
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return gc;
		} else {
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	}

	public boolean EditarGrupo(GruposCurso gc) throws Exception {
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.editarGrupo);
		pst.setString(1, gc.getNombre());
		pst.setBoolean(2, gc.isActivo());
		pst.setInt(3, gc.getId());
		boolean x = pst.executeUpdate() > 0;
		try {
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public boolean NuevoGrupo(GruposCurso gc) throws Exception {
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn
				.prepareStatement(Querys.db + "INSERT INTO grupocurso ( nombre,marca,activo ) VALUES ( ?,?,? )");
		pst.setString(1, gc.getNombre());
		pst.setString(2, gc.getMarca());
		pst.setBoolean(3, gc.isActivo());
		boolean x = pst.executeUpdate() > 0;
		try {
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

}
