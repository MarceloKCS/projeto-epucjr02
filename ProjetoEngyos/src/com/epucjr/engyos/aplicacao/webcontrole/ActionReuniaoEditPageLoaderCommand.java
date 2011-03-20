package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.web.FrontControlerServlet;
import com.epucjr.engyos.dominio.factory.CommandFactory;
import com.epucjr.engyos.dominio.factory.ViewFactory;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeReuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Classe que atende a uma acao de carregar o formul�rio a partir da reuni�o
 * selecionada na busca
 *
 * @author Projeto Engyos Team
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class ActionReuniaoEditPageLoaderCommand implements Command{


    /**
     * M�todo que executa a requisi��o de carga do formul�rio de reuni�o para edi��o
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
        FormularioDeReuniao formularioDeReuniao = new FormularioDeReuniao();
        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();

        //Obtendo o id da reuni�o requisitada para a edi��o
        String idReuniaoString = request.getParameter("idReuniao");
        long idReuniao = 0;

        if(idReuniaoString != null && !idReuniaoString.equals("")){
            idReuniao = Long.parseLong(idReuniaoString.trim());
        }

        //Carregando o reuni�o requisitada
        Reuniao reuniao = dataAccessObjectManager.obterReuniao(idReuniao);

        //Carregando os dados necess�rios para a edi��o de reuni�o

        if(dataAccessObjectManager.isOperacaoEfetuada()){
            formularioDeReuniao.definirCamposPreenchidos(reuniao);
        }


        String respostaOperacao = dataAccessObjectManager.getMensagemStatus();

         //Solu��o tempor�ria para n�o aparecer o msg pagina carregana na p�gina
        formularioDeReuniao.setMensagemStatus(null);

        request.setAttribute("formularioDeReuniao", formularioDeReuniao);

        return respostaOperacao;
    }


}
