/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package epucjr.engyos.applet.worker;

import epucjr.engyos.applet.controle.MarcadorDeDigital;
import epucjr.engyos.applet.gui.Informable;
import java.util.List;
import javax.swing.SwingWorker;

/**
 *
 * @author Rogerio
 */
public class AcaoMarcarDigitalWorker extends SwingWorker<String, String>{

    private final Informable informable;
    

    public AcaoMarcarDigitalWorker(Informable informable) {
        this.informable = informable;
        
    }



    @Override
    protected String doInBackground() throws Exception {
        MarcadorDeDigital marcadorDeDigital = new MarcadorDeDigital();
        String finalMessage = "";
        try {
            do {
                marcadorDeDigital.capturarArmazenarDigitalServlet();

                //this.labelAviso.setText(marcadorDeDigital.getMensagemStatus());
                publish(marcadorDeDigital.getMenasgemServerOperacao());
            } while (marcadorDeDigital.getMensagemStatus().equals("sucesso"));
        } catch (NullPointerException ex) {
            finalMessage = "Leitura Encerrada";
            return finalMessage;
        }

        return marcadorDeDigital.getMensagemStatus() + "&acao=encerrar";
    }

//    @Override
//    protected void done() {
//        firePropertyChange(this, this, this);
//    }
    @Override
    protected void process(List<String> chunks) {
        for (String message : chunks) {
            informable.messageChanged(message);
        }
  }
}
