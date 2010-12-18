package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeCongregacao;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeCongregacao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;


public class CongregacaoRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Congregacao congregacao;
	private ValidadorDeFormularioDeCongregacao validator;
	private FormularioDeCongregacao formularioDeCongregacao;
	
    public CongregacaoRegisterServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.validator = new ValidadorDeFormularioDeCongregacao();
		
		
		String nome = (String)request.getParameter("Nome");
		String endereco = (String)request.getParameter("Endereco");
		this.congregacao = new Congregacao(nome, endereco);
		//this.controleDeCongregacao.cadastrarCongregacao(request, this.congregacao);
		
		this.validator.verificarCamposValidos(this.congregacao);
		this.formularioDeCongregacao = new FormularioDeCongregacao(this.congregacao);
		
		if(!this.validator.isFormularioValido()){
			this.formularioDeCongregacao.setStatus("Erro ao Cadastrar");
			request.setAttribute("viewCongregacao", this.formularioDeCongregacao);
			request.setAttribute("errorCongregacao", this.validator);
			RequestDispatcher view = request.getRequestDispatcher("CadastrarCongregacao.jsp");
			view.forward(request, response);
		}
		else{
			DataAccessObjectManager hibernate = new DataAccessObjectManager();
			hibernate.persistirObjeto(this.congregacao);
			this.formularioDeCongregacao = new FormularioDeCongregacao(null);
			this.formularioDeCongregacao.setStatus("Sucesso ao Cadastrar");
			request.setAttribute("viewCongregacao", this.formularioDeCongregacao);
			request.setAttribute("congregacao", this.congregacao);
			//RequestDispatcher view = request.getRequestDispatcher("home.jsp");
			RequestDispatcher view = request.getRequestDispatcher("CadastrarCongregacao.jsp");
			view.forward(request, response);
		}
	}

}
