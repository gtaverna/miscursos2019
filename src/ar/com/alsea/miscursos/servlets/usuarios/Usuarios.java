package ar.com.alsea.miscursos.servlets.usuarios;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.alsea.miscursos.dao.GestorUsuarios;
import ar.com.alsea.miscursos.modelo.Usuario;
import ar.com.alsea.miscursos.util.Info;


@WebServlet("/Usuarios")
public class Usuarios extends HttpServlet {
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

					if (u.getApp_nivel() != Info.PERM_ADMIN) {
						throw new Exception("No tenes permiso para crear nuevos usuarios.");
					}
					request.setAttribute("usuarios", new GestorUsuarios().TraerUsuarios());
					request.getRequestDispatcher("usuarios/usuarios.jsp").forward(request, response);

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
