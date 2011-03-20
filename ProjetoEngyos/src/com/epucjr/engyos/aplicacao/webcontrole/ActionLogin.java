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

  

    public Object execute(Object... arg) {
        HttpServletRequest request = (HttpServletRequest) arg[0];        
        ValidadorLogin validadorLogin = new ValidadorLogin();
        PaginaDeLogin paginaDeLogin = new PaginaDeLogin();
        UserSessionControl userSessionControl = null;
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        if (!UserSessionControl.sessaoEstaAtiva(request.getSession())) {

            userSessionControl = new UserSessionControl(request.getSession());

            // pega o admin
            DataAccessObjectManager dao = new DataAccessObjectManager();
            Administrador admin = dao.obterAdministrador(login);

            if (dao.isOperacaoEfetuada()) {
                
                validadorLogin.verificarUsuariovalido(admin, senha);

                if(validadorLogin.isUsuarioValido()){
                   
                   userSessionControl.criarEDefinirDadosSessaoDeUsuario(admin.obterNome() + " " + admin.obterSobrenome(), admin.obterCPF());
                   paginaDeLogin.setMensagemStatus(validadorLogin.getMensagemStatus());
                    System.out.println("requestSession_NAME: " + request.getSession().getAttribute(UserSessionControl.NOME_USUARIO));
                }
                else{
                    paginaDeLogin.setMensagemStatus(validadorLogin.getMensagemStatus());
                }
                
            } else {
                validadorLogin.setStatusCondition(ValidadorLogin.STATUS_ERROR);
                paginaDeLogin.setMensagemStatus(dao.getMensagemStatus());
            }

        }
        else{
            validadorLogin.setStatusCondition(ValidadorLogin.STATUS_WARNING);
            paginaDeLogin.setMensagemStatus("Sessão já Iniciada");
        }

        paginaDeLogin.setValidadorLogin(validadorLogin);
        request.setAttribute("paginaDeLogin", paginaDeLogin);

        String msgResposta = paginaDeLogin.getMensagemStatus();

        System.out.println("msgResposta = " + msgResposta);
       

        return msgResposta;

    }
}
