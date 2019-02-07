package ar.com.alsea.miscursos.servlets.cursos;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.alsea.miscursos.dao.GestorCategorias;
import ar.com.alsea.miscursos.dao.GestorCursos;
import ar.com.alsea.miscursos.dao.GestorInstructores;
import ar.com.alsea.miscursos.dao.GestorTiendas;
import ar.com.alsea.miscursos.modelo.Curso;
import ar.com.alsea.miscursos.modelo.Usuario;
import ar.com.alsea.miscursos.util.Info;

@WebServlet("/Editar")
public class Editar extends HttpServlet {
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
						throw new Exception("No tenes permiso para editar cursos.");
					}

					int id_curso = Integer.valueOf(request.getParameter("cursoid"));

					Curso cu = new GestorCursos().TraerCurso(id_curso);
					/*// valido que el estado sea NUEVO
					if (cu.getEstado().contentEquals(Info.CursoCancelado) || 
							cu.getEstado().contentEquals(Info.CursoFinalizado) ){
						throw new Exception("Curso ya finalizado ");
					}*/
					
					request.setAttribute("curso", cu);

					GestorTiendas td = new GestorTiendas();
					request.setAttribute("tiendas", td.TraerTiendasPorCurso(u.getMarca(), id_curso));

					//GestorInstructores id = new GestorInstructores();
					//request.setAttribute("instructores", id.TraerInstructoresPorCurso(id_curso));
					
	// agrego ib
					
					GestorInstructores gi = new GestorInstructores();
					ArrayList<String[]> fd = gi.TraerInstructoresPorMarcaycurso(u.getMarca(),id_curso);
					request.setAttribute("instructores", fd);

					
	//
					GestorCategorias catd = new GestorCategorias();
					request.setAttribute("categorias", catd.TraerCategoriasPorCurso(id_curso));

					request.getRequestDispatcher("cursos/editar.jsp").forward(request, response);

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
					

			
					
					
					Integer[] instructores = getId(request.getParameterValues("instructores"));
					int id = Integer.valueOf(request.getParameter("id"));
					String nombre = request.getParameter("nombre");
					String fecha = request.getParameter("fecha");
					String descripcion = request.getParameter("descripcion");
					float aprobacion = Float.valueOf(request.getParameter("aprobacion"));
					// opcionales
					String correlativa = request.getParameter("correlativa");
					String presencia = request.getParameter("presencia");
					String lugar = request.getParameter("lugar");
					String grupo = request.getParameter("grupo");
					String grupoaprobacion = request.getParameter("grupoaprobacion");
					String grupopresencia = request.getParameter("grupopresencia");
					String tolerancia = request.getParameter("tolerancia");
					

					Curso c = new Curso();
					c.setId(id);
					c.setNombre(nombre);
					c.setFecha(fecha);
					c.setLugar(lugar);
					c.setAprobacion(aprobacion);
					c.setDescripcion(descripcion);
					c.setTolerancia(Integer.valueOf(tolerancia));
					
					c.setInstructores(instructores);
					
					if (grupo != "")
						c.setGrupo(Integer.valueOf(grupo));
					if (grupoaprobacion != "")
						c.setGrupo_correlativa(Integer.valueOf(grupoaprobacion));
					if (grupopresencia != "")
						c.setGrupo_presencia(Integer.valueOf(grupopresencia));
					if (correlativa != "")
						c.setCorrelativa(Integer.valueOf(correlativa));
					if (correlativa != "")
						c.setPresencia(Integer.valueOf(presencia));
					

					GestorCursos cd = new GestorCursos();

					if (cd.EditarCurso(id, c)) {
						//new GestorMails().CursoModificado(c);
						request.setAttribute("exito", "Curso editado con exito.");
						request.getRequestDispatcher("/exito.jsp").forward(request, response);
					} else {
						request.setAttribute("error", "Error al editar el curso.");
						request.getRequestDispatcher("/error.jsp").forward(request, response);
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
