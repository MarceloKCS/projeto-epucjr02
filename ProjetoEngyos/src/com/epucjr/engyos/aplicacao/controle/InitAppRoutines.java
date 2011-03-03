/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.controle;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Rogerio
 */
public class InitAppRoutines {

    HttpSession session;

    public InitAppRoutines(HttpSession httpSession) {
        this.session = httpSession;
    }

    public void limparSession(){
        if(!this.session.isNew()){
             this.session.invalidate();
        }
       
    }



}
