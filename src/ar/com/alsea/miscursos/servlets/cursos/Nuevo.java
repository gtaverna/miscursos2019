package ar.com.alsea.miscursos.servlets.cursos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.alsea.miscursos.dao.GestorCategorias;
import ar.com.alsea.miscursos.dao.GestorCursos;
import ar.com.alsea.miscursos.dao.GestorGrupoTiendas;
import ar.com.alsea.miscursos.dao.GestorInstructores;
import ar.com.alsea.miscursos.dao.GestorTiendas;
import ar.com.alsea.miscursos.modelo.Curso;
import ar.com.alsea.miscursos.modelo.Usuario;
import ar.com.alsea.miscursos.util.Info;

@WebServlet("/Nuevo")
public class Nuevo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// leo la session
			HttpSession session = request.getSession(false);

			// si no hay session redirecciono a la pagina de login
			if (session != null) {

				// leo la info del usuario de la session
				Usuario u = (Usuario) session.getAttribute("user");

				// si el usuario no existe redirecciono al login
				if (u != null) {

					if (u.getApp_nivel() != Info.PERM_ADMIN) {
						throw new Exception("No tenes permiso para crear nuevos cursos.");
					}

					GestorTiendas td = new GestorTiendas();
					request.setAttribute("tiendas", td.TraerTiendas(u.getMarca()));
					request.setAttribute("tiendasGrupo", new GestorGrupoTiendas().TraerGrupos( u.getMarca() ) );

					GestorInstructores id = new GestorInstructores();
					request.setAttribute("instructores", id.TraerInstructoresPorMarca(u.getMarca()));

					GestorCategorias cd = new GestorCategorias();
					request.setAttribute("categorias", cd.TraerCategoriasPorMarca(u.getMarca()));

					request.getRequestDispatcher("cursos/nuevo.jsp").forward(request, response);

				} else {
					response.sendRedirect("login.jsp");
				}

			} else {
				response.sendRedirect("login.jsp");
			}

		} catch (Exception e) {
			request.setAttribute("error", e);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	private Integer[] getId(String[] values) {
		Integer[] array = new Integer[values.length];
		int i = 0;
		for (String var : values) {
			array[i++] = Integer.valueOf(var.substring(2));
		}
		return array;
	}
	
	private List<Integer> getIdEnLista(String[] values) {
		List<Integer> array = new ArrayList<Integer>();
		for (String var : values) {
			array.add(Integer.valueOf(var.substring(2)));
		}
		return array;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// leo la session
			HttpSession session = request.getSession(false);

			// si no hay session redirecciono a la pagina de login
			if (session != null) {

				// leo la info del usuario de la session
				Usuario u = (Usuario) session.getAttribute("user");

				// si el usuario no existe redirecciono al login
				if (u != null) {

					if (u.getApp_nivel() != Info.PERM_ADMIN) {
						throw new Exception("No tienes permiso para crear nuevos cursos.");
					}

					String nombre = request.getParameter("nombre");
					String fecha = request.getParameter("fecha");
					String marca = request.getParameter("marca");
					String descripcion = request.getParameter("descripcion");
										
					
					Integer[] instructores = getId(request.getParameterValues("instructores"));
					Integer[] categorias = getId(request.getParameterValues("categorias"));

					// opcionales
					String correlativa = request.getParameter("correlativa");
					String presencia = request.getParameter("presencia");
					String lugar = request.getParameter("lugar");
					String grupo = request.getParameter("grupo");
					String tolerancia = request.getParameter("tolerancia");
					String grupoaprobacion = request.getParameter("grupoaprobacion");
					String grupopresencia = request.getParameter("grupopresencia");

					Curso c = new Curso();
					c.setNombre(nombre);
					c.setFecha(fecha);
					c.setMarca(marca);
					c.setLugar(lugar);
					c.setDescripcion(descripcion);
					
					if ( request.getParameter("aprobacion") != "" ){
						float aprobacion = Float.valueOf( request.getParameter("aprobacion") );
						c.setAprobacion(aprobacion);
					}
					
					List<Integer> tiendas = new ArrayList<Integer>();
					if(request.getParameterValues("tiendas")!=null){
					 tiendas = getIdEnLista(request.getParameterValues("tiendas"));
					}
					if(request.getParameterValues("tiendasGrupo")!=null){
						List<Integer> tiendasGrupo = getIdEnLista(request.getParameterValues("tiendasGrupo"));
						GestorGrupoTiendas ggt = new GestorGrupoTiendas();
						
						for (int ti : tiendasGrupo){
							tiendas.addAll(ggt.TraerLasTiendasDelGrupo(ti));
							//System.out.println(tiendas);
						}
					}
					
					Set<Integer> hs = new HashSet<>();
					hs.addAll(tiendas);
					tiendas.clear();
					tiendas.addAll(hs);
					
					c.setTiendas( tiendas.toArray(new Integer[tiendas.size()]) );
					
					c.setInstructores(instructores);
					c.setCategorias(categorias);
					
					if (tolerancia != "")
						c.setTolerancia(Integer.valueOf(tolerancia));
					if (grupo != "")
						c.setGrupo(Integer.valueOf(grupo));
					if (grupoaprobacion != "")
						c.setGrupo_correlativa(Integer.valueOf(grupoaprobacion));
					if (grupopresencia != "")
						c.setGrupo_presencia(Integer.valueOf(grupopresencia));
					if (correlativa != "")
						c.setCorrelativa(Integer.valueOf(correlativa));
					if (presencia != "")
						c.setPresencia(Integer.valueOf(presencia));

					GestorCursos cd = new GestorCursos();

					if (cd.NuevoCurso(c)) {
						request.setAttribute("exito", "Curso creado con exito.");
						request.getRequestDispatcher("/exito.jsp").forward(request, response);
					} else {
						throw new Exception("Error al crear curso.");
					}

				} else {
					response.sendRedirect("login.jsp");
				}

			} else {
				response.sendRedirect("login.jsp");
			}

		} catch (Exception e) {
			request.setAttribute("error", e);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
}
