/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.ReuniaoSessionControl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rogerio
 */
public class ActionReuniaoSessionFinishCommand implements Command{

    @Override
    public Object execute(Object... arg) {
        HttpServletRequest request = (HttpServletRequest) arg[0];
        String resposta = "";

         ReuniaoSessionControl reuniaoSessionControl = new ReuniaoSessionControl(request.getSession());

         if (reuniaoSessionControl.verificarSessionStatusAtiva()){
             reuniaoSessionControl.fecharSessaoReuniao();
             resposta = "reuniao encerrada";
         }
         else{
             resposta = "ERRO : reunião não iniciada ou existente";
         }

        return resposta;
    }



}
