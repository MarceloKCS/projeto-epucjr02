package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;


public class ReuniaoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReuniaoDeleteServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view;
		String confirmacao;
		confirmacao = (String) request.getAttribute("conf");
		
		if(confirmacao.equals("yes")){	
			Reuniao reuniaoDelete;
			DataAccessObjectManager hibernate = new DataAccessObjectManager();
			reuniaoDelete = hibernate.obterReuniao(Integer.parseInt((String)request.getAttribute("id")));
			hibernate.deletarObjeto(reuniaoDelete);
		}
		
		HttpSession session = request.getSession();
		
		request.setAttribute("resultadoDaBusca", session.getAttribute("buscarReuniao"));
		
		view = request.getRequestDispatcher("BuscarReuniao.jsp");
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
