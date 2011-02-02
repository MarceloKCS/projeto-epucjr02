package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epucjr.engyos.dominio.crud.BuscaAvancada;
import com.epucjr.engyos.dominio.crud.ValidadorLogin;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BuscaAvancada buscaAvancada;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        buscaAvancada = new BuscaAvancada();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view;
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		ValidadorLogin vl = new ValidadorLogin();
		
		boolean ok = vl.validarLogin(login, senha);
		HttpSession session = request.getSession();
		
		if (ok) {
			view = request.getRequestDispatcher("Principal.jsp");
		} else {
			view = request.getRequestDispatcher("nao entra");
		}
		
		session.removeAttribute("senha");

		view.forward(request, response);
		
	}

}
