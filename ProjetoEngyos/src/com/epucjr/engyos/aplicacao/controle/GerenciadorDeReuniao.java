package com.epucjr.engyos.aplicacao.controle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeReuniao;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeReuniao;

public class GerenciadorDeReuniao {
	
	private ValidadorDeFormularioDeReuniao validator;
	private FormularioDeReuniao formularioDeReuniao;
	
	public GerenciadorDeReuniao(){
		this.validator = new ValidadorDeFormularioDeReuniao();
	}
	
	public void cadastrarReuniao(HttpServletRequest request, Reuniao reuniao){
		this.validator.verificarCamposValidos(reuniao);
		this.formularioDeReuniao = new FormularioDeReuniao(reuniao);
		if(!this.validator.isFormularioValido()){
			HttpSession session = request.getSession();
			session.setAttribute("viewReuniao", this.formularioDeReuniao);
			session.setAttribute("reuniao", reuniao);
			session.setAttribute("errorReuniao", this.validator);
			System.out.println("!ok");
		}
		else{
			//DataAccessObjectManager hibernate = new DataAccessObjectManager();
			//hibernate.persistirObjeto(this.congregacao);
			System.out.println("ok");
		}
	}
	
	public void editarReuniao(HttpServletRequest request, Reuniao reuniao){
		this.validator.verificarCamposValidos(reuniao);
		this.formularioDeReuniao = new FormularioDeReuniao(reuniao);
		if(!this.validator.isFormularioValido()){
			HttpSession session = request.getSession();
			session.setAttribute("viewReuniao", this.formularioDeReuniao);
			session.setAttribute("reuniao", reuniao);
			session.setAttribute("errorReuniao", this.validator.getErrors());
			System.out.println("!ok");
		}
		else{
			//DataAccessObjectManager hibernate = new DataAccessObjectManager();
			//hibernate.margeObjeto(this.congregacao);
			System.out.println("ok");
		}
	}

}
