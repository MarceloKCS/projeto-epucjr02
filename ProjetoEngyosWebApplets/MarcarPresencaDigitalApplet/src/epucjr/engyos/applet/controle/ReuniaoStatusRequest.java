/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package epucjr.engyos.applet.controle;

import epucjr.engyos.comunicacao.ServletComunication;
import java.net.MalformedURLException;

/**
 *
 * @author Rogerio
 */
public class ReuniaoStatusRequest {

    private String mensagemStatus;
    private boolean operacaoExecutada;

    public ReuniaoStatusRequest() {
        this.mensagemStatus = "";
        this.operacaoExecutada = false;
    }

    public String obterStatusDaSessaoDeReuniao(){
        ServletComunication servletComunication = null;
        try {
            servletComunication = new ServletComunication();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            this.setOperacaoExecutada(false);
            this.setMensagemStatus("ERRO:101:: Erro de sintaxe na url...");
            return this.getMensagemStatus();
        }

        String resposta = servletComunication.realizarRequestServlet("verificar_reuniao_aberta");

        return resposta;
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
