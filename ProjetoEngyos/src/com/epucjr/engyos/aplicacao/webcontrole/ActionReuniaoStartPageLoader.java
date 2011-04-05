package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.InitAppRoutines;
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


        //Instanciação de objetos e variáveis necessários para a realização da carga
	HttpServletRequest request = (HttpServletRequest) arg[0];

        //TODO lembrar de remover senão vai adr pau cada vez que passar aqui quando implementar o login
//        InitAppRoutines initAppRoutines = new InitAppRoutines(request.getSession());
//        initAppRoutines.limparSession();
        /** DO NOT FORGET TO REMOVE ABOVE****************************************************************/


        String idReuniaoIdObtida = request.getParameter("idReuniao");
        long idReuniao = 0;
        if(idReuniaoIdObtida != null && !idReuniaoIdObtida.equals("")){
            idReuniao = Long.parseLong(idReuniaoIdObtida.trim());
        }

        System.out.println("idReuniao = " + idReuniao);

        //Inicializar uma sessão de reunião agendada na página
        ReuniaoMonitor reuniaoMonitor = new ReuniaoMonitor(idReuniao);

        reuniaoMonitor.reuniaoLoader();

        PaginaDeReuniao paginaDeReuniao = reuniaoMonitor.obterPaginaDeReuniaoInicializada();

        request.setAttribute("paginaDeReuniao", paginaDeReuniao);

        String mensagemRetorno = reuniaoMonitor.getMensagemStatus();

        System.out.println("msgReturn = " + mensagemRetorno);

        return mensagemRetorno;

    }


}
