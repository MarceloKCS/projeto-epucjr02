package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeAdministrador;
import com.epucjr.engyos.dominio.modelo.Administrador;
import com.epucjr.engyos.dominio.modelo.Identificacao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeAdministrador;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Rogerio
 */
public class ActionAdministradorRegisterCommand implements Command{
    private static org.apache.log4j.Logger log = Logger.getLogger(ActionAdministradorRegisterCommand.class);

    @Override
    public Object execute(Object... arg) {
        //Instanciação de objetos e variáveis necessários para a realização do cadastro
        HttpServletRequest request = (HttpServletRequest) arg[0];
        DataAccessObjectManager dataAccessObjectManager = null;
        FormularioDeAdministrador formularioDeAdministrador = null;
        String respostaOperacao = "";
        //Obtenção de parâmetros necessários obtidos da página
        String nome = request.getParameter("Nome");
        String cpf = request.getParameter("Cpf");
        String senha = request.getParameter("Senha");
        String senhaConfirmacao = request.getParameter("SenhaConfirmacao");
        log.debug("nome: " + nome);
        log.debug("Cpf: " + cpf);
        log.debug("Senha: " + senha);
        log.debug("SenhaConfirmacao: " + senhaConfirmacao);

        //1. Validar os dados cadastrais
        ValidadorDeFormularioDeAdministrador validadorDeFormularioDeAdministrador = new ValidadorDeFormularioDeAdministrador();
        validadorDeFormularioDeAdministrador.verificarCamposValidos(nome, cpf, senha, senhaConfirmacao);

        if(validadorDeFormularioDeAdministrador.isFormularioValido()){
            dataAccessObjectManager = new DataAccessObjectManager();
            Identificacao identificacao = new Identificacao(senha);

            Administrador administrador = new Administrador(nome, cpf, identificacao);
            dataAccessObjectManager.persistirObjeto(administrador);

            //Realização de passos para caso de sucesso ou fracasso por ocorrência de um erro interno
            //ex. banco de dados
            if (dataAccessObjectManager.isOperacaoEfetuada()) {
                //Instanciação e Carregar dados do obreiro registrado para apresentação
                formularioDeAdministrador = new FormularioDeAdministrador();
                formularioDeAdministrador.definirDadosDeConfirmacaoDeCadastro(dataAccessObjectManager.getMensagemStatus(), nome, cpf);

                //Define mensagem de sucesso ao cadastrar
		formularioDeAdministrador.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
            }
            //Ocorreu um erro de cadastro
            else {
                formularioDeAdministrador = new FormularioDeAdministrador();
                formularioDeAdministrador.definirCamposPreenchidosPeloUsuario(request);
                formularioDeAdministrador.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
            }

        }
        else{
            formularioDeAdministrador = new FormularioDeAdministrador();
            formularioDeAdministrador.setValidadorDeFormularioDeAdministrador(validadorDeFormularioDeAdministrador);
            formularioDeAdministrador.definirCamposPreenchidosPeloUsuario(request);
            formularioDeAdministrador.setMensagemStatus("Erro ao Cadastrar");
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
