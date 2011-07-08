/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeAdministrador;
import com.epucjr.engyos.dominio.modelo.Administrador;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeAdministrador;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
/**
 *
 * @author Rogerio
 */
public class ActionAdministradorEditCommand implements Command{
    private static org.apache.log4j.Logger log = Logger.getLogger(ActionAdministradorEditCommand.class);
    @Override
    public Object execute(Object... arg) {
        //Instanciação de objetos e variáveis necessários para a realização do cadastro
        HttpServletRequest request = (HttpServletRequest) arg[0];
        DataAccessObjectManager dataAccessObjectManager = null;
        FormularioDeAdministrador formularioDeAdministrador = null;
        String msgSenha = "";
        String respostaOperacao = "";
        //Obtenção de parâmetros necessários obtidos da página
        String nome = request.getParameter("Nome");
        String cpf = request.getParameter("Cpf");
        String senha = request.getParameter("Senha");
        String senhaConfirmacao = request.getParameter("SenhaConfirmacao");
        log.debug("Nome: " + nome);
        log.debug("Cpf: " + cpf);
        log.debug("Senha: " + senha);
        log.debug("SenhaConfirmacao: " + senhaConfirmacao);

        //Reobtenção do obreiro a ser editado
        dataAccessObjectManager = new DataAccessObjectManager();
        Administrador administrador = dataAccessObjectManager.obterAdministrador(cpf);
        log.debug("Administrador reobtido: " + administrador.obterNome());

         //Reorganização dos dados para validaçao
        if (!nome.equals(administrador.obterNome())) {
            administrador.definirNome(nome);
        }

        //Não adequado definir mensagens de retorno aqui, mas...
        if (!senha.equals("") && !senha.equals(administrador.getIdentificacao().getSenha())) {
            administrador.getIdentificacao().setSenha(senha);
            msgSenha = "Senha Alterada";
        } else {
            senha = administrador.getIdentificacao().getSenha();
            senhaConfirmacao = administrador.getIdentificacao().getSenha();
            msgSenha = "Senha Não Alterada";
        }

        //1. Validar os dados cadastrais
        ValidadorDeFormularioDeAdministrador validadorDeFormularioDeAdministrador = new ValidadorDeFormularioDeAdministrador();
        validadorDeFormularioDeAdministrador.verificarCamposValidos(nome, cpf, senha, senhaConfirmacao);

        if (validadorDeFormularioDeAdministrador.isFormularioValido()) {
            //Identificacao identificacao = new Identificacao(impressaoDigital, senha);
            //TODO para teste, aós o recebimento dos parâmetros da página alterar

            dataAccessObjectManager.mergeDataObjeto(administrador);

            //Realização de passos para caso de sucesso ou fracasso por ocorrência de um erro interno
            //ex. banco de dados
            if (dataAccessObjectManager.isOperacaoEfetuada()) {
                //Instanciação e Carregar dados do obreiro registrado para apresentação
                formularioDeAdministrador = new FormularioDeAdministrador();
                formularioDeAdministrador.definirDadosDeConfirmacaoDeEdicao(dataAccessObjectManager.getMensagemStatus(), nome, cpf, msgSenha);

                //Define mensagem de sucesso ao editar
                formularioDeAdministrador.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
            }
            //Ocorreu um erro de edição
            else {
                formularioDeAdministrador = new FormularioDeAdministrador();
                formularioDeAdministrador.definirCamposPreenchidosPeloUsuario(request);
                formularioDeAdministrador.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
            }

        }
         else {
            formularioDeAdministrador = new FormularioDeAdministrador();
            formularioDeAdministrador.setValidadorDeFormularioDeAdministrador(validadorDeFormularioDeAdministrador);
            formularioDeAdministrador.definirCamposPreenchidosPeloUsuario(request);
            formularioDeAdministrador.setMensagemStatus("Erro ao Editar");
         }

        //Fechando o EntityManager de DataAccessObjectManager após uso
        if (dataAccessObjectManager != null) {
            dataAccessObjectManager.fecharEntityManager();
        }

        respostaOperacao = formularioDeAdministrador.getMensagemStatus();
        request.setAttribute("formularioDeAdministrador", formularioDeAdministrador);
        return respostaOperacao;
    }


}
