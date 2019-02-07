package ar.com.alsea.miscursos.servlets.grupos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import ar.com.alsea.miscursos.dao.GestorGrupos;
import ar.com.alsea.miscursos.modelo.Autocomplete;
import ar.com.alsea.miscursos.modelo.Usuario;

/**
 * Servlet implementation class AutocompleteCorrelativas
 */
@WebServlet("/AutocompleteGrupos")
public class AutocompleteGrupos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			Usuario u = (Usuario) session.getAttribute("user");
			
			String term = request.getParameter("term");
			String marca = u.getMarca();
			ArrayList<Autocomplete> grupos = new GestorGrupos().TraerGruposPorIdyNombre(term, marca);
			JSONArray jsArray = new JSONArray( grupos );
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();  
			out.print(jsArray.toString());
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
