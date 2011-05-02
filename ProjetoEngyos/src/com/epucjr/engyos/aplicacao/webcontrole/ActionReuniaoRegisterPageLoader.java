package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeReuniao;

public class ActionReuniaoRegisterPageLoader implements Command{

	public Object execute(Object... arg) {
		//Instancia��o de objetos necess�rios para carregar a p�gina
		HttpServletRequest request = (HttpServletRequest) arg[0];		
		FormularioDeReuniao formularioDeReuniao = new FormularioDeReuniao();		
		String respostaOperacao = formularioDeReuniao.getMensagemStatus();		
		//Solu��o tempor�ria para n�o aparecer o msg pagina carregana na p�gina
		formularioDeReuniao.setMensagemStatus(null);
		
		request.setAttribute("formularioDeReuniao", formularioDeReuniao);		
		
		return respostaOperacao;
	}
	
}
