/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.UserSessionControl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rogerio
 */
public class ActionLogoutCommand implements Command{

    @Override
    public Object execute(Object... arg) {

        HttpServletRequest request = (HttpServletRequest) arg[0];
        String msgResposta = "";

        UserSessionControl userSessionControl = new UserSessionControl(request.getSession());

        if(UserSessionControl.sessaoEstaAtiva(request.getSession())){

            userSessionControl.encerrarSessao();

            msgResposta = "Sessão encerrada.";

        }
        else{
            msgResposta = "Nenhuma sessão foi iniciada.";
        }

        System.out.println("msgResposta = " + msgResposta);

        return msgResposta;
        
    }



}
