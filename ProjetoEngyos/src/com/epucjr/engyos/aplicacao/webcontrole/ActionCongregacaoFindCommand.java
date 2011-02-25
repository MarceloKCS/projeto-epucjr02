package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.crud.BuscaAvancada;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDaCongregacao;

public class ActionCongregacaoFindCommand implements Command{
	
	public Object execute(Object... arg) {
		//Instanciação de objetos e variáveis necessários para a realização da busca
		HttpServletRequest request = (HttpServletRequest) arg[0];
		
		//Variáveis a serem obtidas na busca
		String parametroBusca = request.getParameter("busca_input");
		int paginaSelecionada = 0;
		String paginaSelecionadaStr = request.getParameter("paginaCorrente");
		
		System.out.println("PARAMETRO = " + parametroBusca);
		System.out.println("PC = " + paginaSelecionadaStr);
		
		//Preparação dos campos de busca respeitando as especificações dos métodos
		if(parametroBusca == null){
			parametroBusca = "";
		}					
		if(paginaSelecionadaStr == null){
			paginaSelecionada = 1;
		}
		else{
			paginaSelecionada = Integer.parseInt(request.getParameter("paginaCorrente"));
		}
		
		//TODO - Sequencia de mensagem visando teste - REFATORAR
		BuscaAvancada buscaAvancada = new BuscaAvancada();
		buscaAvancada.buscarCongregacao(parametroBusca, paginaSelecionada);
		FormularioDeBuscaDaCongregacao resultadoDeBuscaDaCongregacao = new FormularioDeBuscaDaCongregacao(buscaAvancada.getListaDeCongregacoesEncontradas(), paginaSelecionada, buscaAvancada.getQuantidadeDePagina());
		resultadoDeBuscaDaCongregacao.setParametroDeBusca(parametroBusca);
		resultadoDeBuscaDaCongregacao.setMensagemStatus(buscaAvancada.getMensagemStatus());
		
		request.setAttribute("resultadoDeBuscaDaCongregacao", resultadoDeBuscaDaCongregacao);
		
		return buscaAvancada.getMensagemStatus();
	}

}
