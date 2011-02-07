package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeObreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class ActionObreiroEditPageLoaderCommand implements Command{

	public Object execute(Object... arg) {
		//Instanciação de objetos necessários para carregar a página
		HttpServletRequest request = (HttpServletRequest) arg[0];
		FormularioDeObreiro formularioDeObreiro = new FormularioDeObreiro();
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();	
		
		//Obtendo o id do obreiro requisitado para a edição
		String cpfObreiro = request.getParameter("cpfObreiro");
		
		//Carregando o obreiro requisitado
		Obreiro obreiro = dataAccessObjectManager.obterObreiro(cpfObreiro);
		
		//Carregando os dados necessários para a edição de obreiro
		
		if(dataAccessObjectManager.isOperacaoEfetuada()){
			//formulario
		}
		
		
		String respostaOperacao = formularioDeObreiro.getMensagemStatus();
		
		return null;
	}
	
}
