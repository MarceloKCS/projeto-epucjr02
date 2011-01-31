package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epucjr.engyos.dominio.crud.BuscaAvancada;
import com.epucjr.engyos.dominio.modelo.Congregacao;

/**
 * Servlet implementation class ObreiroEditarCongregacaoFindAllServlet
 */
public class ObreiroFindCongregacaoFindAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BuscaAvancada buscaAvancada;
	private List<Congregacao> congregacao;
       

    public ObreiroFindCongregacaoFindAllServlet() {
    	this.buscaAvancada = new BuscaAvancada();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view;
		HttpSession session = request.getSession();
		
		this.congregacao = this.buscaAvancada.buscarTodasCongregacoes();
		session.setAttribute("listaDeCongregacao", this.congregacao);
		request.setAttribute("listaDeCongregacao", this.congregacao);
		view = request.getRequestDispatcher("BuscarObreiro.jsp");
		view.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
