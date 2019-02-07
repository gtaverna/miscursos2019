package ar.com.alsea.miscursos.servlets.cursos;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.alsea.miscursos.dao.GestorAsistentes;
import ar.com.alsea.miscursos.dao.GestorCursos;
import ar.com.alsea.miscursos.modelo.Curso;
import ar.com.alsea.miscursos.modelo.Usuario;
import ar.com.alsea.miscursos.util.Info;

/**
 * Servlet implementation class Tomarlista
 */
@WebServlet("/Tomarlista")
public class Tomarlista extends HttpServlet {
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

					// si no es instructor salta error
					if (u.getApp_nivel() != Info.PERM_INSTRUCTOR) {
						throw new Exception("No tenes permiso para tomar lista del curso.");
					}

					// leo el id del curso
					int id_curso = Integer.valueOf(request.getParameter("cursoid"));

					// con el id del curso busco el curso
					GestorCursos cd = new GestorCursos();
					Curso c = cd.TraerCurso(id_curso);

					/*// valido que el estado sea NUEVO
					if (!c.getEstado().contentEquals(Info.CursoConfirmado) || 
							!c.getEstado().contentEquals(Info.CursoBorrador) ){
						throw new Exception("Ya fue tomado la lista del curso");
					}*/
					
					// envio el curso al dispatcher
					request.setAttribute("curso", c);

					// busco los asistentes y envio al dispatcher
					GestorAsistentes ad = new GestorAsistentes();
					request.setAttribute("asistentes", ad.TraerAsistentesAsignados(c.getId()));

					// redirecciono a la pagina de calificar
					request.getRequestDispatcher("cursos/tomarlista.jsp").forward(request, response);

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

					// si no es instructor salta error
					if (u.getApp_nivel() != Info.PERM_INSTRUCTOR) {
						throw new Exception("No tenes permiso para tomar lista del curso.");
					}

					// leo el id del curso
					int idcurso = Integer.valueOf(request.getParameter("cursoid"));

					GestorAsistentes ga = new GestorAsistentes();
					
					// leo todos los parametros del request
					Enumeration<?> paramNames = request.getParameterNames();
					// mientras exista elements
					while (paramNames.hasMoreElements()) {
						// leo el nombre del atributo
						String paramName = (String) paramNames.nextElement();
						// si el atributo empieza con asistencia, busco el id del empleado
						if (paramName.contains("asistencia_")) {
							// leo el id del empleado
							int id_asistente = Integer.parseInt(paramName.replaceAll("[\\D]", ""));
							// busco el valor del atributo
							String paramValue = request.getParameter(paramName);
							// si no es vacio, grabo segun opcion seleccionada
							if (paramValue.length() > 0) {
								// se graba el estado segun empleado
								if (!ga.GuardarAsistenciaDelEmpleado(id_asistente,paramValue)){
									throw new Exception("Error al grabar asistencia del empleado.");
								}
							}
						}
					}
					
					GestorCursos gc = new GestorCursos();
					
					// leo la opcion de guardar o borrador
					String estadoGuardar = request.getParameter("estadoGuardar");
					if (estadoGuardar.contentEquals("BORRADOR")) {
						estadoGuardar = Info.CursoBorrador;
					} else {
						// si la opcion es guardar, valido si hay aprobacion en el curso.
						Curso curso = gc.TraerCurso(idcurso);
						if (curso.getAprobacion() > 0){
							estadoGuardar = Info.CursoPendienteCalificacion;
						} else {
							estadoGuardar = Info.CursoFinalizado;
							// graba estado del curso segun tienda
							for (int tienda : curso.getTiendas()){
								if (!gc.ModificarEstadoCursoTienda(idcurso, tienda, estadoGuardar)){
									throw new Exception("Error al grabar estado del curso.");
								}
							}
						}
					}
					
					// graba estado del curso
					if (!gc.ModificarEstadoCurso(idcurso, estadoGuardar)){
						throw new Exception("Error al grabar estado del curso.");
					}
					
					// envio mensaje de exito al dispatcher
					request.setAttribute("exito", "Asistencia guardada con exito.");
					request.getRequestDispatcher("/exito.jsp").forward(request, response);

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
