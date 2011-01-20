package com.epucjr.engyos.aplicacao.webcontrole;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeReuniao;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeReuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

/**
 * Servlet implementation class ReuniaoRegisterServlet
 */
public class ReuniaoRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Reuniao reuniao;	
	private ValidadorDeFormularioDeReuniao validator;
	private FormularioDeReuniao formularioDeReuniao;

    public ReuniaoRegisterServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.validator = new ValidadorDeFormularioDeReuniao();

		
		String dia = (String)request.getParameter("dataReuniaoDia");
		String mes = (String)request.getParameter("dataReuniaoMes");
		String ano = (String)request.getParameter("dataReuniaoAno");
		String data = dia+mes+ano;
		String horaReuniao = (String)request.getParameter("horaReuniao");
		String minutoReuniao = (String)request.getParameter("minutoReuniao");
		String hora = horaReuniao+minutoReuniao;
		this.reuniao = new Reuniao(data, hora);
		
		
		this.validator.verificarCamposValidos(this.reuniao);
		this.formularioDeReuniao = new FormularioDeReuniao(this.reuniao);
		
		if(!this.validator.isFormularioValido()){
			this.formularioDeReuniao.setStatus("Erro ao Cadastrar");
			request.setAttribute("viewReuniao", this.formularioDeReuniao);
			request.setAttribute("errorReuniao", this.validator);
			RequestDispatcher view = request.getRequestDispatcher("CadastrarReuniao.jsp");
			view.forward(request, response);
		}
		else{
			DataAccessObjectManager hibernate = new DataAccessObjectManager();
			hibernate.persistirObjeto(this.reuniao);
			this.formularioDeReuniao = new FormularioDeReuniao(null);
			this.formularioDeReuniao.setStatus("Sucesso ao Cadastrar");
			request.setAttribute("viewReuniao", this.formularioDeReuniao);
			request.setAttribute("reuniao", this.reuniao);
			RequestDispatcher view = request.getRequestDispatcher("CadastrarReuniao.jsp");
			view.forward(request, response);
		}
	}

}