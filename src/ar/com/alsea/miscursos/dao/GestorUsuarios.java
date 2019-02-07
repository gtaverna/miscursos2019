package ar.com.alsea.miscursos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;

import ar.com.alsea.miscursos.modelo.Usuario;
import ar.com.alsea.miscursos.util.Conexion;
import ar.com.alsea.miscursos.util.Querys;

public class GestorUsuarios {

	public Usuario validate(String name, String pass) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.TraerUsuario);
		pst.setString(1, name);

		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			if (validateDomain(name, pass)) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNombre(rs.getString("nombre"));
				u.setEmail(rs.getString("email"));
				u.setApp_nivel(rs.getInt("app_nivel"));
				u.setMarca(rs.getString("marca"));
				u.setTienda(rs.getString("tienda"));
				u.setCcpayroll(rs.getInt("ccpayroll"));
				u.setTelefono(rs.getString("telefono"));
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

	public String TraerMail(int id, int curso) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = conn.prepareStatement(Querys.db + "select email from " + Querys.alsea_usuarios
				+ " u inner join tienda_curso tc on tc.id_tienda = u.id where u.id = ? and tc.id_curso = ? and tc.estado != 'CONFIRMADO' and tc.estado != 'FINALIZADO'");
		pst.setInt(1, id);
		pst.setInt(2, curso);

		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			String resp = rs.getString("email");
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resp;
		} else {
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}

	public String TraerMail(int id) throws Exception {
		Conexion c = new Conexion();
		Connection conn = c.getConnection();

		PreparedStatement pst = conn
				.prepareStatement("select email from " + Querys.alsea_usuarios + " u where u.id = ?");
		pst.setInt(1, id);

		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			String resp = rs.getString("email");
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resp;
		} else {
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}

	private boolean validateDomain(String name, String pass) {

		Hashtable<String, String> props = new Hashtable<String, String>();

		props.put(Context.SECURITY_PRINCIPAL, name + "@alsea-netodp.local");
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		props.put(Context.PROVIDER_URL, "ldap://172.31.1.16/");
		props.put(Context.SECURITY_CREDENTIALS, pass);

		InitialLdapContext ldap = null;
		try {
			ldap = new InitialLdapContext(props, null);
		} catch (NamingException e) {
			return false;
		}

		return (ldap != null);
	}

	public ArrayList<Usuario> TraerUsuarios() throws Exception {
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.TraerUsuarios);

		ResultSet rs = pst.executeQuery();

		ArrayList<Usuario> uu = new ArrayList<Usuario>();
		while (rs.next()) {
			Usuario u = new Usuario();
			u.setApp_nivel(rs.getInt("app_nivel"));
			u.setEmail(rs.getString("email"));
			u.setId(rs.getInt("id"));
			u.setMarca(rs.getString("marca"));
			u.setNombre(rs.getString("nombre"));
			u.setTelefono(rs.getString("telefono"));
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

	public Usuario TraerUsuario(int id) throws Exception {
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.TraerUsuarioPorId);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			Usuario u = new Usuario();
			u.setApp_nivel(rs.getInt("app_nivel"));
			u.setEmail(rs.getString("email"));
			u.setId(rs.getInt("id"));
			u.setMarca(rs.getString("marca"));
			u.setNombre(rs.getString("nombre"));
			u.setTelefono(rs.getString("telefono"));
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
			throw new Exception("Imposible acceder a la informacion del usuario.");
		}
	}

	public boolean EditarUsuario(Usuario nu) throws Exception {
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.editarUsuario);
		pst.setString(1, nu.getNombre());
		pst.setString(2, nu.getEmail());
		pst.setInt(3, nu.getApp_nivel());
		pst.setString(4, nu.getMarca());
		pst.setString(5, nu.getTelefono());
		pst.setInt(6, nu.getId());

		boolean x = pst.executeUpdate() > 0;
		try {
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public boolean NuevoUsuario(Usuario nu) throws Exception {
		Connection conn = new Conexion().getConnection();
		PreparedStatement pst = conn.prepareStatement(Querys.nuevoUsuario);
		pst.setString(1, nu.getNombre());
		pst.setString(2, nu.getEmail());
		pst.setInt(3, 8); // miscursos
		pst.setInt(4, nu.getApp_nivel());
		pst.setString(5, nu.getMarca());
		pst.setString(6, nu.getTelefono());

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
