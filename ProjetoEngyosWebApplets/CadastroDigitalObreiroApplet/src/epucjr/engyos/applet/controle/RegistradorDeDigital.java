/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package epucjr.engyos.applet.controle;

import com.nitgen.SDK.BSP.NBioBSPJNI;
import epucjr.engyos.comunicacao.ServletComunication;
import epucjr.engyos.devicemanager.ControleBioDeviceHardware;

/**
 *
 * @author Rogerio
 */
public class RegistradorDeDigital {

    private String mensagemStatus;
    private boolean operacaoExecutada;

    public RegistradorDeDigital() {
        this.mensagemStatus = "";
        this.operacaoExecutada = false;
    }

    public Object capturarImpressaoDigital(){
        ControleBioDeviceHardware controleBioDeviceHardware = new ControleBioDeviceHardware();
        controleBioDeviceHardware.inicializaHardware();
        controleBioDeviceHardware.abrirDispositivo();
        Object digital = null;
        if(controleBioDeviceHardware.isHardwareInicializado()){
            digital = controleBioDeviceHardware.capturarDigitalModoString();
            // String valorBin = controleBioDeviceHardware.capturarDigitalModoBinario();
            controleBioDeviceHardware.fecharDispositivo();
        }
        this.setMensagemStatus(controleBioDeviceHardware.getMensagemStatus());
        this.setOperacaoExecutada(controleBioDeviceHardware.isOperacaoExecutada());

      return digital;
    }

    public void capturarArmazenarDigitalServlet(){
        ServletComunication servletComunication = new ServletComunication();
        NBioBSPJNI.FIR_TEXTENCODE digitalCapturadaModoText = (NBioBSPJNI.FIR_TEXTENCODE) this.capturarImpressaoDigital();

        if(this.isOperacaoExecutada()){
            String digitalCapturadaText = digitalCapturadaModoText.TextFIR;
            String resposta = servletComunication.marcarDigitalString(digitalCapturadaText);
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





}
