/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package epucjr.engyos.applet;

import epucjr.engyos.applet.controle.RegistradorDeDigital;
import javax.swing.JApplet;
import javax.swing.JOptionPane;

/**
 *
 * @author Rogerio
 */
public class CadastroDigitalApplet extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    @Override
    public void init() {
        System.out.println("Applet Initialized");
    }

    public void triggerDigitalInsert() {
        RegistradorDeDigital registradorDeDigital = new RegistradorDeDigital();
        registradorDeDigital.capturarArmazenarDigitalServlet();

        JOptionPane.showMessageDialog(null, registradorDeDigital.getMensagemStatus());

    }

    // TODO overwrite start(), stop() and destroy() methods

}
