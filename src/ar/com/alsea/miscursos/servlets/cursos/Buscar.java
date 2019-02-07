package ar.com.alsea.miscursos.servlets.cursos;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.alsea.miscursos.dao.GestorCursos;
import ar.com.alsea.miscursos.modelo.Curso;
import ar.com.alsea.miscursos.modelo.Usuario;

/**
 * Servlet implementation class Buscar
 */
@WebServlet("/Buscar")
public class Buscar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// leo la session
			HttpSession session = request.getSession(false);

			// si no hay session redirecciono a la pagina de login
			if (session != null) {

				// leo la info del usuario de la session
				Usuario u = (Usuario) session.getAttribute("user");

				// si el usuario no existe redirecciono al login
				if (u != null) {

					String busqueda = "";
					if ( request.getParameter("busqueda") != null ){
						busqueda = request.getParameter("busqueda");
					}
										
					ArrayList<Curso> cl = new GestorCursos().TraerCursosPorBusqueda(busqueda,u);

					// envio permiso y listado de cursos al dispatcher
					request.setAttribute("cursos", cl);
					
					// redirecciono a la pagina de cursos
					request.getRequestDispatcher("cursos/historial.jsp").forward(request, response);

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
