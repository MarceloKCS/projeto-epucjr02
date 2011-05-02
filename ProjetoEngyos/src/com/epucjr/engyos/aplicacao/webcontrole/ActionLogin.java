package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.UserSessionControl;
import com.epucjr.engyos.dominio.administrativo.ValidadorLogin;
import com.epucjr.engyos.dominio.modelo.Administrador;
import com.epucjr.engyos.dominio.visualizacao.PaginaDeLogin;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

/**
 *Acao responsável pela criacçao e autenticação do usuário
 *
 *@author Projeto Engyos Team
 */
public class ActionLogin implements Command {

  

    /**
     * Executa a ação de efetuar o login de um usuário a partir do nome de usuário e da senha.
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

            //1. recuperação do administrador requisitado para login, sendo o seu
            //login a sua chave no sistema
            dataAccessObjectManager = new DataAccessObjectManager();
            Administrador administrador = dataAccessObjectManager.obterAdministrador(login);

            //2.Verifica se foi possivel obter o administrador a partir da chave, ou a operação de banco de
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
            paginaDeLogin.setMensagemStatus("Sessão já Iniciada");
        }

        //Fechando o EntityManager de DataAccessObjectManager após uso
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
