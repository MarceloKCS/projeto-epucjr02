package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.tecnologia.dao.CongregacaoDAO;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Rogerio
 */
public class ActionCongregacaoRemoverCommand implements Command{
     private static org.apache.log4j.Logger log = Logger.getLogger(ActionReuniaoRemoverCommand.class);

    @Override
    public Object execute(Object... arg) {
        HttpServletRequest request = (HttpServletRequest) arg[0];
        CongregacaoDAO congregacaoDAO = new CongregacaoDAO();
        String mensagemRetorno = "";
        String idCongregacaoInput = request.getParameter("idCongregacao");
        log.debug("idCongregacao: " + idCongregacaoInput );

        long idCongregacao = 0;
        if(idCongregacaoInput != null && !idCongregacaoInput.equals("")){
            idCongregacao = Long.parseLong(idCongregacaoInput);
        }

        congregacaoDAO.delete(idCongregacao);

        mensagemRetorno = congregacaoDAO.getMensagemStatus();

        congregacaoDAO.fecharEntityManager();

         return mensagemRetorno;
    }




}
