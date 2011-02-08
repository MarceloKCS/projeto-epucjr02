package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epucjr.engyos.dominio.crud.BuscaAvancada;
import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeObreiro;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeObreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;


public class ObreiroEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Obreiro obreiro;
	private ValidadorDeFormularioDeObreiro validator;
	private FormularioDeObreiro formularioDeObreiro;
 
    public ObreiroEditServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		this.validator = new ValidadorDeFormularioDeObreiro();
//
//		String nome = (String)request.getParameter("Nome");
//		String cpf = (String)request.getParameter("Cpf");
//		String cargo = (String)request.getParameter("Cargo");
//		String congregacao = (String)request.getParameter("Congregacao");
////		String senha = (String)request.getParameter("Senha");
////		String senhaConfirmacao = (String)request.getParameter("senhaConfirmacao");
//		DataAccessObjectManager daom = new DataAccessObjectManager();
//		this.obreiro = daom.obterObreiro(cpf);
//		this.obreiro.setNome(nome);
//		this.obreiro.setCargo(cargo);
//		this.obreiro.setCongregacao(congregacao);
//		//this.controleDeObreiro.cadastrarObreiro(request, this.obreiro);
//		//request.setAttribute("listaDeCongregacao", session.getAttribute("listaDeCongregacao"));
//
//		this.validator.verificarCamposValidos(this.obreiro);
//		this.formularioDeObreiro = new FormularioDeObreiro(this.obreiro);
//
//		BuscaAvancada ba = new BuscaAvancada();
//		List<Congregacao> listc = ba.buscarCongregacao("", "");
//		request.setAttribute("listaCongregacao", listc);
//
//		if(!this.validator.isFormularioValido()){
//			this.formularioDeObreiro.setStatus("Erro ao Editar");
//			request.setAttribute("viewObreiro", this.formularioDeObreiro);
//			request.setAttribute("errorObreiro", this.validator);
//			RequestDispatcher view = request.getRequestDispatcher("EditarObreiro.jsp");
//			view.forward(request, response);
//		}
//		else{
//			DataAccessObjectManager hibernate = new DataAccessObjectManager();
//			hibernate.mergeDataObjeto(this.obreiro);
//			this.formularioDeObreiro = new FormularioDeObreiro(obreiro);
//			this.formularioDeObreiro.setStatus("Sucesso ao Editar");
//			request.setAttribute("viewObreiro", this.formularioDeObreiro);
//			request.setAttribute("obreiro", this.obreiro);
//			RequestDispatcher view = request.getRequestDispatcher("EditarObreiro.jsp");
//			view.forward(request, response);
//		}
	}

}
