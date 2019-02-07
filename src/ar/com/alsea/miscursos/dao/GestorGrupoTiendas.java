package ar.com.alsea.miscursos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import ar.com.alsea.miscursos.modelo.GrupoTiendas;
import ar.com.alsea.miscursos.modelo.Tienda;
import ar.com.alsea.miscursos.util.Conexion;
import ar.com.alsea.miscursos.util.Marcas;
import ar.com.alsea.miscursos.util.Querys;

public class GestorGrupoTiendas {

	public ArrayList<GrupoTiendas> TraerGrupos(String marca) throws Exception {
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.db + "SELECT * FROM grupotiendas where marca = ?");
		pst.setString(1, marca);
		ResultSet rs = pst.executeQuery();

		ArrayList<GrupoTiendas> uu = new ArrayList<GrupoTiendas>();
		while (rs.next()) {
			GrupoTiendas u = new GrupoTiendas(rs.getInt("id"), rs.getString("descripcion"), null,
					rs.getString("marca"));
			uu.add(u);
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uu;
	}

	public GrupoTiendas TraerGrupotienda(int id) throws Exception {
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.db + "SELECT * FROM grupotiendas where id = ?");
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			GrupoTiendas u = new GrupoTiendas(rs.getInt("id"), rs.getString("descripcion"), null,
					rs.getString("marca"));
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return u;
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

	public ArrayList<Tienda> TraerTiendas(String marca) throws Exception {
		Connection conn = new Conexion().getConnection();

		String query = "";
		switch (marca) {
		case Marcas.BK:
			query = "SELECT u.id, t.nombre FROM " + Querys.alsea_usuarios + " u JOIN " + Querys.microsBKtiendas
					+ " t ON t.email = u.email WHERE u.app_id = 8";
			break;
		case Marcas.SBX:
			query = "SELECT u.id, t.nombre FROM " + Querys.alsea_usuarios + " u JOIN " + Querys.microsSBtiendas
					+ " t ON t.email = u.email WHERE u.app_id = 8";
			break;
		}

		PreparedStatement pst = conn.prepareStatement(query);
		ResultSet rs = pst.executeQuery();

		ArrayList<Tienda> uu = new ArrayList<Tienda>();
		while (rs.next()) {
			Tienda u = new Tienda(rs.getInt("id"), rs.getString("nombre"));
			uu.add(u);
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uu;
	}

	public boolean NuevoGrupoTiendas(GrupoTiendas gt) throws Exception {
		String sql = Querys.db + "INSERT INTO grupotiendas ( descripcion, marca ) VALUES ( ?, ? );";
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, gt.getDescripcion());
		pst.setString(2, gt.getMarca());
		pst.execute();
		ResultSet rs = pst.getGeneratedKeys();
		if (rs.next()) {
			gt.setId(rs.getInt(1));
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (Tienda t : gt.getTiendas()) {
				if (!InsertTiendaGrupo(t, gt.getId()))
					return false;
			}
			return true;
		} else {
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	private boolean InsertTiendaGrupo(Tienda t, int grupo) throws Exception {
		String sql = Querys.db + "INSERT INTO tienda_grupo ( id_usuario, id_grupo ) VALUES ( ?, ? )";

		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, t.getId());
		pst.setInt(2, grupo);

		boolean x = pst.executeUpdate() > 0;
		try {
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public ArrayList<Tienda> TraerTiendasDeGrupo(String marca, int grupoid) throws Exception {
		String query = "";
		switch (marca) {
		case Marcas.BK:
			query = Querys.db + "SELECT u.id, t.nombre, tg.id_grupo FROM " + Querys.alsea_usuarios + " u JOIN "
					+ Querys.microsBKtiendas
					+ " t ON t.email = u.email left join tienda_grupo tg on tg.id_usuario = u.id and tg.id_grupo = ? WHERE u.app_id = 8";
			break;
		case Marcas.SBX:
			query = Querys.db + "SELECT u.id, t.nombre, tg.id_grupo FROM " + Querys.alsea_usuarios + " u JOIN "
					+ Querys.microsSBtiendas
					+ " t ON t.email = u.email left join tienda_grupo tg on tg.id_usuario = u.id and tg.id_grupo = ? WHERE u.app_id = 8";
			break;
		}

		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, grupoid);
		ResultSet rs = pst.executeQuery();

		ArrayList<Tienda> uu = new ArrayList<Tienda>();
		while (rs.next()) {
			Tienda u = new Tienda(rs.getInt("id"), rs.getString("nombre"));
			if (rs.getInt("id_grupo") > 0)
				u.setSeleccionado(true);
			uu.add(u);
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uu;
	}

	public List<Integer> TraerLasTiendasDelGrupo(int grupoid) throws Exception {
		String query = Querys.db + "select * FROM tienda_grupo WHERE id_grupo = ?";
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, grupoid);
		ResultSet rs = pst.executeQuery();

		List<Integer> uu = new ArrayList<Integer>();
		while (rs.next()) {
			uu.add(rs.getInt("id_usuario"));
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uu;
	}

	public boolean EditarGrupoTiendas(GrupoTiendas gt) throws Exception {
		String sql = Querys.db + "UPDATE grupotiendas SET descripcion = ? WHERE id = ?";
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, gt.getDescripcion());
		pst.setInt(2, gt.getId());
		if (pst.executeUpdate() > 0) {
			try {
				pst.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (LimpiarGrupo(gt.getId())) {
				for (Tienda t : gt.getTiendas()) {
					if (!InsertTiendaGrupo(t, gt.getId()))
						throw new Exception("Error al ingresar tienda al grupo");
				}
				return true;
			} else {
				throw new Exception("Imposible limpiar las tiendas existentes del grupo.");
			}
		} else {
			try {
				pst.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			throw new Exception("Error al grabar cambios en el grupo");
		}
	}

	private boolean LimpiarGrupo(Integer id) throws Exception {
		String sql = Querys.db + "DELETE FROM tienda_grupo WHERE id_grupo = ?";
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		pst.executeUpdate();
		try {
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean EliminarGrupo(Integer id) throws Exception {
		LimpiarGrupo(id);
		String sql = Querys.db + "DELETE FROM grupotiendas WHERE id = ?";
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		pst.executeUpdate();
		try {
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
