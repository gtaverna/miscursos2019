package ar.com.alsea.miscursos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ar.com.alsea.miscursos.modelo.Autocomplete;
import ar.com.alsea.miscursos.modelo.Curso;
import ar.com.alsea.miscursos.modelo.Usuario;
import ar.com.alsea.miscursos.util.Conexion;
import ar.com.alsea.miscursos.util.Info;
import ar.com.alsea.miscursos.util.Querys;

public class GestorCursos {

	public GestorCursos() {
	}

	public ArrayList<Curso> TraerCursosPorInstructor(int userid, boolean activo) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = conn.prepareStatement(
				activo ? Querys.TraerCursosPorInstructor : Querys.TraerCursosFinalizadosPorInstructor);
		pst.setInt(1, userid);
		ResultSet rs = pst.executeQuery();
		ArrayList<Curso> ac = new ArrayList<Curso>();
		while (rs.next()) {
			ac.add(rs2curso(rs));
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ac;
	}

	public ArrayList<Curso> TraerCursosPorTienda(int userid, boolean activo) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = conn
				.prepareStatement(activo ? Querys.TraerCursosPorTienda : Querys.TraerCursosFinalizadosPorTienda);
		pst.setInt(1, userid);

		ResultSet rs = pst.executeQuery();

		ArrayList<Curso> ac = new ArrayList<Curso>();
		while (rs.next()) {
			ac.add(rs2curso(rs));
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ac;
	}

	public ArrayList<Curso> TraerCursosPorMarca(String marca, boolean activo) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = conn
				.prepareStatement(activo ? Querys.TraerCursosPorMarca : Querys.TraerCursosFinalizadosPorMarca);
		pst.setString(1, marca);

		ResultSet rs = pst.executeQuery();
		ArrayList<Curso> ac = new ArrayList<Curso>();
		while (rs.next()) {
			ac.add(rs2curso(rs));
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ac;
	}

	private Curso rs2curso(ResultSet rs) throws SQLException {
		Curso cu = new Curso();
		cu.setId(rs.getInt("id"));
		cu.setNombre(rs.getString("nombre"));
		cu.setFecha(rs.getString("fecha"));
		cu.setCorrelativa(rs.getInt("correlativa"));
		cu.setPresencia(rs.getInt("presencia"));
		cu.setLugar(rs.getString("lugar"));
		cu.setAprobacion(rs.getFloat("aprobacion"));
		cu.setEstado(rs.getString("estado"));
		cu.setGrupo(rs.getInt("grupo") > 0 ? rs.getInt("grupo") : null);
		cu.setGrupo_correlativa(rs.getInt("grupoaprobacion"));
		cu.setGrupo_presencia(rs.getInt("grupopresencia"));
		cu.setMarca(rs.getString("marca"));
		cu.setNombre_correlativa(rs.getString("nombre_correlativa"));
		cu.setNombre_presencia(rs.getString("nombre_presencia"));
		cu.setNombre_grupo_correlativa(rs.getString("nombre_grupo_correlativa"));
		cu.setNombre_grupo_presencia(rs.getString("nombre_grupo_presencia"));
		cu.setNombre_grupo(rs.getString("nombre_grupo"));
		cu.setDescripcion(rs.getString("descripcion"));
		cu.setTolerancia(rs.getInt("tolerancia"));
		return cu;
	}

	public Curso TraerCurso(int id) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.TraerCurso);
		pst.setInt(1, id);

		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			Curso cu = rs2curso(rs);
			cu.setTiendas(TraerTiendasPorCurso(id));
			cu.setInstructores(TraerInstructoresPorCurso(id));
			cu.setCategorias(TraerCategoriasPorCurso(id));
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cu;

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

	public Curso TraerCursoPorTienda(int idcurso, int idtienda) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.TraerCursoConEstadoPorTienda);
		pst.setInt(1, idcurso);
		pst.setInt(2, idtienda);

		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			Curso cu = rs2curso(rs);
			cu.setInstructores(TraerInstructoresPorCurso(idcurso));
			cu.setCategorias(TraerCategoriasPorCurso(idcurso));
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cu;

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

	private Integer[] TraerCategoriasPorCurso(int idcurso) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.TraerCategoriasPorCurso);
		pst.setInt(1, idcurso);

		ResultSet rs = pst.executeQuery();
		ArrayList<Integer> ai = new ArrayList<Integer>();
		while (rs.next()) {
			ai.add(rs.getInt("id"));
		}

		Integer[] ints = new Integer[ai.size()];
		for (int i = 0, len = ai.size(); i < len; i++)
			ints[i] = ai.get(i);
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ints;
	}

	private Integer[] TraerInstructoresPorCurso(int id) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.TraerInstructoresPorCurso);
		pst.setInt(1, id);

		ResultSet rs = pst.executeQuery();
		ArrayList<Integer> ai = new ArrayList<Integer>();
		while (rs.next()) {
			ai.add(rs.getInt("id"));
		}

		Integer[] ints = new Integer[ai.size()];
		for (int i = 0, len = ai.size(); i < len; i++)
			ints[i] = ai.get(i);
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ints;
	}

	private Integer[] TraerTiendasPorCurso(int id) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.TraerTiendasPorCurso);
		pst.setInt(1, id);

		ResultSet rs = pst.executeQuery();
		ArrayList<Integer> ai = new ArrayList<Integer>();
		while (rs.next()) {
			ai.add(rs.getInt("id_tienda"));
		}

		Integer[] ints = new Integer[ai.size()];
		for (int i = 0, len = ai.size(); i < len; i++)
			ints[i] = ai.get(i);
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ints;
	}

	public boolean NuevoCurso(Curso c) throws Exception {
		Conexion co = new Conexion();
		Connection conn = co.getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.NuevoCurso, Statement.RETURN_GENERATED_KEYS);

		pst.setString(1, c.getNombre());
		pst.setString(2, c.getFecha());

		if (c.getCorrelativa() != null)
			pst.setInt(3, c.getCorrelativa());
		else
			pst.setNull(3, java.sql.Types.INTEGER);

		if (c.getPresencia() != null)
			pst.setInt(4, c.getPresencia());
		else
			pst.setNull(4, java.sql.Types.INTEGER);

		pst.setString(5, c.getLugar());
		pst.setFloat(6, c.getAprobacion());
		pst.setString(7, Info.CursoPendienteTiendas);
		pst.setString(8, c.getMarca());

		if (c.getGrupo() != null)
			pst.setInt(9, c.getGrupo());
		else
			pst.setNull(9, java.sql.Types.INTEGER);

		if (c.getGrupo_correlativa() != null)
			pst.setInt(10, c.getGrupo_correlativa());
		else
			pst.setNull(10, java.sql.Types.INTEGER);

		if (c.getGrupo_presencia() != null)
			pst.setInt(11, c.getGrupo_presencia());
		else
			pst.setNull(11, java.sql.Types.INTEGER);

		pst.setString(12, c.getDescripcion());

		if (c.getTolerancia() != null)
			pst.setInt(13, c.getTolerancia());
		else
			pst.setNull(13, java.sql.Types.INTEGER);

		pst.execute();
		ResultSet rs = pst.getGeneratedKeys();
		if (rs.next()) {
			c.setId(rs.getInt(1));
			rs.close();
			for (int id_categoria : c.getCategorias()) {
				InsertCategoriaCurso(c.getId(), id_categoria);
			}

			for (int id_tienda : c.getTiendas()) {
				GestorAsistentes asist = new GestorAsistentes();
				if (!asist.TraerEmpleadosPorTiendaCategoria(c, getcecos(id_tienda)).isEmpty()) {
					InsertTiendaCurso(c.getId(), id_tienda);
				}
				// InsertTiendaCurso(c.getId(), id_tienda);
			}
			for (int id_instructor : c.getInstructores()) {
				InsertInstructorCurso(c.getId(), id_instructor);
			}
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
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

	private boolean InsertTiendaCurso(int id_curso, int id_tienda) throws Exception {
		Conexion co = new Conexion();
		Connection conn = co.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.NuevoCursoTienda);
		pst.setInt(1, id_curso);
		pst.setInt(2, id_tienda);
		pst.setString(3, Info.CursoTiendaNuevo);

		boolean x = pst.execute();
		try {
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	private boolean InsertInstructorCurso(int id_curso, int id_instructor) throws Exception {
		Conexion co = new Conexion();
		Connection conn = co.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.NuevoCursoInstructor);
		pst.setInt(1, id_instructor);
		pst.setInt(2, id_curso);

		boolean x = pst.execute();
		try {
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	private boolean InsertCategoriaCurso(int id_curso, int id_categoria) throws Exception {
		Conexion co = new Conexion();
		Connection conn = co.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.NuevoCursoCategoria);
		pst.setInt(1, id_categoria);
		pst.setInt(2, id_curso);
		boolean x = pst.execute();
		try {
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public boolean EditarCurso(int id, Curso c) throws Exception {
		Conexion co = new Conexion();
		Connection conn = co.getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.EditarCurso);
		PreparedStatement pst1 = conn.prepareStatement(Querys.LimpiarInstructor);

		pst.setString(1, c.getNombre());
		pst.setString(2, c.getFecha());
		pst.setString(3, c.getLugar());
		pst.setFloat(4, c.getAprobacion());
		pst.setInt(5, c.getGrupo());
		// pst.setInt(6, c.getGrupo_correlativa());
		// pst.setInt(7, c.getGrupo_presencia());
		pst.setString(6, c.getDescripcion());
		pst.setInt(7, c.getTolerancia());

		pst.setInt(8, id);

		// limpio los instructores
		pst1.setInt(1, c.getId());
		pst1.executeUpdate();

		// agrego los nuevos instrucores
		for (int id_instructor : c.getInstructores()) {
			InsertInstructorCurso(c.getId(), id_instructor);
		}

		boolean x = pst.executeUpdate() > 0;
		try {
			pst.close();
			pst1.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public boolean ModificarEstadoCurso(int idcurso, String estado) throws Exception {
		Conexion co = new Conexion();
		Connection conn = co.getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.ModificarEstadoCurso);

		pst.setString(1, estado);
		pst.setInt(2, idcurso);

		boolean x = pst.executeUpdate() > 0;
		try {
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public boolean ModificarEstadoCursoTienda(int idcurso, int idtienda, String estado) throws Exception {
		Conexion co = new Conexion();
		Connection conn = co.getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.ModificarEstadoCursoTienda);

		pst.setString(1, estado);
		pst.setInt(2, idcurso);
		pst.setInt(3, idtienda);

		boolean x = pst.executeUpdate() > 0;
		try {
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public boolean VerificarEstadoCurso(int idcurso) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.VerificarEstadoCurso);
		pst.setInt(1, idcurso);
		pst.setString(2, Info.CursoTiendaConfirmado);

		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			int i = rs.getInt("estado");
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (i == 0) {
				return ModificarEstadoCurso(idcurso, Info.CursoConfirmado);
			}

		} else {
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public ArrayList<Curso> TraerCursosPorBusqueda(String busqueda, Usuario u) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		String query = Querys.TraerCursoPorBusqueda;
		switch (u.getApp_nivel()) {
		case 10:
			query = query.replace("%$JOIN$%",
					"JOIN tienda_curso tc ON tc.id_curso = c.id and tc.id_tienda = " + u.getId());
			break;
		case 20:
			query = query.replace("%$JOIN$%",
					"JOIN instructor_curso ic ON ic.id_curso = c.id and ic.id_instructor = " + u.getId());
			break;
		default:
			query = query.replace("%$JOIN$%", "");
			break;
		}

		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, "%" + busqueda + "%");
		pst.setString(2, "%" + busqueda + "%");

		ResultSet rs = pst.executeQuery();
		ArrayList<Curso> ac = new ArrayList<Curso>();
		while (rs.next()) {
			ac.add(rs2curso(rs));
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ac;
	}

	public ArrayList<Autocomplete> TraerCursosPorIdyNombre(String term) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		String query = Querys.TraerCursoCorrelativa;

		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, "%" + term + "%");
		pst.setString(2, "%" + term + "%");

		ResultSet rs = pst.executeQuery();
		ArrayList<Autocomplete> ac = new ArrayList<Autocomplete>();
		while (rs.next()) {
			Autocomplete a = new Autocomplete();
			a.setValue(rs.getInt("id"));
			a.setLabel(rs.getString("nombre"));
			ac.add(a);
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ac;
	}

	private Integer getcecos(int id_tienda) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();
		PreparedStatement pst = conn.prepareStatement("WITH tiendas AS ( SELECT t.email, t.nombre, t.ccpayroll FROM "
				+ Querys.microsBKtiendas + " t UNION ALL SELECT t.email, t.nombre, t.ccpayroll FROM "
				+ Querys.microsSBtiendas + " t ) SELECT t.ccpayroll FROM " + Querys.alsea_usuarios
				+ " u LEFT JOIN tiendas t ON t.email = u.email WHERE app_id = 8 AND u.id = ?");
		pst.setInt(1, id_tienda);

		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			int x = rs.getInt("ccpayroll");
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return x;
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

	public boolean InsertarMotivoCancelacion(int id, String motivo) throws Exception {
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn
				.prepareStatement(Querys.db + "INSERT INTO cancelaciones ( id_curso,descripcion) VALUES ( ?,?)");
		pst.setInt(1, id);
		pst.setString(2, motivo);
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
