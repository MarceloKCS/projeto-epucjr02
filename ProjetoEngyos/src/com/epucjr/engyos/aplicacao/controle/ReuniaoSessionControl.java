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
public class ReuniaoSessionControl extends MainSessionControl{
    
    private final String REUNIAO_DEFAULT_FIELD_VALUE = "reuniao";
    private final String REUNIAO_DEFAULT_FIELD_ID = "id_reuniao";

    public ReuniaoSessionControl(HttpSession session) {
        super(session);
    }    

    public void criarSessaoDeReuniao(){
        if(!this.verificaAtributoExistente(REUNIAO_DEFAULT_FIELD_VALUE)){
            this.definirSessionStatusAtivaInativa(REUNIAO_DEFAULT_FIELD_VALUE, false);
        }
    }

    public void abrirSessaoReuniao(){
        if(!this.verificarSessaoReuniaoAberta()){
            if(this.verificaAtributoExistente(REUNIAO_DEFAULT_FIELD_VALUE)){
                this.session.setAttribute(REUNIAO_DEFAULT_FIELD_VALUE, true);
            }
            else{
                this.definirSessionStatusAtivaInativa(REUNIAO_DEFAULT_FIELD_VALUE, true);
            }
        }
    }

     public void fecharSessaoReuniao(){
        if(!this.verificarSessaoReuniaoAberta()){
            if(this.verificaAtributoExistente(REUNIAO_DEFAULT_FIELD_VALUE)){
                this.session.setAttribute(REUNIAO_DEFAULT_FIELD_VALUE, false);
            }
            else{
                this.definirSessionStatusAtivaInativa(REUNIAO_DEFAULT_FIELD_VALUE, false);
            }
        }
    }

     /**
      * Sobrecarga de método de verificarSessionStatusAtiva() para
      * auxiliar no controle de sessão de reunião
      *
      * //TODO: Refatorar para melhorar de modo a não precisar deste e
      * revisar o método verificarSessaoReuniaoAberta()
      *
      * @param nomeDaSessao
      * @return
      */
     private boolean verificarSessionStatusAtiva(String nomeDaSessao) {
        boolean flagCampo = (Boolean) session.getAttribute(nomeDaSessao);

        return flagCampo;
    }

    public boolean verificarSessaoReuniaoAberta(){
        if(this.verificaAtributoExistente(this.REUNIAO_DEFAULT_FIELD_VALUE)){
            return this.verificarSessionStatusAtiva(this.REUNIAO_DEFAULT_FIELD_VALUE);
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
