package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeObreiro;

public class ActionObreiroEditPageLoaderCommand implements Command{

	public Object execute(Object... arg) {
		//Instancia��o de objetos necess�rios para carregar a p�gina
		HttpServletRequest request = (HttpServletRequest) arg[0];
		FormularioDeObreiro formularioDeObreiro = new FormularioDeObreiro();
		String respostaOperacao = formularioDeObreiro.getMensagemStatus();.
		
		return null;
	}
	
}
