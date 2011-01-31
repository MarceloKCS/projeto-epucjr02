package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epucjr.engyos.dominio.crud.BuscaAvancada;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.PaginaDeBusca;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDaCongregacao;

/**
 * Servlet implementation class CongregacaoFindServlet
 */
public class CongregacaoFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Congregacao> congregacao; 
	private String nomeBusca;
	private BuscaAvancada buscaAvancada;
	private PaginaDeBusca paginacaoDeBusca;
	private FormularioDeBuscaDaCongregacao buscarCongregacao;
   
    public CongregacaoFindServlet() {
    	this.buscaAvancada = new BuscaAvancada();
    	this.paginacaoDeBusca = new PaginaDeBusca();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view;
		
		this.nomeBusca = (String)request.getParameter("Nome");
		this.congregacao = new ArrayList<Congregacao>(this.buscaAvancada.buscarCongregacao(this.nomeBusca, ""));
		this.paginacaoDeBusca.paginarBusca(this.congregacao);
		this.paginacaoDeBusca.setTipoDeBusca("congregacao");
		
		if (paginacaoDeBusca.getPaginaTotal() == 0) {
			this.buscarCongregacao = new FormularioDeBuscaDaCongregacao(new ArrayList<Congregacao>(0), paginacaoDeBusca.getPaginaTotal());
		} else this.buscarCongregacao = new FormularioDeBuscaDaCongregacao(this.paginacaoDeBusca.getPaginaListaPaginaBusca().get(0), paginacaoDeBusca.getPaginaTotal());
		
		HttpSession session = request.getSession();
		session.setAttribute("resultadoDaBusca", this.paginacaoDeBusca);
		session.setAttribute("buscarCongregacao", this.buscarCongregacao);
		
		request.setAttribute("resultadoDaBusca", this.buscarCongregacao);
		
		view = request.getRequestDispatcher("BuscarCongregacao.jsp");
		view.forward(request, response);
	}
}
