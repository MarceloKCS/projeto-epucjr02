/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.ReuniaoSessionControl;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Rogerio
 */
public class ActionReuniaoSessionFinishCommand implements Command{
    private static org.apache.log4j.Logger log = Logger.getLogger(ActionAdministradorEditCommand.class);
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
