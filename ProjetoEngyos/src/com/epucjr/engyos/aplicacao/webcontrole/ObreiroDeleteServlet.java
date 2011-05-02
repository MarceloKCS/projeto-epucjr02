package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;


public class ObreiroDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ObreiroDeleteServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view;
		String confirmacao;
		confirmacao = (String) request.getAttribute("conf");
		
		if(confirmacao.equals("yes")){	
			Obreiro obreiroDelete;
			DataAccessObjectManager hibernate = new DataAccessObjectManager();
			obreiroDelete = hibernate.obterObreiro((String)request.getAttribute("id"));
			hibernate.deletarObjeto(obreiroDelete);
		}
		
		HttpSession session = request.getSession();
		
		request.setAttribute("resultadoDaBusca", session.getAttribute("buscarObreiro"));
		
		view = request.getRequestDispatcher("BuscarObreiro.jsp");
		view.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
