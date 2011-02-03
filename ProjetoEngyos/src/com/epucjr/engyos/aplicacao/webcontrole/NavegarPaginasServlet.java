package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.PaginaDeBusca;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDaCongregacao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDaReuniao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDoObreiro;

/**
 * Servlet implementation class NavegarPaginasServlet
 */
public class NavegarPaginasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaginaDeBusca paginacaoDeBusca;
	private FormularioDeBuscaDaCongregacao buscarCongregacao;
	private FormularioDeBuscaDoObreiro buscarObreiro;
	private FormularioDeBuscaDaReuniao buscarReuniao;
       
    public NavegarPaginasServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//pega qual é a página que deseja carregar
		HttpSession session = request.getSession();
		RequestDispatcher view;
		
		int pagina = Integer.parseInt(request.getParameter("numeroPagina"));
		this.paginacaoDeBusca = (PaginaDeBusca) session.getAttribute("resultadoDaBusca");
		//verifica se é busca da congregacao
		if(this.paginacaoDeBusca.getTipoDeBusca().equals("congregacao")){
			if (paginacaoDeBusca.getPaginaTotal() == 0) this.buscarCongregacao = new FormularioDeBuscaDaCongregacao(new ArrayList<Congregacao>(0), paginacaoDeBusca.getPaginaTotal());
			else this.buscarCongregacao = new FormularioDeBuscaDaCongregacao(paginacaoDeBusca.getPaginaListaPaginaBusca().get(pagina-1), paginacaoDeBusca.getPaginaTotal());
			
			request.setAttribute("resultadoDaBusca", this.buscarCongregacao);
			view = request.getRequestDispatcher("BuscarCongregacao.jsp");
			view.forward(request, response);				
		}
		else{
			//verifica se é busca do obreiro
			if(this.paginacaoDeBusca.getTipoDeBusca().equals("obreiro")){
				if (paginacaoDeBusca.getPaginaTotal() == 0) this.buscarObreiro = new FormularioDeBuscaDoObreiro(new ArrayList<Obreiro>(0), paginacaoDeBusca.getPaginaTotal());
				else this.buscarObreiro = new FormularioDeBuscaDoObreiro(paginacaoDeBusca.getPaginaListaPaginaBusca().get(pagina-1), paginacaoDeBusca.getPaginaTotal());
				
				request.setAttribute("resultadoDaBusca", this.buscarObreiro);
				view = request.getRequestDispatcher("BuscarObreiro.jsp");
				view.forward(request, response);			
			}
			//verifica se é busca da reuniao
			else{
				if (paginacaoDeBusca.getPaginaTotal() == 0) this.buscarReuniao = new FormularioDeBuscaDaReuniao(new ArrayList<Reuniao>(0), paginacaoDeBusca.getPaginaTotal());
				else this.buscarReuniao = new FormularioDeBuscaDaReuniao(paginacaoDeBusca.getPaginaListaPaginaBusca().get(pagina-1), paginacaoDeBusca.getPaginaTotal());
				
				request.setAttribute("resultadoDaBusca", this.buscarReuniao);
				view = request.getRequestDispatcher("BuscarReuniao.jsp");
				view.forward(request, response);	
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
