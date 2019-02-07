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
 * Servlet implementation class EditarGrupoTienda
 */
@WebServlet("/EditarGrupoTienda")
public class EditarGrupoTienda extends HttpServlet {
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
						throw new Exception("No tenes permiso para crear editar grupos.");
					}
					
					int id = Integer.valueOf(request.getParameter("id"));
					request.setAttribute("grupo", new GestorGrupoTiendas().TraerGrupotienda(id) );
					request.setAttribute("tiendas", new GestorGrupoTiendas().TraerTiendasDeGrupo( u.getMarca(), id ) );
					request.getRequestDispatcher("gruposTiendas/editar.jsp").forward(request, response);

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

					int id = Integer.valueOf( request.getParameter("id") );
					String grupo = request.getParameter("grupo");
					String[] tiendas = request.getParameterValues("tiendas");
					String marca = request.getParameter("marca");
					
					if(tiendas == null){ //Si no tiene tiendas asociadas elimino
						
						if (new GestorGrupoTiendas().EliminarGrupo(id) ) {
							
							request.setAttribute("exito", "Grupo eliminado con exito.");
							request.getRequestDispatcher("/exito.jsp").forward(request, response);
						} else {
							throw new Exception("Error al eliminar grupo.");
						}
						
						
						
					} else{
						ArrayList<Tienda> at = new ArrayList<Tienda>();
						for (String tienda : tiendas){
							Tienda t = new Tienda(Integer.valueOf(tienda),"");
							at.add(t);
						}
						GrupoTiendas gt = new GrupoTiendas(
								id, 
								grupo, 
								at, 
								marca);
						
						if (new GestorGrupoTiendas().EditarGrupoTiendas(gt) ) {
							
							request.setAttribute("exito", "Grupo modificado con exito.");
							request.getRequestDispatcher("/exito.jsp").forward(request, response);
						} else {
							throw new Exception("Error al crear grupo.");
						}
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
