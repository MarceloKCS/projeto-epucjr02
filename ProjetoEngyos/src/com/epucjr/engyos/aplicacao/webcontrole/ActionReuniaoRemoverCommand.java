package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.tecnologia.dao.ReuniaoDAO;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Rogerio
 */
public class ActionReuniaoRemoverCommand implements Command{
    private static org.apache.log4j.Logger log = Logger.getLogger(ActionReuniaoRemoverCommand.class);

    @Override
    public Object execute(Object... arg) {
        HttpServletRequest request = (HttpServletRequest) arg[0];
        ReuniaoDAO reuniaoDAO = new ReuniaoDAO();
        String mensagemRetorno = "";
        String idReuniaoInput = request.getParameter("idReuniao");
        log.debug("idReuniao: " + idReuniaoInput );
        long idReuniao = 0;
        if(idReuniaoInput != null && !idReuniaoInput.equals("")){
            idReuniao = Long.parseLong(idReuniaoInput);
        }

        reuniaoDAO.delete(idReuniao);

        reuniaoDAO.fecharEntityManager();

        mensagemRetorno = reuniaoDAO.getMensagemStatus();

        return mensagemRetorno;
    }



}
