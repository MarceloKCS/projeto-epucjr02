package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeObreiro;

public class ActionObreiroRegisterPageLoader implements Command{
	
	public Object execute(Object... arg) {
		//Instancia��o de objetos necess�rios para carregar a p�gina
		HttpServletRequest request = (HttpServletRequest) arg[0];
		FormularioDeObreiro formularioDeObreiro = new FormularioDeObreiro();
		String respostaOperacao = formularioDeObreiro.getMensagemStatus();
		
		request.setAttribute("formularioDeObreiro", formularioDeObreiro);
		
		//Obten��o de par�metros necess�rios obtidos da p�gina
		/*String nome = request.getParameter("Nome");
		String cpf = request.getParameter("Cpf");
		String cargo = request.getParameter("Cargo");
		String idCongregacaoEscolhido = request.getParameter("idCongregacao");
		long idCongregacao = Long.parseLong(idCongregacaoEscolhido);
		String congregacao = (String)request.getParameter("Congregacao");*/
		
		return respostaOperacao;
	}

}
