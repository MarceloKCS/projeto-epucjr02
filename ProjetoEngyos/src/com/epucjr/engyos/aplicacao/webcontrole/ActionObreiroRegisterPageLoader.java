package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeObreiro;

public class ActionObreiroRegisterPageLoader implements Command{
	
	public Object execute(Object... arg) {
		//Instanciação de objetos necessários para carregar a página
		HttpServletRequest request = (HttpServletRequest) arg[0];
		FormularioDeObreiro formularioDeObreiro = new FormularioDeObreiro();
		String respostaOperacao = formularioDeObreiro.getMensagemStatus();
		
		//Solução temporária para não aparecer o msg pagina carregana na página
		formularioDeObreiro.setMensagemStatus(null);
				
		request.setAttribute("formularioDeObreiro", formularioDeObreiro);		
		
		return respostaOperacao;
	}

}
