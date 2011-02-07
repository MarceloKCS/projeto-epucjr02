package com.epucjr.engyos.aplicacao.controle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeCongregacao;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeCongregacao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class GerenciadorDeCongregacao {

	private ValidadorDeFormularioDeCongregacao validator;
	private FormularioDeCongregacao formularioDeCongregacao;
	
	public GerenciadorDeCongregacao(){
		this.validator = new ValidadorDeFormularioDeCongregacao();
	}
	
	public void cadastrarCongregacao(HttpServletRequest request, Congregacao congregacao){
//		this.validator.verificarCamposValidos(congregacao);
//		this.formularioDeCongregacao = new FormularioDeCongregacao(congregacao);
//		if(!this.validator.isFormularioValido()){
//			HttpSession session = request.getSession();
//			session.setAttribute("viewCongregacao", this.formularioDeCongregacao);
//			session.setAttribute("congregacao", congregacao);
//			session.setAttribute("errorCongregacao", this.validator);
//			System.out.println("!ok");
//		}
//		else{
//			DataAccessObjectManager hibernate = new DataAccessObjectManager();
//			hibernate.persistirObjeto(congregacao);
//			System.out.println("ok");
//		}
	}
	
	public void editarCongregacao(HttpServletRequest request, Congregacao congregacao){
//		this.validator.verificarCamposValidos(congregacao);
//		this.formularioDeCongregacao = new FormularioDeCongregacao(congregacao);
//		if(!this.validator.isFormularioValido()){
//			HttpSession session = request.getSession();
//			session.setAttribute("viewCongregacao", this.formularioDeCongregacao);
//			session.setAttribute("congregacao", congregacao);
//			session.setAttribute("errorCongregacao", this.validator.getErrors());
//			System.out.println("!ok");
//		}
//		else{
//			DataAccessObjectManager hibernate = new DataAccessObjectManager();
//			hibernate.persistirObjeto(congregacao);
//			System.out.println("ok");
//		}
	}
	
}
