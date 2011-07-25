/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package epucjr.engyos.applet;

import epucjr.engyos.applet.controle.RegistradorDeDigital;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;
import javax.swing.JOptionPane;
import netscape.javascript.JSObject;

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
        System.out.println("Inserindo digital...");
        RegistradorDeDigital registradorDeDigital = new RegistradorDeDigital();
        registradorDeDigital.capturarArmazenarDigitalServlet();

        this.publicarMensagemNaPagina(registradorDeDigital.getMensagemStatus());
    }

    public void publicarMensagemNaPagina(String mensagem){
        try{
            JSObject jdJSObject = JSObject.getWindow(this);
            // dynamically change HTML in page; write data summary
            jdJSObject.call("emitirAvisoApplet", new Object[]{mensagem});
        }
        catch(netscape.javascript.JSException ex){
            System.out.println("APPLET EXCEPTION204:: " + ex.getMessage());
        }
    }

    // TODO overwrite start(), stop() and destroy() methods

}
