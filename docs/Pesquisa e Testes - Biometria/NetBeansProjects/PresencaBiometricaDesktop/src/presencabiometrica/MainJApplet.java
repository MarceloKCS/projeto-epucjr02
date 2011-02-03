/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presencabiometrica;

import DataManager.ServletDataManager;
import javax.swing.JApplet;

/**
 *
 * @author Diego
 */
public class MainJApplet {

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    public static void main(String[] args) {
        Controler controler = new Controler(new ServletDataManager(), null);

//        new Controler(new InterfaceDataManager() {
//
//            public String[] getReunioes(String data1, String data0) {
//                return null;
//            }
//
//            public long getReuniao(String data) {
//                return -1;
//            }
//
//            public String marcarPresenca(long idReuniao, String senha) {
//                return null;
//            }
//
//            public String marcarPresencaDigital(long idReuniao, String digital) {
//                return null;
//            }
//        }, getParameter("reuniao"));
    }

    // TODO overwrite start(), stop() and destroy() methods

}
