package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.ReuniaoSessionControl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Projeto Engyos Team
 */
public class ActionReuniaoSessionStartCommand implements Command{

    @Override
    public Object execute(Object... arg) {

        HttpServletRequest request = (HttpServletRequest) arg[0];
        String resposta = "";

        //Inicia uma sessão para a referida reunião com o id da reunião em questão
        ReuniaoSessionControl reuniaoSessionControl = new ReuniaoSessionControl(request.getSession());

        String idReuniaoPagina = request.getParameter("idReuniao");
//        long idReuniao = 0;
//
//        if (idReuniaoPagina != null && !idReuniaoPagina.equals("")) {
//            idReuniao = Long.parseLong(idReuniaoPagina.trim());
//        }

        if (!reuniaoSessionControl.verificarSessaoReuniaoAberta()){
            System.out.println("CLOSED SESSION: STARTING");
            reuniaoSessionControl.criarSessaoDeReuniao();
            reuniaoSessionControl.abrirSessaoReuniao();
            reuniaoSessionControl.armazenaIdReuniaoCorrente(idReuniaoPagina);

            resposta = "Reunião Iniciada";

        } else {
            System.out.println("SESSION ALREADY OPENED");
            resposta = "Reunião já iniciada";
        }

        return resposta;

    }
}
