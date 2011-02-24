package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.ReuniaoMonitor;
import com.epucjr.engyos.dominio.visualizacao.PaginaDeReuniao;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rogerio
 */
public class ActionReuniaoStartPageLoader implements Command{

    @Override
    public Object execute(Object... arg) {

        //Instancia��o de objetos e vari�veis necess�rios para a realiza��o da carga
	HttpServletRequest request = (HttpServletRequest) arg[0];

        String idReuniaoIdObtida = request.getParameter("idReuniao");
        long idReuniao = 0;
        if(idReuniaoIdObtida != null && !idReuniaoIdObtida.equals("")){
            idReuniao = Long.parseLong(idReuniaoIdObtida.trim());
        }

        System.out.println("idReuniao = " + idReuniao);

        //Inicializar uma sess�o de reuni�o agendada na p�gina
        ReuniaoMonitor reuniaoMonitor = new ReuniaoMonitor(idReuniao);

        reuniaoMonitor.inicializaReuniao();

        PaginaDeReuniao paginaDeReuniao = reuniaoMonitor.obterPaginaDeReuniaoInicializada();

        request.setAttribute("paginaDeReuniao", paginaDeReuniao);

        String mensagemRetorno = reuniaoMonitor.getMensagemStatus();

        System.out.println("msgReturn = " + mensagemRetorno);

        return mensagemRetorno;

    }


}
