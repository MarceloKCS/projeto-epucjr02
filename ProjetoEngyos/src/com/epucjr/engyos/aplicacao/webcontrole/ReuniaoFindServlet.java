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
import com.epucjr.engyos.dominio.modelo.PaginaDeBusca;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDaReuniao;

/**
 * Servlet implementation class CongregacaoFindServlet
 */
public class ReuniaoFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Reuniao> reuniao; 
	private String dataBusca;
	private BuscaAvancada buscaAvancada;
	private PaginaDeBusca paginacaoDeBusca;
	private FormularioDeBuscaDaReuniao buscarReuniao;
   
    public ReuniaoFindServlet() {
    	this.buscaAvancada = new BuscaAvancada();
    	this.paginacaoDeBusca = new PaginaDeBusca();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view;
		
		this.dataBusca = (String)request.getParameter("dataReuniaoDia") + (String)request.getParameter("dataReuniaoMes") + (String)request.getParameter("dataReuniaoAno");
		this.reuniao = new ArrayList<Reuniao>(this.buscaAvancada.buscarReuniao(this.dataBusca, "", ""));
		this.paginacaoDeBusca.paginarBusca(this.reuniao);
		this.paginacaoDeBusca.setTipoDeBusca("reuniao");
		
		if (paginacaoDeBusca.getPaginaTotal() == 0) {
			this.buscarReuniao = new FormularioDeBuscaDaReuniao(new ArrayList<Reuniao>(0), paginacaoDeBusca.getPaginaTotal());
		} else this.buscarReuniao = new FormularioDeBuscaDaReuniao(this.paginacaoDeBusca.getPaginaListaPaginaBusca().get(0), paginacaoDeBusca.getPaginaTotal());
		
		
		HttpSession session = request.getSession();
		session.setAttribute("resultadoDaBusca", this.paginacaoDeBusca);
		session.setAttribute("buscarReuniao", this.buscarReuniao);
		
		request.setAttribute("resultadoDaBusca", this.buscarReuniao);
		
		view = request.getRequestDispatcher("BuscarReuniao.jsp");
		view.forward(request, response);
	}

}
