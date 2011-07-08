package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.tecnologia.dao.ObreiroDAO;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Rogerio
 */
public class ActionObreiroRemoverCommand implements Command{
    private static org.apache.log4j.Logger log = Logger.getLogger(ActionReuniaoRemoverCommand.class);

    @Override
    public Object execute(Object... arg) {
         HttpServletRequest request = (HttpServletRequest) arg[0];
         ObreiroDAO obreiroDAO = new ObreiroDAO();
         String mensagemRetorno = "";
         String cpfObreiro = request.getParameter("cpfObreiro");
         log.debug("cpfObreiro: " + cpfObreiro );

         obreiroDAO.delete(cpfObreiro);

         mensagemRetorno = obreiroDAO.getMensagemStatus();

         obreiroDAO.fecharEntityManager();

         return mensagemRetorno;
    }



}
