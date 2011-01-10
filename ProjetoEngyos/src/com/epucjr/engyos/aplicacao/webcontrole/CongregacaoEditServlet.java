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


public class CongregacaoEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Congregacao congregacao;
	private ValidadorDeFormularioDeCongregacao validator;
	private FormularioDeCongregacao formularioDeCongregacao;
       
    public CongregacaoEditServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.validator = new ValidadorDeFormularioDeCongregacao();
		
		
		String nome = (String)request.getParameter("Nome");
		String endereco = (String)request.getParameter("Endereco");
		long idCongregacao = Long.parseLong(request.getParameter("idCongregacao"));
		DataAccessObjectManager daom = new DataAccessObjectManager();
		this.congregacao = daom.obterCongregacao(idCongregacao);
		this.congregacao.setNome(nome);
		this.congregacao.setEndereco(endereco);
		//this.controleDeCongregacao.editarCongregacao(request, this.congregacao);
		
		this.validator.verificarCamposValidos(this.congregacao);
		this.formularioDeCongregacao = new FormularioDeCongregacao(this.congregacao);
		request.setAttribute("viewCongregacao", this.formularioDeCongregacao);
		request.setAttribute("Congregacao", congregacao);
		
		if(!this.validator.isFormularioValido()){
			this.formularioDeCongregacao.setStatus("Erro ao Editar");
			request.setAttribute("errorCongregacao", this.validator);
			RequestDispatcher view = request.getRequestDispatcher("EditarCongregacao.jsp");
			view.forward(request, response);
		}
		else{
			this.formularioDeCongregacao.setStatus("Sucesso ao Editar");
			DataAccessObjectManager hibernate = new DataAccessObjectManager();
			hibernate.mergeDataObjeto(this.congregacao);
			RequestDispatcher view = request.getRequestDispatcher("EditarCongregacao.jsp");
			view.forward(request, response);
		}
	}

}
