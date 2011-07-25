package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.crud.BuscaAvancada;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDaReuniao;
import org.apache.log4j.Logger;

public class ActionReuniaoFindCommand implements Command{
    private static org.apache.log4j.Logger log = Logger.getLogger(ActionReuniaoFindCommand.class);
	
	public Object execute(Object... arg) {
		//Instancia��o de objetos e vari�veis necess�rios para a realiza��o da busca
		HttpServletRequest request = (HttpServletRequest) arg[0];
		
		//Vari�veis a serem obtidas na busca
		String parametroBusca = request.getParameter("busca_input");
		int paginaSelecionada = 0;
		String paginaSelecionadaStr = request.getParameter("paginaCorrente");

                log.debug("busca_input = " + parametroBusca);
                log.debug("paginaCorrente = " + paginaSelecionadaStr);
		
		//Prepara��o dos campos de busca respeitando as especifica��es dos m�todos
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
		buscaAvancada.buscarReuniao(parametroBusca, paginaSelecionada);
		FormularioDeBuscaDaReuniao resultadoDeBuscaDaReuniao = new FormularioDeBuscaDaReuniao(buscaAvancada.getListaDeReunioesEncontradas(), paginaSelecionada, buscaAvancada.getQuantidadeDePagina());
		resultadoDeBuscaDaReuniao.setParametroDeBusca(parametroBusca);
		resultadoDeBuscaDaReuniao.setMensagemStatus(buscaAvancada.getMensagemStatus());
		
		request.setAttribute("resultadoDeBuscaDaReuniao", resultadoDeBuscaDaReuniao);
		
		return buscaAvancada.getMensagemStatus();
	}

}
