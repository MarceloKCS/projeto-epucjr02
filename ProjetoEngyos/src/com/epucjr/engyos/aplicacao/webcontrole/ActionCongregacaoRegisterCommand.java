package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeCongregacao;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeCongregacao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import org.apache.log4j.Logger;

public class ActionCongregacaoRegisterCommand implements Command {
    private static org.apache.log4j.Logger log = Logger.getLogger(ActionCongregacaoRegisterCommand.class);


    @Override
    public Object execute(Object... arg) {
        //Instanciação de objetos e variáveis necessários para a realização do cadastro
        HttpServletRequest request = (HttpServletRequest) arg[0];
        DataAccessObjectManager dataAccessObjectManager = null;
        FormularioDeCongregacao formularioDeCongregacao = null;

        //Obtenção de parâmetros necessários obtidos da página
        String nomeDaCongregacao = request.getParameter("Nome");
        String endereco = request.getParameter("Endereco");
        String congregacaoPadrao = request.getParameter("congregacao_padrao");
        log.debug("Nome: " + nomeDaCongregacao);
        log.debug("Endereco: " + endereco);
        log.debug("congregacao_padrao: " + congregacaoPadrao);
        //1. Validar os dados cadastrais
        ValidadorDeFormularioDeCongregacao validadorDeFormularioDeCongregacao = new ValidadorDeFormularioDeCongregacao();
        validadorDeFormularioDeCongregacao.verificarCamposValidos(nomeDaCongregacao, endereco);

        if (validadorDeFormularioDeCongregacao.isFormularioValido()) {
            dataAccessObjectManager = new DataAccessObjectManager();

            //Como os datos são validos, persistir a Congregacao
            Congregacao congregacao = new Congregacao(nomeDaCongregacao, endereco);
            if(congregacaoPadrao != null && congregacaoPadrao.equals("selecionado")){
                congregacao.setCongregacaoPadrao(true);
            }

            dataAccessObjectManager.persistirObjeto(congregacao);

            //Realização de passos para caso de sucesso ou fracasso por ocorrência de um erro interno
            //ex. banco de dados
            if (dataAccessObjectManager.isOperacaoEfetuada()) {
                //Instanciação e Carregar dados da congregacao registrada para apresentação
                formularioDeCongregacao = new FormularioDeCongregacao();
                formularioDeCongregacao.definirDadosDeConfirmacaoDeCadastro(dataAccessObjectManager.getMensagemStatus(), nomeDaCongregacao, endereco);

                //Define mensagem de sucesso ao cadastrar
                formularioDeCongregacao.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());

            } //Ocorreu um erro de cadastro
            else {
                formularioDeCongregacao = new FormularioDeCongregacao();
                formularioDeCongregacao.definirCamposPreenchidosPeloUsuario(request);
                formularioDeCongregacao.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
            }

        } else {
            formularioDeCongregacao = new FormularioDeCongregacao();
            formularioDeCongregacao.setValidadorDeFormularioDeCongregacao(validadorDeFormularioDeCongregacao);
            formularioDeCongregacao.definirCamposPreenchidosPeloUsuario(request);
            formularioDeCongregacao.setMensagemStatus("Erro ao Cadastrar");
        }

        //Fechando o EntityManager de DataAccessObjectManager após uso
        if (dataAccessObjectManager != null) {
            dataAccessObjectManager.fecharEntityManager();
        }
        String respostaOperacao = formularioDeCongregacao.getMensagemStatus();
        request.setAttribute("formularioDeCongregacao", formularioDeCongregacao);

        System.out.println("Resposta = " + respostaOperacao);
        return respostaOperacao;

    }
}
