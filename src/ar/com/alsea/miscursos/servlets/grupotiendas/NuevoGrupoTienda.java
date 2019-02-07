package ar.com.alsea.miscursos.servlets.grupotiendas;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.alsea.miscursos.dao.GestorGrupoTiendas;
import ar.com.alsea.miscursos.modelo.GrupoTiendas;
import ar.com.alsea.miscursos.modelo.Tienda;
import ar.com.alsea.miscursos.modelo.Usuario;
import ar.com.alsea.miscursos.util.Info;

/**
 * Servlet implementation class NuevoGrupoTienda
 */
@WebServlet("/NuevoGrupoTienda")
public class NuevoGrupoTienda extends HttpServlet {
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
						throw new Exception("No tenes permiso para crear/modificar grupos de tiendas.");
					}
					//request.setAttribute("grupos", new GestorGruposTiendas().TraerGrupos() );
					request.setAttribute("marca", u.getMarca() );
					request.setAttribute("tiendas", new GestorGrupoTiendas().TraerTiendas( u.getMarca() ) );
					request.getRequestDispatcher("gruposTiendas/nuevo.jsp").forward(request, response);

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
						throw new Exception("No tienes permiso para crear nuevos grupos.");
					}

					String grupo = request.getParameter("grupo");
					String[] tiendas = request.getParameterValues("tiendas");
					String marca = request.getParameter("marca");
					
					ArrayList<Tienda> at = new ArrayList<Tienda>();
					for (String tienda : tiendas){
						Tienda t = new Tienda(Integer.valueOf(tienda),"");
						at.add(t);
					}
					GrupoTiendas gt = new GrupoTiendas(
							null, 
							grupo, 
							at, 
							marca);
					
					if (new GestorGrupoTiendas().NuevoGrupoTiendas(gt) ) {
						request.setAttribute("exito", "Grupo creado con exito.");
						request.getRequestDispatcher("/exito.jsp").forward(request, response);
					} else {
						throw new Exception("Error al crear grupo.");
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
