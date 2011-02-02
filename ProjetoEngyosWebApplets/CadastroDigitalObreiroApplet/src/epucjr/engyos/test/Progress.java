/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package epucjr.engyos.test;

import java.io.Serializable;

/**
 *
 * @author Rogerio
 */
public class Progress implements Serializable{

    private String nomeTitle;

    public Progress(String msg) {
        this.nomeTitle = msg;
    }

    public String getNomeTitle() {
        return nomeTitle;
    }

    public void setNomeTitle(String nomeTitle) {
        this.nomeTitle = nomeTitle;
    }

    


}
