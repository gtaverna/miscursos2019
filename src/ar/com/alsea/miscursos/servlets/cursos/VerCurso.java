package ar.com.alsea.miscursos.servlets.cursos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.alsea.miscursos.dao.GestorAsistentes;
import ar.com.alsea.miscursos.dao.GestorCategorias;
import ar.com.alsea.miscursos.dao.GestorCursos;
import ar.com.alsea.miscursos.dao.GestorInstructores;
import ar.com.alsea.miscursos.dao.GestorTiendas;
import ar.com.alsea.miscursos.modelo.Usuario;
import ar.com.alsea.miscursos.util.Info;

/**
 * Servlet implementation class Tomarlista
 */
@WebServlet("/VerCurso")
public class VerCurso extends HttpServlet {
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

					// leo id del curso
					int id_curso = Integer.valueOf(request.getParameter("cursoid"));

					GestorCursos cd = new GestorCursos();
					request.setAttribute("curso", cd.TraerCurso(id_curso));

					GestorTiendas td = new GestorTiendas();
					request.setAttribute("tiendas", td.TraerTiendasPorCurso(u.getMarca(), id_curso));

					GestorInstructores id = new GestorInstructores();
					request.setAttribute("instructores", id.TraerInstructoresPorCurso(id_curso));

					GestorCategorias catd = new GestorCategorias();
					request.setAttribute("categorias", catd.TraerCategoriasPorCurso(id_curso));
					
					switch (u.getApp_nivel()) {
					case Info.PERM_ADMIN:
					case Info.PERM_INSTRUCTOR:
						request.setAttribute("asistentes", new GestorAsistentes().TraerAsistentesAsignados(id_curso));
						break;
					case Info.PERM_TIENDA:
						request.setAttribute("asistentes", new GestorAsistentes().TraerAsistentesAsignadosPorTienda(id_curso, u.getCcpayroll()));
						break;
					}
					
					request.getRequestDispatcher("cursos/ver.jsp").forward(request, response);

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
