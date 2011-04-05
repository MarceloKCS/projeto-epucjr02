/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rogerio
 */
public class ActionBuscaPageLoaderCommand implements Command{

    @Override
    public Object execute(Object... arg) {
       HttpServletRequest request = (HttpServletRequest) arg[0];


        String paginaSelecionada = request.getParameter("pagina_busca");


        return paginaSelecionada;
    }



}
