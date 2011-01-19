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
		
		request.setAttribute("formularioDeObreiro", formularioDeObreiro);
		
		//Obtenção de parâmetros necessários obtidos da página
		/*String nome = request.getParameter("Nome");
		String cpf = request.getParameter("Cpf");
		String cargo = request.getParameter("Cargo");
		String idCongregacaoEscolhido = request.getParameter("idCongregacao");
		long idCongregacao = Long.parseLong(idCongregacaoEscolhido);
		String congregacao = (String)request.getParameter("Congregacao");*/
		
		return respostaOperacao;
	}

}
