/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.tecnologia.dao.AdministradorDAO;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
/**
 *
 * @author Rogerio
 */
public class ActionAdministradorRemoverCommand implements Command{
    private static org.apache.log4j.Logger log = Logger.getLogger(ActionAdministradorRegisterCommand.class);

    @Override
    public Object execute(Object... arg) {
        HttpServletRequest request = (HttpServletRequest) arg[0];
        AdministradorDAO administradorDAO = new AdministradorDAO();
        String mensagemRetorno = "";
        String cpfAdministrador = request.getParameter("cpfAdministrador");
        log.debug("cpfAdministrador: " + cpfAdministrador );

        administradorDAO.delete(cpfAdministrador);

        mensagemRetorno = administradorDAO.getMensagemStatus();

        return mensagemRetorno;
    }



}
