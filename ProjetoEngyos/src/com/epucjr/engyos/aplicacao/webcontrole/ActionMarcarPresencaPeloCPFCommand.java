package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.IReuniaoMonitor;
import com.epucjr.engyos.aplicacao.controle.ReuniaoMonitor;
import com.epucjr.engyos.aplicacao.controle.ReuniaoSessionControl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Projeto Engyos
 */
public class ActionMarcarPresencaPeloCPFCommand implements Command{

    @Override
    public Object execute(Object... arg) {
        HttpServletRequest request = (HttpServletRequest) arg[0];
        String resposta = "padrao";
        //Carrega o controlador de ssess�o da reuni�o que realizar� os controles
        //de uma reuni�o e suas regras quando da sua execu��o
        ReuniaoSessionControl reuniaoSessionControl = new ReuniaoSessionControl(request.getSession());

        String cpfObreiro = request.getParameter("cpf");
        String idReuniaoPagina = request.getParameter("idReuniao");
        long idReuniao = 0;

        if (idReuniaoPagina != null && !idReuniaoPagina.equals("")) {
            idReuniao = Long.parseLong(idReuniaoPagina.trim());
        }

        //Algumas mensagens de teste, apagar depois      
        System.out.println("CPF = " + cpfObreiro);
        System.out.println("REUNIAO_ID = " + idReuniao);

        //Uma presen�a n�o pode ser marcada caso a reuni�o n�o esteja iniciada.

        if (reuniaoSessionControl.verificarSessaoReuniaoAberta()) {
            IReuniaoMonitor reuniaoMonitor = new ReuniaoMonitor(idReuniao);
            //marca a presenca da reuniao pelo CPF
            reuniaoMonitor.marcarPresencaPeloCPF(cpfObreiro);

            //Manda a resposta da opera��o para a p�gina
            resposta = reuniaoMonitor.getMensagemStatus();

        } else {
            resposta = "Reuni�o n�o iniciada";
        }
        return resposta;

    }


}
