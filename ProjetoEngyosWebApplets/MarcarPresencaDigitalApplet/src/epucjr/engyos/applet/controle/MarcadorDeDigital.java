/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package epucjr.engyos.applet.controle;

import epucjr.engyos.comunicacao.ServletComunication;
import epucjr.engyos.devicemanager.ControleBioDeviceHardware;
import epucjr.engyos.util.AppletClientMessenger;
import epucjr.engyos.util.ListUtilTokenizer;
import java.net.MalformedURLException;

/**
 *
 * @author Rogerio
 */
public class MarcadorDeDigital{

    private String mensagemStatus;
    private String menasgemServerOperacao;
    private boolean operacaoExecutada;
    private boolean operacaoThreadEnd;

    public MarcadorDeDigital() {
        this.mensagemStatus = "";
        this.menasgemServerOperacao = "";
        this.operacaoExecutada = false;
        this.operacaoThreadEnd = false;
    }

  
    public String capturarImpressaoDigital(){
        ControleBioDeviceHardware controleBioDeviceHardware = new ControleBioDeviceHardware();
        controleBioDeviceHardware.inicializaHardware();
        controleBioDeviceHardware.abrirDispositivo();
        String digital = controleBioDeviceHardware.capturarDigitalModoTextString();
        //controleBioDeviceHardware.fecharDispositivo();
        controleBioDeviceHardware.encerrarHardware();
        
        this.setMensagemStatus(controleBioDeviceHardware.getMensagemStatus());
        this.setOperacaoExecutada(controleBioDeviceHardware.isOperacaoExecutada());

      return digital;
    }

    public synchronized void capturarArmazenarDigitalServlet(String idReuniao){
        ServletComunication servletComunication = null;
        try {
            servletComunication = new ServletComunication();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            this.setOperacaoExecutada(false);
            this.setMensagemStatus("ERRO:101:: Erro de sintaxe na url...");
        }
       //Captura a impressão digital pelo dispiositivo
        String impressaoDigital = this.capturarImpressaoDigital();

        

        //Verifica o sucesso da captura da digital pelo dispositivo e da inicialização do Comunicador do servlet
        if (this.isOperacaoExecutada() && servletComunication != null) {

            //Monta a mensagem em um modelo que será reconhecido no servlet
            AppletClientMessenger appletRequestMessenger = new AppletClientMessenger();
            appletRequestMessenger.setParameterGET("idReuniao", idReuniao);
            appletRequestMessenger.setParameterGET("digital", impressaoDigital);

            //Realiza a tentativa de enviar a mensagem montada, com a requisição de marcar presença
            String resposta = servletComunication.realizarRequestServlet("marcar_presenca", appletRequestMessenger.obterRequestMessageParameters());

            //A resposta recebida do servidor pelo servlet vem separada em status operacao e mensagem, obtendo estes campos
            String mensagemStatus = ListUtilTokenizer.obterArrayString(resposta)[0];
            String mensagemServerOperacao = ListUtilTokenizer.obterArrayString(resposta)[1];

            this.setMensagemStatus(mensagemStatus);
            this.setMenasgemServerOperacao(mensagemServerOperacao);
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

    public String getMenasgemServerOperacao() {
        return menasgemServerOperacao;
    }

    public void setMenasgemServerOperacao(String menasgemServerOperacao) {
        this.menasgemServerOperacao = menasgemServerOperacao;
    }

    


}
