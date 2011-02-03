package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeCongregacao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

/**
 * Servlet implementation class CongregacaoCarregaServlet
 */
public class CongregacaoCarregaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CongregacaoCarregaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		
		DataAccessObjectManager daom = new DataAccessObjectManager();
		Congregacao c = daom.obterCongregacao(id);
		
		String mensagem = daom.getMensagemStatus();
		boolean ok = daom.isOperacaoEfetuada();
		
		if (ok) {
			request.setAttribute("Nome", c.getNome());
			request.setAttribute("Endereco", c.getEndereco());
			request.setAttribute("IdCongregacao", c.getIdCongregacao());
		} else {
			request.setAttribute("Nome", "");
			request.setAttribute("Endereco", "");
			request.setAttribute("IdCongregacao", -1);
		}

		request.setAttribute("Mensagem", mensagem);
		
		RequestDispatcher view = request.getRequestDispatcher("EditarCongregacao.jsp");
		view.forward(request, response);
	}

}
