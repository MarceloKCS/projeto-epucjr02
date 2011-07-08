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
        //Instancia��o de objetos e vari�veis necess�rios para a realiza��o do cadastro
        HttpServletRequest request = (HttpServletRequest) arg[0];
        DataAccessObjectManager dataAccessObjectManager = null;
        FormularioDeAdministrador formularioDeAdministrador = null;
        String msgSenha = "";
        String respostaOperacao = "";
        //Obten��o de par�metros necess�rios obtidos da p�gina
        String nome = request.getParameter("Nome");
        String cpf = request.getParameter("Cpf");
        String senha = request.getParameter("Senha");
        String senhaConfirmacao = request.getParameter("SenhaConfirmacao");
        log.debug("Nome: " + nome);
        log.debug("Cpf: " + cpf);
        log.debug("Senha: " + senha);
        log.debug("SenhaConfirmacao: " + senhaConfirmacao);

        //Reobten��o do obreiro a ser editado
        dataAccessObjectManager = new DataAccessObjectManager();
        Administrador administrador = dataAccessObjectManager.obterAdministrador(cpf);
        log.debug("Administrador reobtido: " + administrador.obterNome());

         //Reorganiza��o dos dados para valida�ao
        if (!nome.equals(administrador.obterNome())) {
            administrador.definirNome(nome);
        }

        //N�o adequado definir mensagens de retorno aqui, mas...
        if (!senha.equals("") && !senha.equals(administrador.getIdentificacao().getSenha())) {
            administrador.getIdentificacao().setSenha(senha);
            msgSenha = "Senha Alterada";
        } else {
            senha = administrador.getIdentificacao().getSenha();
            senhaConfirmacao = administrador.getIdentificacao().getSenha();
            msgSenha = "Senha N�o Alterada";
        }

        //1. Validar os dados cadastrais
        ValidadorDeFormularioDeAdministrador validadorDeFormularioDeAdministrador = new ValidadorDeFormularioDeAdministrador();
        validadorDeFormularioDeAdministrador.verificarCamposValidos(nome, cpf, senha, senhaConfirmacao);

        if (validadorDeFormularioDeAdministrador.isFormularioValido()) {
            //Identificacao identificacao = new Identificacao(impressaoDigital, senha);
            //TODO para teste, a�s o recebimento dos par�metros da p�gina alterar

            dataAccessObjectManager.mergeDataObjeto(administrador);

            //Realiza��o de passos para caso de sucesso ou fracasso por ocorr�ncia de um erro interno
            //ex. banco de dados
            if (dataAccessObjectManager.isOperacaoEfetuada()) {
                //Instancia��o e Carregar dados do obreiro registrado para apresenta��o
                formularioDeAdministrador = new FormularioDeAdministrador();
                formularioDeAdministrador.definirDadosDeConfirmacaoDeEdicao(dataAccessObjectManager.getMensagemStatus(), nome, cpf, msgSenha);

                //Define mensagem de sucesso ao editar
                formularioDeAdministrador.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
            }
            //Ocorreu um erro de edi��o
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

        //Fechando o EntityManager de DataAccessObjectManager ap�s uso
        if (dataAccessObjectManager != null) {
            dataAccessObjectManager.fecharEntityManager();
        }

        respostaOperacao = formularioDeAdministrador.getMensagemStatus();
        request.setAttribute("formularioDeAdministrador", formularioDeAdministrador);
        return respostaOperacao;
    }


}
