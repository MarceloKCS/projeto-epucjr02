/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.web.FrontControlerServlet;
import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeCongregacao;
import com.epucjr.engyos.dominio.factory.CommandFactory;
import com.epucjr.engyos.dominio.factory.ViewFactory;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeCongregacao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import javax.servlet.http.HttpServletRequest;

/**
 *Classe que tem como finalidade executar a ação que cumpra a promessa de editar uma congregação
 * cadastrada no sistema
 *
 * @author Projeto Engyos Team
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class ActionCongregacaoEditCommand implements Command{


    /**
     * Método que executa a requisição de edição do formulário de congregação
     *
     * @param arg O <code>HttpServletRequest</code> e o <code>HttpServletResponse</code>
     * @return o Objeto com o resultado da acao requisitada, utilizada pela view
     *factory no <code>FrontControllerServlet</code>
     *
     * @see FrontControlerServlet#servico(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse
     * @see CommandFactory
     * @see ViewFactory
     * @see Congregacao
     * @see ValidadorDeFormularioDeCongregacao
     * @see DataAccessObjectManager
     * @see FormularioDeCongregacao
     */
    @Override
    public Object execute(Object... arg) {
         //Instanciação de objetos e variáveis necessários para a realização do cadastro
        HttpServletRequest request = (HttpServletRequest) arg[0];
        DataAccessObjectManager dataAccessObjectManager = null;
        FormularioDeCongregacao formularioDeCongregacao = null;

        //Obtenção de parâmetros necessários obtidos da página
        String nomeDaCongregacao = request.getParameter("Nome");
	String endereco = request.getParameter("Endereco");
        String idCongregacaoString = request.getParameter("idCongregacao");
        long idCongregacao = 0;

        if(idCongregacaoString != null && !idCongregacaoString.equals("")){
            idCongregacao = Long.parseLong(idCongregacaoString.trim());
        }

         //Reobtenção da congregacao a ser editada
         dataAccessObjectManager = new DataAccessObjectManager();
         Congregacao congregacao = dataAccessObjectManager.obterCongregacao(idCongregacao);

          //Reorganização dos dados para validaçao
         if(!nomeDaCongregacao.equals(congregacao.getNome())){
             congregacao.setNome(nomeDaCongregacao);
         }
         if(!endereco.equals(congregacao.getEndereco())){
             congregacao.setEndereco(endereco);
         }

        //1. Validar os dados cadastrais
        ValidadorDeFormularioDeCongregacao validadorDeFormularioDeCongregacao = new ValidadorDeFormularioDeCongregacao();
        validadorDeFormularioDeCongregacao.verificarCamposValidos(nomeDaCongregacao, endereco);

        if (validadorDeFormularioDeCongregacao.isFormularioValido()) {
            dataAccessObjectManager.mergeDataObjeto(congregacao);
            //Realização de passos para caso de sucesso ou fracasso por ocorrência de um erro interno
            //ex. banco de dados
            if (dataAccessObjectManager.isOperacaoEfetuada()) {
                formularioDeCongregacao = new FormularioDeCongregacao();
                formularioDeCongregacao.definirDadosDeConfirmacaoDeEdicao(dataAccessObjectManager.getMensagemStatus(), nomeDaCongregacao, endereco);

                //Define mensagem de sucesso ao cadastrar
                formularioDeCongregacao.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());

            } //Ocorreu um erro de edição
            else {
                formularioDeCongregacao = new FormularioDeCongregacao();
                formularioDeCongregacao.definirCamposPreenchidosPeloUsuario(request);
                formularioDeCongregacao.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
            }
        } else {
            formularioDeCongregacao = new FormularioDeCongregacao();
            formularioDeCongregacao.setValidadorDeFormularioDeCongregacao(validadorDeFormularioDeCongregacao);
            formularioDeCongregacao.definirCamposPreenchidosPeloUsuario(request);
            formularioDeCongregacao.setMensagemStatus("Erro ao Editar");
        }

        //Fechando o EntityManager de DataAccessObjectManager após uso
        if (dataAccessObjectManager != null) {
            dataAccessObjectManager.fecharEntityManager();
        }

        String respostaOperacao = formularioDeCongregacao.getMensagemStatus();
        request.setAttribute("formularioDeCongregacao", formularioDeCongregacao);
        System.out.println("value!= " + respostaOperacao);
        return respostaOperacao;
    }


}
