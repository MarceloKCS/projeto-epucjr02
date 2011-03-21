package com.epucjr.engyos.aplicacao.controle;

import javax.servlet.http.HttpSession;

/**
 *Aqui reside o controle de sessão central da aplicação, com o objetivo de facilitar
 * as configurações de sessão e definir os parâmetros e verificações necessárias
 * nas manipulações de sessão no decorrer de sua utilização
 *
 * @author Projeto Engyos Team
 */
public class MainSessionControl implements SessionControl{

    public HttpSession session;
    private final int SESSION_EXPIRATION_TIME_INSECONDS = -1;

    /**
     * Construtor
     * Define as configurações iniciais de sessão
     *
     * @param session
     */
    public MainSessionControl(HttpSession session) {
       
        this.session = session;
        this.session.setMaxInactiveInterval(SESSION_EXPIRATION_TIME_INSECONDS);

    }

    @Override
    public void definirSessionStatusAtivaInativa(String nomeDaSessao, boolean status) {
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
    public void definirAtributoNaSessao(String nomeDoCampo, Object valorDoCampo) {
        if(!this.verificaAtributoExistente(nomeDoCampo)){
            this.session.setAttribute(nomeDoCampo, valorDoCampo);
        }
    }

    @Override
    public void removerAtributoNaSessao(String nomeDoAtributo) {
        if(this.verificaAtributoExistente(nomeDoAtributo)){
            this.session.removeAttribute(nomeDoAtributo);
        }
    }

    @Override
    public boolean isSessaoAtiva(String nomeDaSessao) {
        if(this.verificaAtributoExistente(nomeDaSessao)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean verificarSessionStatusAtiva() {
        if(this.getSession().isNew()){
            return false;
        }
        else return true;
    }



    @Override
    public void encerrarSessao() {
        if(!this.session.isNew()){
            this.session.invalidate();
            this.session = null;
            System.out.println("SESSION INVALIDATE");
        }
        else{
            System.out.println("WTF!!!!!!!!!!!");
        }
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
    public boolean verificarValidadeDaSessao(String nomeDaSessao) {

       return  this.isSessaoAtiva(nomeDaSessao);

    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    
}
