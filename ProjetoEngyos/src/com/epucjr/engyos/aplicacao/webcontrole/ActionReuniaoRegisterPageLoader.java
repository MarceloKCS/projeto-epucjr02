package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeReuniao;

public class ActionReuniaoRegisterPageLoader implements Command{

	public Object execute(Object... arg) {
		//Instanciação de objetos necessários para carregar a página
		HttpServletRequest request = (HttpServletRequest) arg[0];		
		FormularioDeReuniao formularioDeReuniao = new FormularioDeReuniao();		
		String respostaOperacao = formularioDeReuniao.getMensagemStatus();		
		//Solução temporária para não aparecer o msg pagina carregana na página
		formularioDeReuniao.setMensagemStatus(null);
		
		request.setAttribute("formularioDeReuniao", formularioDeReuniao);		
		
		return respostaOperacao;
	}
	
}
