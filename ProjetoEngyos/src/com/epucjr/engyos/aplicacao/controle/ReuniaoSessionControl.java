/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.controle;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Rogerio
 */
public class ReuniaoSessionControl implements SessionControl{

    private HttpSession session;
    private final String REUNIAO_DEFAULT_FIELD_VALUE = "reuniao";
    private final String REUNIAO_DEFAULT_FIELD_ID = "id_reuniao";

    public ReuniaoSessionControl(HttpSession session) {
        this.session = session;
    }

    @Override
    public void criarFlagSessionStatus(String nomeDaSessao, boolean status) {
       if(!verificaAtributoExistente(nomeDaSessao)){
           this.session.setAttribute(nomeDaSessao, status);
       }
    }

    @Override
    public boolean verificaAtributoExistente(String nomeDoCampo) {
         if(this.session.getAttribute(nomeDoCampo) != null && !this.session.getAttribute(nomeDoCampo).equals("")){
            return true;
        }
         else{
             return false;
         }
    }

    @Override
    public void definirAtributoNaSessao(String nomeDoCampo, String valorDoCampo) {
        if(!this.verificaAtributoExistente(nomeDoCampo)){
            this.session.setAttribute(valorDoCampo, valorDoCampo);
        }
    }

    @Override
    public void encerrarSessao(String nomeDaSessao) {
        if(this.verificaAtributoExistente(nomeDaSessao)){
            this.session.removeAttribute(nomeDaSessao);
        }
    }

    @Override
    public boolean isSessaoFlagAtiva(String nomeDaSessao) {
        if(this.verificaAtributoExistente(nomeDaSessao)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean obterFlagSessionStatus(String nomeDaSessao) {
        boolean flagCampo = (Boolean) session.getAttribute(nomeDaSessao);

        return flagCampo;
    }



    @Override
    public void matarTodasSessoes() {
        this.session.invalidate();
    }

    @Override
    public Object obterAtributoDaSessao(String nomeDoCampo) {
        if(this.verificaAtributoExistente(nomeDoCampo)){
            return session.getAttribute(nomeDoCampo);
        }
        else{
            return null;
        }
    }

    @Override
    public boolean obterValidadeSessao(String nomeDaSessao) {

       return  this.isSessaoFlagAtiva(nomeDaSessao);

    }

    public void criarSessaoDeReuniao(){
        if(!this.verificaAtributoExistente(REUNIAO_DEFAULT_FIELD_VALUE)){
            this.criarFlagSessionStatus(REUNIAO_DEFAULT_FIELD_VALUE, false);
        }
    }

    public void abrirSessaoReuniao(){
        if(!this.verificarSessaoReuniaoAberta()){
            if(this.verificaAtributoExistente(REUNIAO_DEFAULT_FIELD_VALUE)){
                this.session.setAttribute(REUNIAO_DEFAULT_FIELD_VALUE, true);
            }
            else{
                this.criarFlagSessionStatus(REUNIAO_DEFAULT_FIELD_VALUE, true);
            }
        }
    }

     public void fecharSessaoReuniao(){
        if(!this.verificarSessaoReuniaoAberta()){
            if(this.verificaAtributoExistente(REUNIAO_DEFAULT_FIELD_VALUE)){
                this.session.setAttribute(REUNIAO_DEFAULT_FIELD_VALUE, false);
            }
            else{
                this.criarFlagSessionStatus(REUNIAO_DEFAULT_FIELD_VALUE, false);
            }
        }
    }

    public boolean verificarSessaoReuniaoAberta(){
        if(this.verificaAtributoExistente(this.REUNIAO_DEFAULT_FIELD_VALUE)){
            return this.obterFlagSessionStatus(this.REUNIAO_DEFAULT_FIELD_VALUE);
        }
        else{
            return false;
        }
    }

    public void armazenaIdReuniaoCorrente(String idReuniao){
        if(!this.verificaAtributoExistente(this.REUNIAO_DEFAULT_FIELD_ID)){
            this.definirAtributoNaSessao(this.REUNIAO_DEFAULT_FIELD_ID, idReuniao);
        }
    }

    public String obterIdReuniaoCorrente(){
        if(this.verificaAtributoExistente(REUNIAO_DEFAULT_FIELD_ID)){
            return this.obterAtributoDaSessao(REUNIAO_DEFAULT_FIELD_ID).toString();
        }
        else{
            return "";
        }
    }

}
