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
 * Servlet implementation class Tomarlista
 */
@WebServlet("/Recordatorio")
public class Recordatorio extends HttpServlet {
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

					if (u.getApp_nivel() != Info.PERM_INSTRUCTOR) {
						throw new Exception("No tienes permiso para enviar notificaciones.");
					}

					int id_curso = Integer.valueOf(request.getParameter("cursoid"));
					Curso c = new GestorCursos().TraerCurso(id_curso);

					new GestorMails().Recordatorio(c);
					response.setStatus(200);
				} else {
					response.sendRedirect("login.jsp");
				}

			} else {
				response.sendRedirect("login.jsp");
			}

		} catch (Exception e) {
			
			response.setStatus(500);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
