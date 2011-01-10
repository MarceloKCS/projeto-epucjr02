package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epucjr.engyos.dominio.crud.BuscaAvancada;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeObreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

/**
 * Servlet implementation class ObreiroCarregaServlet
 */
public class ObreiroCarregaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObreiroCarregaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpf = request.getParameter("cpf");
				
		DataAccessObjectManager daom = new DataAccessObjectManager();
		Obreiro o = daom.obterObreiro(cpf);
		
		FormularioDeObreiro f = new FormularioDeObreiro(o);
		request.setAttribute("viewObreiro", f);
		
		BuscaAvancada ba = new BuscaAvancada();
		List<Congregacao> listc = ba.buscarCongregacao("", "");
		request.setAttribute("listaCongregacao", listc);
		
		RequestDispatcher view = request.getRequestDispatcher("EditarObreiro.jsp");
		view.forward(request, response);
	}

}
