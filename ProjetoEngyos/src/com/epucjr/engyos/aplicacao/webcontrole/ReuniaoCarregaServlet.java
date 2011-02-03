package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeReuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

/**
 * Servlet implementation class ReuniaoCarregaServlet
 */
public class ReuniaoCarregaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReuniaoCarregaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long idDaReuniao = Long.parseLong(request.getParameter("idReuniao"));
		
		DataAccessObjectManager daom = new DataAccessObjectManager();
		Reuniao r = daom.obterReuniao(idDaReuniao);
		
		FormularioDeReuniao f = new FormularioDeReuniao(r);
		request.setAttribute("viewReuniao", f);
		
		RequestDispatcher view = request.getRequestDispatcher("EditarReuniao.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
