package ar.com.alsea.miscursos.servlets.cursos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.alsea.miscursos.dao.GestorCursos;
import ar.com.alsea.miscursos.dao.GestorMails;
import ar.com.alsea.miscursos.modelo.Curso;
import ar.com.alsea.miscursos.modelo.Usuario;
import ar.com.alsea.miscursos.util.Info;

/**
 * Servlet implementation class Cancelar
 */
@WebServlet("/Cancelar")
public class Cancelar extends HttpServlet {
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

					// validar que sea usuario admin
					if (u.getApp_nivel() != Info.PERM_ADMIN) {
						throw new Exception("No tenes permiso para cancelar el curso.");
					}

					// leo id del curso
					int idcurso = Integer.valueOf(request.getParameter("cursoid"));
					
					// con el id del curso traigo el curso segun la tienda
					GestorCursos gc = new GestorCursos();
					Curso curso = gc.TraerCurso(idcurso);
					
					// valido que el estado sea PENDIENTE_CALIFICACION
					if (curso.getEstado().contentEquals(Info.CursoCancelado) ){
						throw new Exception("Curso ya cancelado");
					}
					
					// graba estado del curso
					if (!gc.ModificarEstadoCurso(idcurso, Info.CursoCancelado)){
						throw new Exception("Error al cancelar curso.");
					}
					
					gc.InsertarMotivoCancelacion(idcurso,request.getParameter("motivo"));
					
					
					for (int tienda : curso.getTiendas()){
						if (!gc.ModificarEstadoCursoTienda(idcurso, tienda, Info.CursoCancelado)){
							throw new Exception("Error al cancelar curso.");
						}
					}

					// envio mensaje de exito al dispatcher
					
					new GestorMails().CursoCancelado(curso);
					request.setAttribute("exito", "Curso cancelado con exito.");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
