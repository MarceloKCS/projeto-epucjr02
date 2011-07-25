/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package epucjr.engyos.handler;

/**
 *
 * @author Rogerio
 */
public class CadastroDigitalExceptionHandler {

    private String mensagem;

    public CadastroDigitalExceptionHandler() {
        this.mensagem = null;
    }

    public void lidarComExcecaoLancadaPelaApplet(Exception exception){

    }

    public void lidarComExcecaoLancadaPelaAplicacao(Exception exception){
        if(exception instanceof NullPointerException){
            this.mensagem = exception.getMessage();
            return;
        }
        else{
            this.mensagem = exception.getMessage();
        }
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }


    

}
