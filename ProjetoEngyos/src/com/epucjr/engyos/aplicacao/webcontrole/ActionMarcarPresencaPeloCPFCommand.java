package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.IReuniaoMonitor;
import com.epucjr.engyos.aplicacao.controle.ReuniaoMonitor;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Projeto Engyos
 */
public class ActionMarcarPresencaPeloCPFCommand implements Command{

    @Override
    public Object execute(Object... arg) {
        HttpServletRequest request = (HttpServletRequest) arg[0];
       
        String cpfObreiro = request.getParameter("cpf");
        String idReuniaoPagina = request.getParameter("idReuniao");
        long idReuniao = 0;

        if(idReuniaoPagina != null && !idReuniaoPagina.equals("")){
            idReuniao = Long.parseLong(idReuniaoPagina.trim());
        }

        //Algumas mensagens de teste, apagar depois      
        System.out.println("CPF = " + cpfObreiro);
        System.out.println("REUNIAO_ID = " + idReuniao);

        IReuniaoMonitor reuniaoMonitor = new ReuniaoMonitor(idReuniao);

        //marca a presenca da reuniao pelo CPF
        reuniaoMonitor.marcarPresencaPeloCPF(cpfObreiro);

        //Manda a resposta da operação para a página
        String reposta = reuniaoMonitor.getMensagemStatus();

        return reposta;

      
    }


}
