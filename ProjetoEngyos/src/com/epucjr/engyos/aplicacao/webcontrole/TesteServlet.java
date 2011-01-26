package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epucjr.engyos.tecnologia.utilitarios.ListUtilTokenizer;

/**
 * Servlet implementation class TesteServlet
 */
public class TesteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TesteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String obreiro = request.getParameter("obreiros");		
		
		List<String> listaSeparada = ListUtilTokenizer.obterListaString(obreiro);
		
		for(String id : listaSeparada){
			System.out.println("idObtido = " + id);
		}
		System.out.println("Valor Devolvido = " + obreiro);
		
		PrintWriter printWriter = new PrintWriter(response.getOutputStream());
		printWriter.println("<html>");
		printWriter.println("<h1>Teste Finalizado</h1>");
		printWriter.println("</html>");
		printWriter.close();
		
	}

}
