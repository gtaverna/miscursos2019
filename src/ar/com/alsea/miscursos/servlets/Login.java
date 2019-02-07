package ar.com.alsea.miscursos.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.alsea.miscursos.dao.GestorUsuarios;
import ar.com.alsea.miscursos.modelo.Usuario;

@WebServlet("/Login")
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// a las peticiones POST devuelvo error
		request.setAttribute("error", "Servlet no permite GET");
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String user = request.getParameter("username");
			String pwd = request.getParameter("userpass");
			
			if(user.trim().isEmpty() || pwd.trim().isEmpty())
				throw new Exception("Por favor, complete los campos obligatoríos.");

			GestorUsuarios ld = new GestorUsuarios();
			Usuario u = ld.validate(user, pwd);

			if (u != null) {

				HttpSession session = request.getSession();
				session.setAttribute("user", u);
				session.setMaxInactiveInterval(30 * 60);
				response.sendRedirect("Cursos");
				//response.sendRedirect(request.getParameter("from"));

			} else {
				request.setAttribute("invalida", "Usuario o contraseña invalida");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", e);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	private static final long serialVersionUID = 1L;

}