package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeCongregacao;

public class ActionCongregacaoRegisterPageLoaderCommand implements Command{

	public Object execute(Object... arg) {
		//Instanciação de objetos e variáveis necessários para a realização do cadastro
		HttpServletRequest request = (HttpServletRequest) arg[0];
		FormularioDeCongregacao formularioDeCongregacao = new FormularioDeCongregacao();
		
		String respostaOperacao = "Formulario Carregado";
		//Solução temporária para não aparecer o msg pagina carregana na página
		formularioDeCongregacao.setMensagemStatus(null);
		
		request.setAttribute("formularioDeCongregacao", formularioDeCongregacao);
		
		return respostaOperacao;
	}
	
}
