package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.web.FrontControlerServlet;
import com.epucjr.engyos.dominio.factory.CommandFactory;
import com.epucjr.engyos.dominio.factory.ViewFactory;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeCongregacao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Classe que atende a uma acao de carregar o formul�rio a partir da congrega��o
 * selecionada na busca
 *
 * @author Projeto Engyos Team
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class ActionCongregacaoEditPageLoaderCommand implements Command{

    /**
     * M�todo que executa a requisi��o de carga do formul�rio de congrega��o para edi��o
     *
     * @param arg O <code>HttpServletRequest</code> e o <code>HttpServletResponse</code>
     * @return o Objeto com o resultado da acao requisitada, utilizada pela view
     *factory no <code>FrontControllerServlet</code>
     *
     * @see FrontControlerServlet#servico(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse
     * @see CommandFactory
     * @see ViewFactory
     */
    @Override
    public Object execute(Object... arg) {
        //Instancia��o de objetos necess�rios para carregar a p�gina
        HttpServletRequest request = (HttpServletRequest) arg[0];
        FormularioDeCongregacao formularioDeCongregacao = new FormularioDeCongregacao();
        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();

        //Obtendo o id da congrega��o requisitada para a edi��o
        String idCongregacaoString = request.getParameter("idCongregacao");
        long idCongregacao = 0;
        System.out.println("valorID = " + idCongregacao);

        if(idCongregacaoString != null && !idCongregacaoString.equals("")){
            idCongregacao = Long.parseLong(idCongregacaoString);
        }

        //Carregando a congrega��o requisitada
        Congregacao congregacao = dataAccessObjectManager.obterCongregacao(idCongregacao);

        //Carregando os dados necess�rios para a edi��o de congrega��o
        if(dataAccessObjectManager.isOperacaoEfetuada()){
            formularioDeCongregacao.definirCamposPreenchidos(congregacao);
        }

        String respostaOperacao = dataAccessObjectManager.getMensagemStatus();

        //Fechando o EntityManager de DataAccessObjectManager ap�s uso
        if (dataAccessObjectManager != null) {
            dataAccessObjectManager.fecharEntityManager();
        }

        request.setAttribute("formularioDeCongregacao", formularioDeCongregacao);


        return respostaOperacao;
    }



}
