package ar.com.alsea.miscursos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ar.com.alsea.miscursos.modelo.Asistente;
import ar.com.alsea.miscursos.modelo.Categoria;
import ar.com.alsea.miscursos.modelo.Curso;
import ar.com.alsea.miscursos.util.Conexion;
import ar.com.alsea.miscursos.util.Info;
import ar.com.alsea.miscursos.util.Marcas;
import ar.com.alsea.miscursos.util.Querys;

public class GestorAsistentes {


	public ArrayList<Asistente> TraerEmpleadosPorTiendaCategoria(Curso cur, int ccpayroll) throws Exception {
		//Conexion c = new Conexion();
		//Connection conn = c.getConnectionPayroll();
		Connection conn = new Conexion().getConnection();
		
		String query = "SELECT legajo, nombre, categoDesc 'categoria' FROM Payroll.dbo.Empleados WHERE empresa = ? and estado = 'A' AND cc = ? AND codigoDeCategoriaPorConvenio IN ( SELECT c.idpayroll FROM miscursos.dbo.categorias c INNER JOIN miscursos.dbo.categoria_curso cc ON cc.id_curso = ? AND cc.id_categoria = c.id )";
		/*switch (cur.getMarca()) {
		case Marcas.BK:
			query = Querys.PayrollBurgerKing;
			break;
		case Marcas.SBX:
			query = Querys.PayrollStarbucks;
			break;
		}
		query += Querys.TraerEmpleadosPorTiendaCategoria;

		GestorCategorias cd = new GestorCategorias();
		String queryCategorias = "";
		for (Categoria ca : cd.TraerCategoriasPorCurso(cur.getId())) {
			queryCategorias += ca.getIdpayroll() + ",";
			// System.out.println(queryCategorias);
		}
		queryCategorias = queryCategorias.substring(0, queryCategorias.length() - 1);
		query = query.replace("&CATEGORIAS&", queryCategorias);*/

		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, cur.getMarca());
		pst.setInt(2, ccpayroll);
		pst.setInt(3, cur.getId());

		ResultSet rs = pst.executeQuery();

		ArrayList<Asistente> asistentes = new ArrayList<Asistente>();
		while (rs.next()) {
			Asistente a = new Asistente();
			a.setLegajo(rs.getString("Legajo"));
			a.setNombre(rs.getString("Nombre"));
			a.setLocal(ccpayroll);
			a.setCategoria(rs.getString("Categoria"));
			asistentes.add(a);
		}try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<Asistente> validados = ValidarCorrelativaPresencia(asistentes, cur);
		if (cur.getEstado() != null && cur.getEstado().contentEquals(Info.CursoTiendaBorrador))
			return BuscarSeleccionados(validados, cur.getId());
		else
			return validados;
	}

	public boolean GuardarAsistentesPorTienda(String marca, int idcurso, String[] legajos) throws Exception {
		for (String legajo : legajos) {
			//legajo = fixLegajo(legajo, 12);
			Asistente a = TraerEmpleadoPorLegajoMarca(marca, legajo);
			if (!NuevoAsistenteEnCurso(a, idcurso))
				throw new Exception("Error al registrar empleado en el curso." + a);
		}
		return true;
	}

	public boolean BorrarAsistentes(int idcurso, int ccpayroll) throws Exception {
		Conexion co = new Conexion();
		Connection conn = co.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.BorrarAsistentesDeCursoPorTienda);
		pst.setInt(1, idcurso);
		pst.setInt(2, ccpayroll);
		boolean x = pst.executeUpdate() > 0;
		try {
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public ArrayList<Asistente> TraerAsistentesAsignadosConPresencia(int idcurso) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		String query = Querys.TraerEmpleadosAsignadosConPresencia;
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, idcurso);

		ResultSet rs = pst.executeQuery();

		ArrayList<Asistente> asistentes = new ArrayList<Asistente>();
		while (rs.next()) {
			Asistente a = rs2asistente(rs);
			asistentes.add(a);
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return asistentes;
	}

	private Asistente rs2asistente(ResultSet rs) throws Exception {
		Asistente a = new Asistente();
		a.setLegajo(rs.getString("legajo"));
		a.setId(rs.getInt("id"));
		a.setNombre(rs.getString("nombre"));
		a.setLocal(rs.getInt("local"));
		a.setCategoria(rs.getString("categoria"));
		a.setMarca(rs.getString("marca"));
		a.setEstado_asistencia(rs.getString("estado_asistencia"));
		a.setEstado_aprobacion(rs.getString("estado_aprobacion"));
		a.setResultado(rs.getFloat("resultado"));
		return a;

	}

	public ArrayList<Asistente> TraerAsistentesAsignados(int idcurso) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		String query = Querys.TraerEmpleadosAsignados;
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, idcurso);

		ResultSet rs = pst.executeQuery();

		ArrayList<Asistente> asistentes = new ArrayList<Asistente>();
		while (rs.next()) {
			Asistente a = rs2asistente(rs);
			asistentes.add(a);
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return asistentes;
	}

	public ArrayList<Asistente> TraerAsistentesAsignadosPorTienda(int idcurso, int idtienda) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		String query = Querys.TraerEmpleadosAsignadosPorTienda;
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, idcurso);
		pst.setInt(2, idtienda);

		ResultSet rs = pst.executeQuery();

		ArrayList<Asistente> asistentes = new ArrayList<Asistente>();
		while (rs.next()) {
			Asistente a = rs2asistente(rs);
			asistentes.add(a);
		}
		try {
			pst.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return asistentes;
	}

	public boolean GuardarAsistenciaDelEmpleado(int id_asistente, String paramValue) throws Exception {
		Conexion co = new Conexion();
		Connection conn = co.getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.GuardarAsistenciaDelEmpleado);

		pst.setString(1, paramValue);
		pst.setInt(2, id_asistente);

		return pst.executeUpdate() > 0;
	}

	public boolean GuardarCalificacionDelEmpleado(int id_asistente, float parseFloat, String estadoAprobacion)
			throws Exception {
		Conexion co = new Conexion();
		Connection conn = co.getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.GuardarCalificacionDelEmpleado);

		pst.setFloat(1, parseFloat);
		pst.setString(2, estadoAprobacion);
		pst.setInt(3, id_asistente);

		boolean x = pst.executeUpdate() > 0;
		try {
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	// -----------------------------------------------privates

	private ArrayList<Asistente> ValidarCorrelativaPresencia(ArrayList<Asistente> aa, Curso curso) throws Exception {

		if (curso.getGrupo_correlativa() != null && curso.getGrupo_correlativa() > 0) {
			ArrayList<Asistente> tmp = new ArrayList<Asistente>();
			for (Asistente x : aa) {
				if (validarGrupoCorrelativa(curso.getGrupo_correlativa(), x.getLegajo())) {
					tmp.add(x);
				}
			}
			aa = tmp;
		}

		if (curso.getGrupo_presencia() != null && curso.getGrupo_presencia() > 0) {
			ArrayList<Asistente> tmp = new ArrayList<Asistente>();
			for (Asistente x : aa) {
				if (validarGrupoPresencia(curso.getGrupo_presencia(), x.getLegajo())) {
					tmp.add(x);
				}
			}
			aa = tmp;
		}

		if (curso.getCorrelativa() != null && curso.getCorrelativa() > 0) {
			ArrayList<Asistente> tmp = new ArrayList<Asistente>();
			for (Asistente x : aa) {
				if (validarCorrelativa(curso.getCorrelativa(), x.getLegajo())) {
					tmp.add(x);
				}
			}
			aa = tmp;
		}

		if (curso.getPresencia() != null && curso.getPresencia() > 0) {
			ArrayList<Asistente> tmp = new ArrayList<Asistente>();
			for (Asistente x : aa) {
				if (validarPresencia(curso.getPresencia(), x.getLegajo())) {
					tmp.add(x);
				}
			}
			aa = tmp;
		}

		return aa;
	}

	private boolean validarPresencia(int presencia, String legajo) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.validarPresencia);
		pst.setInt(1, presencia);
		pst.setString(2, legajo);

		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			boolean x = rs.getInt("seleccionado") > 0;
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
			return false;
		}
	}

	private boolean validarCorrelativa(int correlativa, String legajo) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.validarCorrelativa);
		pst.setInt(1, correlativa);
		pst.setString(2, legajo);

		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			boolean x = rs.getInt("seleccionado") > 0;
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
			return false;
		}
	}

	private boolean validarGrupoPresencia(int presencia, String legajo) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.validarGrupoPresencia);
		pst.setInt(1, presencia);
		pst.setString(2, legajo);

		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			String resp = rs.getString("estado_asistencia");

			boolean x = resp == null ? false : !resp.contains(Info.AlumnoAusente);
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
			return false;
		}
	}

	private boolean validarGrupoCorrelativa(int grupo, String legajo) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.validarGrupoCorrelativa);
		pst.setInt(1, grupo);
		pst.setString(2, legajo);

		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			String resp = rs.getString("estado_aprobacion");
			boolean x = resp == null ? false : !resp.contains(Info.AlumnoDesaprobado);
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
			return false;
		}
	}

	private ArrayList<Asistente> BuscarSeleccionados(ArrayList<Asistente> aa, int idcurso) throws Exception {
		for (Asistente a : aa) {
			if (EmpleadoSeleccionado(a.getLegajo(), idcurso)) {
				a.setSeleccionado(true);
			} else {
				a.setSeleccionado(false);
			}
		}
		return aa;
	}

	private boolean EmpleadoSeleccionado(String legajo, int idcurso) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.EmpleadoSeleccionado);
		pst.setInt(1, idcurso);
		pst.setString(2, legajo);

		ResultSet rs = pst.executeQuery();
		if (rs.next()) {

			boolean x = rs.getInt("seleccionado") > 0;
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
			return false;
		}

	}

	private boolean NuevoAsistenteEnCurso(Asistente a, int idcurso) throws Exception {
		Conexion co = new Conexion();
		Connection conn = co.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.NuevoAsistenteCurso);
		pst.setInt(1, idcurso);
		pst.setString(2, a.getNombre());
		pst.setInt(3, a.getLocal());
		pst.setString(4, a.getCategoria());
		pst.setString(5, a.getMarca());
		pst.setString(6, a.getLegajo());

		boolean x = pst.executeUpdate() > 0;
		try {
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;

	}

	private String fixLegajo(String string, int length) {
		return String.format("%1$" + length + "s", string);
	}

	private Asistente TraerEmpleadoPorLegajoMarca(String marca, String legajo) throws Exception {
		//Conexion c = new Conexion();
		Connection conn = new Conexion().getConnection(); 
				//c.getConnectionPayroll();

		String query = "SELECT legajo, nombre, cc 'cc_id', categoDesc 'categoria' FROM Payroll.dbo.Empleados WHERE empresa = ? and estado = 'A' AND legajo = ?";
		/*switch (marca) {
		case Marcas.BK:
			query = Querys.PayrollBurgerKing;
			break;
		case Marcas.SBX:
			query = Querys.PayrollStarbucks;
			break;
		}
		query += Querys.TraerEmpleadoPorLegajoMarca;*/

		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, marca);
		pst.setString(2, legajo);

		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			Asistente a = new Asistente();
			a.setLegajo(rs.getString("Legajo"));
			a.setNombre(rs.getString("Nombre"));
			a.setLocal(rs.getInt("CC_id"));
			a.setCategoria(rs.getString("Categoria"));
			a.setMarca(marca);

			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return a;
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

}
