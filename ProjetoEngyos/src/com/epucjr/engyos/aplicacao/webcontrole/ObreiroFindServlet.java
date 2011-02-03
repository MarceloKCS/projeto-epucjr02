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
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.PaginaDeBusca;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDoObreiro;

/**
 * Servlet implementation class CongregacaoFindServlet
 */
public class ObreiroFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Obreiro> obreiro; 
	private String nomeBusca;
	private String cargoBusca;
	private String cpfBusca;
	private String congregacaoBusca;
	private BuscaAvancada buscaAvancada;
	private PaginaDeBusca paginacaoDeBusca;
	private FormularioDeBuscaDoObreiro buscarObreiro;
   
    public ObreiroFindServlet() {
    	this.buscaAvancada = new BuscaAvancada();
    	this.paginacaoDeBusca = new PaginaDeBusca();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view;
		
		this.nomeBusca = (String)request.getParameter("Nome");
		//this.cargoBusca = (String)request.getParameter("Cargo");
		//this.congregacaoBusca = (String)request.getParameter("Congregacao");
		//this.cpfBusca = (String)request.getParameter("Cpf");
		
		//TODO: Refazer os indices de busca para atualizar a lista de obreiro com as novas edições
		this.obreiro = new ArrayList<Obreiro>(this.buscaAvancada.buscarObreiros(this.nomeBusca, "", /*this.cpfBusca*/"", ""));
		this.paginacaoDeBusca.paginarBusca(this.obreiro);
		this.paginacaoDeBusca.setTipoDeBusca("obreiro");
		
		if (paginacaoDeBusca.getPaginaTotal() == 0) {
			this.buscarObreiro = new FormularioDeBuscaDoObreiro(new ArrayList<Obreiro>(0), paginacaoDeBusca.getPaginaTotal());
		} else this.buscarObreiro = new FormularioDeBuscaDoObreiro(this.paginacaoDeBusca.getPaginaListaPaginaBusca().get(0), paginacaoDeBusca.getPaginaTotal());
		
		HttpSession session = request.getSession();
		session.setAttribute("resultadoDaBusca", this.paginacaoDeBusca);
		session.setAttribute("buscarObreiro", this.buscarObreiro);
		
		request.setAttribute("resultadoDaBusca", this.buscarObreiro);
		
		view = request.getRequestDispatcher("BuscarObreiro.jsp");
		view.forward(request, response);
	}

}
