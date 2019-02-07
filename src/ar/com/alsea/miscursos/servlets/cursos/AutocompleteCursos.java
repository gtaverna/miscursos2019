package ar.com.alsea.miscursos.servlets.cursos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import ar.com.alsea.miscursos.dao.GestorCursos;
import ar.com.alsea.miscursos.modelo.Autocomplete;

/**
 * Servlet implementation class AutocompleteCorrelativas
 */
@WebServlet("/AutocompleteCursos")
public class AutocompleteCursos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			String term = request.getParameter("term");
			ArrayList<Autocomplete> cursos = new GestorCursos().TraerCursosPorIdyNombre(term);
			JSONArray jsArray = new JSONArray( cursos );
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
