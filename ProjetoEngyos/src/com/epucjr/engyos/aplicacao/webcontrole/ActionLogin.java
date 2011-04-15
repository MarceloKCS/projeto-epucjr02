package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.UserSessionControl;
import com.epucjr.engyos.dominio.administrativo.ValidadorLogin;
import com.epucjr.engyos.dominio.modelo.Administrador;
import com.epucjr.engyos.dominio.visualizacao.PaginaDeLogin;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

/**
 *Acao respons�vel pela criac�ao e autentica��o do usu�rio
 *
 *@author Projeto Engyos Team
 */
public class ActionLogin implements Command {

  

    /**
     * Executa a a��o de efetuar o login de um usu�rio a partir do nome de usu�rio e da senha.
     *
     * @param arg O <code>HttpServletRequest</code> e o <code>HttpServletResponse</code>
     *
     * @return o Objeto com o resultado da acao requisitada, utilizada pela view
     *factory no <code>FrontControllerServlet</code>
     */
    public Object execute(Object... arg) {
        HttpServletRequest request = (HttpServletRequest) arg[0];        
        ValidadorLogin validadorLogin = new ValidadorLogin();
        PaginaDeLogin paginaDeLogin = new PaginaDeLogin();
        UserSessionControl userSessionControl = null;
        DataAccessObjectManager dataAccessObjectManager = null;
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        if (!UserSessionControl.sessaoEstaAtiva(request.getSession())) {

            userSessionControl = new UserSessionControl(request.getSession());

            //1. recupera��o do administrador requisitado para login, sendo o seu
            //login a sua chave no sistema
            dataAccessObjectManager = new DataAccessObjectManager();
            Administrador administrador = dataAccessObjectManager.obterAdministrador(login);

            //2.Verifica se foi possivel obter o administrador a partir da chave, ou a opera��o de banco de
            //dados foi bem sucedida
            if (dataAccessObjectManager.isOperacaoEfetuada()) {
                
                validadorLogin.verificarUsuariovalido(administrador, senha);

                if(validadorLogin.isUsuarioValido()){
                   
                   userSessionControl.criarEDefinirDadosSessaoDeUsuario(administrador.obterNome() + " " + administrador.obterSobrenome(), administrador.obterCPF(), administrador.getSessionStatus());
                   paginaDeLogin.setMensagemStatus(validadorLogin.getMensagemStatus());
                   System.out.println("requestSession_NAME: " + request.getSession().getAttribute(UserSessionControl.NOME_USUARIO));
                    
                }
                else{

                    paginaDeLogin.setMensagemStatus(validadorLogin.getMensagemStatus());
                    
                }
                
            } else {
                validadorLogin.setStatusCondition(ValidadorLogin.STATUS_ERROR);
                paginaDeLogin.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
            }

        }
        else{
            validadorLogin.setStatusCondition(ValidadorLogin.STATUS_WARNING);
            paginaDeLogin.setMensagemStatus("Sess�o j� Iniciada");
        }

        //Fechando o EntityManager de DataAccessObjectManager ap�s uso
        if (dataAccessObjectManager != null) {
            dataAccessObjectManager.fecharEntityManager();
        }

        paginaDeLogin.setValidadorLogin(validadorLogin);
        request.setAttribute("paginaDeLogin", paginaDeLogin);

        String msgResposta = paginaDeLogin.getMensagemStatus();

        System.out.println("msgResposta = " + msgResposta);
       

        return msgResposta;

    }
}
