package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeObreiro;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeObreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class ObreiroRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Obreiro obreiro;
    private ValidadorDeFormularioDeObreiro validator;
    private FormularioDeObreiro formularioDeObreiro;

    public ObreiroRegisterServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.validator = new ValidadorDeFormularioDeObreiro();
//        HttpSession session = request.getSession();
//
//        String nome = (String) request.getParameter("Nome");
//        String cpf = (String) request.getParameter("Cpf");
//        String cargo = (String) request.getParameter("Cargo");
//        String congregacao = (String) request.getParameter("Congregacao");
////		String senha = (String)request.getParameter("Senha");
////		String senhaConfirmacao = (String)request.getParameter("senhaConfirmacao");
//        this.obreiro = new Obreiro(nome, cargo, cpf, congregacao);
////		this.controleDeObreiro.cadastrarObreiro(request, this.obreiro);
//        request.setAttribute("listaDeCongregacao", session.getAttribute("listaDeCongregacao"));
//
//        this.validator.verificarCamposValidos(this.obreiro);
//        this.formularioDeObreiro = new FormularioDeObreiro(this.obreiro);
//
//        if (!this.validator.isFormularioValido()) {
//            this.formularioDeObreiro.setStatus("Erro ao Cadastrar");
//            request.setAttribute("viewObreiro", this.formularioDeObreiro);
//            request.setAttribute("errorObreiro", this.validator);
//            RequestDispatcher view = request.getRequestDispatcher("CadastrarObreiro.jsp");
//            view.forward(request, response);
//        } else {
//            DataAccessObjectManager hibernate = new DataAccessObjectManager();
//            hibernate.persistirObjeto(this.obreiro);
//            this.formularioDeObreiro = new FormularioDeObreiro(null);
//            this.formularioDeObreiro.setStatus("Sucesso ao Cadastrar");
//            request.setAttribute("viewObreiro", this.formularioDeObreiro);
//            request.setAttribute("obreiro", this.obreiro);
//            RequestDispatcher view = request.getRequestDispatcher("CadastrarObreiro.jsp");
//            view.forward(request, response);
//        }
    }
}
