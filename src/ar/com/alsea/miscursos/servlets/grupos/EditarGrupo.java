package ar.com.alsea.miscursos.servlets.grupos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.alsea.miscursos.dao.GestorGrupos;
import ar.com.alsea.miscursos.modelo.GruposCurso;
import ar.com.alsea.miscursos.modelo.Usuario;
import ar.com.alsea.miscursos.util.Info;

/**
 * Servlet implementation class NuevoGrupo
 */
@WebServlet("/EditarGrupo")
public class EditarGrupo extends HttpServlet {
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
						throw new Exception("No tenes permiso para crear nuevos grupos.");
					}
					
					int grupoid = Integer.valueOf(request.getParameter("grupoid"));
					request.setAttribute("grupo", new GestorGrupos().TraerGrupo(grupoid));
					request.getRequestDispatcher("grupos/editar.jsp").forward(request, response);

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
						throw new Exception("No tenes permiso para crear nuevos grupos.");
					}
					
					int id = Integer.valueOf(request.getParameter("id"));
					boolean activo =true;
					if(request.getParameter("activo").equals("Inactivo")){
						activo=false;
					}
					String nombre = request.getParameter("nombre");
					
					GruposCurso gc = new GruposCurso();
					gc.setId(id);
					gc.setNombre(nombre);
					gc.setActivo(activo);
					
					if ( new GestorGrupos().EditarGrupo(gc) ) {
						request.setAttribute("exito", "Grupo editado con exito.");
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
