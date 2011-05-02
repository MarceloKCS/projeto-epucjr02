package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.web.FrontControlerServlet;
import com.epucjr.engyos.dominio.factory.CommandFactory;
import com.epucjr.engyos.dominio.factory.ViewFactory;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeObreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

/**
 * Classe que atende a uma acao de carregar o formul�rio a partir do obreiro
 * selecionado na busca
 *
 * @author Projeto Engyos Team
 *
 * @version 1.0
 *
 * @since 1.0
 */

public class ActionObreiroEditPageLoaderCommand implements Command{


        /**
         * M�todo que executa a requisi��o de carga do formul�rio de obreiro para edi��o
         *
         * @param arg O <code>HttpServletRequest</code> e o <code>HttpServletResponse</code>
         * @return o Objeto com o resultado da acao requisitada, utilizada pela view
         *factory no <code>FrontControllerServlet</code>
         *
         * @see FrontControlerServlet#servico(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse
         * @see CommandFactory
         * @see ViewFactory
         */
	public Object execute(Object... arg) {
		//Instancia��o de objetos necess�rios para carregar a p�gina
		HttpServletRequest request = (HttpServletRequest) arg[0];
		FormularioDeObreiro formularioDeObreiro = new FormularioDeObreiro();
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();	
		
		//Obtendo o id do obreiro requisitado para a edi��o
		String cpfObreiro = request.getParameter("cpfObreiro");
		System.out.println("valorCPF = " + cpfObreiro);
                
		//Carregando o obreiro requisitado
		Obreiro obreiro = dataAccessObjectManager.obterObreiro(cpfObreiro);

		//Carregando os dados necess�rios para a edi��o de obreiro		
		if(dataAccessObjectManager.isOperacaoEfetuada()){
			 formularioDeObreiro.definirCamposPreenchidos(obreiro);
		}		
		
                //Fechando o EntityManager de DataAccessObjectManager ap�s uso
                if (dataAccessObjectManager != null) {
                    dataAccessObjectManager.fecharEntityManager();
                }

		String respostaOperacao = formularioDeObreiro.getMensagemStatus();
                System.out.println("Operacao = " + respostaOperacao);
                
                //Solu��o tempor�ria para n�o aparecer o msg pagina carregana na p�gina
		formularioDeObreiro.setMensagemStatus(null);

                request.setAttribute("formularioDeObreiro", formularioDeObreiro);
		
		return respostaOperacao;
	}
	
}
