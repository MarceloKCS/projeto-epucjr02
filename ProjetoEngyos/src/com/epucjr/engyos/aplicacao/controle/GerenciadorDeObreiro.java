package com.epucjr.engyos.aplicacao.controle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeObreiro;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeObreiro;

public class GerenciadorDeObreiro {

	private ValidadorDeFormularioDeObreiro validator;
	private FormularioDeObreiro formularioDeObreiro;
	
	public GerenciadorDeObreiro(){
		this.validator = new ValidadorDeFormularioDeObreiro();
	}
	
	public void cadastrarObreiro(HttpServletRequest request, Obreiro obreiro){
//		this.validator.verificarCamposValidos(obreiro);
//		this.formularioDeObreiro = new FormularioDeObreiro(obreiro);
//		if(!this.validator.isFormularioValido()){
//			HttpSession session = request.getSession();
//			session.setAttribute("viewObreiro", this.formularioDeObreiro);
//			session.setAttribute("obreiro", obreiro);
//			session.setAttribute("errorObreiro", this.validator);
//			System.out.println("!ok");
//		}
//		else{
//			//DataAccessObjectManager hibernate = new DataAccessObjectManager();
//			//hibernate.persistirObjeto(this.congregacao);
//			System.out.println("ok");
//		}
	}
	
	public void editarObreiro(HttpServletRequest request, Obreiro obreiro){
//		this.validator.verificarCamposValidos(obreiro);
//		this.formularioDeObreiro = new FormularioDeObreiro(obreiro);
//		if(!this.validator.isFormularioValido()){
//			HttpSession session = request.getSession();
//			session.setAttribute("viewObreiro", this.formularioDeObreiro);
//			session.setAttribute("obreiro", obreiro);
//			session.setAttribute("errorObreiro", this.validator.getErrors());
//			System.out.println("!ok");
//		}
//		else{
//			//DataAccessObjectManager hibernate = new DataAccessObjectManager();
//			//hibernate.persistirObjeto(this.congregacao);
//			System.out.println("ok");
//		}
	}
	
}
