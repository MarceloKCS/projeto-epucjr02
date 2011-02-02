package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeCongregacao;

public class ActionCongregacaoRegisterPageLoaderCommand implements Command{

	public Object execute(Object... arg) {
		//Instancia��o de objetos e vari�veis necess�rios para a realiza��o do cadastro
		HttpServletRequest request = (HttpServletRequest) arg[0];
		FormularioDeCongregacao formularioDeCongregacao = new FormularioDeCongregacao();
		
		String respostaOperacao = "Formulario Carregado";
		//Solu��o tempor�ria para n�o aparecer o msg pagina carregana na p�gina
		formularioDeCongregacao.setMensagemStatus(null);
		
		request.setAttribute("formularioDeCongregacao", formularioDeCongregacao);
		
		return respostaOperacao;
	}
	
}
