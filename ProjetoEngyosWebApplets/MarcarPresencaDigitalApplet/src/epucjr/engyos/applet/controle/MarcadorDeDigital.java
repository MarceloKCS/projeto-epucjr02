/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package epucjr.engyos.applet.controle;

import epucjr.engyos.comunicacao.ServletComunication;
import epucjr.engyos.devicemanager.ControleBioDeviceHardware;
import java.net.MalformedURLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Rogerio
 */
public class MarcadorDeDigital{

    private String mensagemStatus;
    private boolean operacaoExecutada;
    private boolean operacaoThreadEnd;

    public MarcadorDeDigital() {
        this.mensagemStatus = "";
        this.operacaoExecutada = false;
        this.operacaoThreadEnd = false;
    }

  
    public String capturarImpressaoDigital(){
        ControleBioDeviceHardware controleBioDeviceHardware = new ControleBioDeviceHardware();
        controleBioDeviceHardware.inicializaHardware();
        controleBioDeviceHardware.abrirDispositivo();
        String digital = controleBioDeviceHardware.capturarDigitalModoTextString();
        controleBioDeviceHardware.fecharDispositivo();
        
        this.setMensagemStatus(controleBioDeviceHardware.getMensagemStatus());
        this.setOperacaoExecutada(controleBioDeviceHardware.isOperacaoExecutada());

      return digital;
    }

    public synchronized void capturarArmazenarDigitalServlet(){
        ServletComunication servletComunication = null;
        try {
            servletComunication = new ServletComunication();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            this.setOperacaoExecutada(false);
            this.setMensagemStatus("ERRO:101:: Erro de sintaxe na url...");
        }
       
        String impressaoDigital = this.capturarImpressaoDigital();
        
        if(this.isOperacaoExecutada() && servletComunication != null){
            String resposta = servletComunication.realizarRequestServlet("marcar_presenca", impressaoDigital);
            this.setMensagemStatus(resposta);
            System.out.println("resposta = " + this.getMensagemStatus());
        }
    }


    public String getMensagemStatus() {
        return mensagemStatus;
    }

    public void setMensagemStatus(String mensagemStatus) {
        this.mensagemStatus = mensagemStatus;
    }

    public boolean isOperacaoExecutada() {
        return operacaoExecutada;
    }

    public void setOperacaoExecutada(boolean operacaoExecutada) {
        this.operacaoExecutada = operacaoExecutada;
    }

    public boolean isOperacaoThreadEnd() {
        return operacaoThreadEnd;
    }

    public void setOperacaoThreadEnd(boolean operacaoThreadEnd) {
        this.operacaoThreadEnd = operacaoThreadEnd;
    }


}
