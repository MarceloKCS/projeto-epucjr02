package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.ReuniaoMonitor;
import com.epucjr.engyos.aplicacao.controle.ReuniaoSessionControl;
import com.epucjr.engyos.dominio.visualizacao.PaginaDeReuniao;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rogerio
 */
public class ActionReuniaoStartPageLoader implements Command{

    @Override
    public Object execute(Object... arg) {
	HttpServletRequest request = (HttpServletRequest) arg[0];
        ReuniaoSessionControl reuniaoSessionControl = new ReuniaoSessionControl();
        String mensagemRetorno = "";
        String idReuniaoIdObtida = request.getParameter("idReuniao");
        long idReuniao = 0;
        if(idReuniaoIdObtida != null && !idReuniaoIdObtida.equals("")){
            idReuniao = Long.parseLong(idReuniaoIdObtida.trim());
        }

        System.out.println("idReuniao = " + idReuniao);

        boolean reuniaoValida = reuniaoSessionControl.verificarReuniaoValida(idReuniao);

        if (reuniaoValida) {
            //Inicializar uma sessão de reunião agendada na página
            ReuniaoMonitor reuniaoMonitor = new ReuniaoMonitor(idReuniao);

            reuniaoMonitor.reuniaoLoader();

            PaginaDeReuniao paginaDeReuniao = reuniaoMonitor.obterPaginaDeReuniaoInicializada();

            request.setAttribute("paginaDeReuniao", paginaDeReuniao);

            mensagemRetorno = reuniaoMonitor.getMensagemStatus();
        } else {
            mensagemRetorno = reuniaoSessionControl.getMessageStatus();
            request.setAttribute("reuniaoFailMessage", mensagemRetorno);
        }
        System.out.println("msgReturn = " + mensagemRetorno);

        return mensagemRetorno;

    }


}
