package ar.com.alsea.miscursos.servlets.cursos;

import java.io.IOException;

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

@WebServlet("/Aceptar")
public class Aceptar extends HttpServlet {
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

					// validar que sea usuario de tienda
					if (u.getApp_nivel() != Info.PERM_TIENDA) {
						//throw new Exception("No tenes permiso para aceptar el curso.");
					}

					// leo id del curso
					int id_curso = Integer.valueOf(request.getParameter("cursoid"));

					// con el id del curso traigo el curso segun la tienda
					GestorCursos cd = new GestorCursos();
					Curso c = cd.TraerCursoPorTienda(id_curso, u.getId());
					
					/*// valido que el estado sea NUEVO
					if (!c.getEstado().contentEquals(Info.CursoTiendaNuevo) || 
							!c.getEstado().contentEquals(Info.CursoTiendaBorrador)){
						throw new Exception("Curso ya aceptado");
					}*/

					// envio el curso al dispatcher
					request.setAttribute("curso", c);

					// busco los posibles asistentes segun tienda, categoria, presencia y correlativa
					GestorAsistentes ad = new GestorAsistentes();
					request.setAttribute("asistentes", ad.TraerEmpleadosPorTiendaCategoria(c, u.getCcpayroll()));

					// redirecciono a la pagina para aceptar
					request.getRequestDispatcher("cursos/aceptar.jsp").forward(request, response);

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
					
					// validar que sea tienda
					if (u.getApp_nivel() != Info.PERM_TIENDA) {
						throw new Exception("No tenes permiso para aceptar el curso.");
					}

					// leo el id del curso
					int idcurso = Integer.valueOf(request.getParameter("cursoid"));
					
					// leo todos los legajos seleccionados
					String[] legajos = request.getParameterValues("asistentes");
					
					// leo la opcion de guardar o borrador
					String estadoGuardar = request.getParameter("estadoGuardar");
					if (!estadoGuardar.contentEquals( Info.CursoTiendaBorrador )) {
						estadoGuardar = Info.CursoTiendaConfirmado;
					} 

					// borro todos los asistentes
					GestorAsistentes ad = new GestorAsistentes();
					ad.BorrarAsistentes(idcurso, u.getCcpayroll());
					
					// si hay legajos seleccionados, se graba como asistente
					if (legajos != null) {
						ad.GuardarAsistentesPorTienda(u.getMarca(), idcurso, legajos);
					}

					// graba estado del curso segun tienda 
					GestorCursos cd = new GestorCursos();
					if (!cd.ModificarEstadoCursoTienda(idcurso, u.getId(), estadoGuardar)){
						throw new Exception("Error al modificar estado del curso.");
					}
					
					// valida si todas las tienda ya aceptaron los asistentes
					// si es asi, se modifica estado general del curso 
					if (cd.VerificarEstadoCurso(idcurso)){
						// TODO enviar aviso por mail a instructor
					}

					// envio mensaje de exito al dispatcher
					request.setAttribute("exito", "Curso guardado con exito.");
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
