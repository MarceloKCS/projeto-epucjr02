package com.epucjr.engyos.aplicacao.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.factory.CommandFactory;
import com.epucjr.engyos.dominio.factory.ViewFactory;

/**
 * Servlet implementation class Servlet
 */
public class FrontControlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	private CommandFactory fabricaComandos;		
	private ViewFactory viewFactory;
	
    public FrontControlerServlet() {
        super();
        
        fabricaComandos = new CommandFactory();
        viewFactory = new ViewFactory();       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servico(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servico(request, response);
	}
	
	public void servico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().print("TESTE");
		String acao = request.getParameter("acao");
		
		Command comando = fabricaComandos.getCommand(acao);
		String resposta = (String) comando.execute(request, response);		
		
		String viewJsp = viewFactory.getView(acao, resposta);
		
		RequestDispatcher view = request.getRequestDispatcher(viewJsp);
		view.forward(request, response);
	}

}

