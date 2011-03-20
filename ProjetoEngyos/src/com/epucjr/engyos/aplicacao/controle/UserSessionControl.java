package com.epucjr.engyos.aplicacao.controle;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Rogerio
 */
public class UserSessionControl extends MainSessionControl{

    public static final String NOME_USUARIO = "NOME_USUARIO";
    public static final String ID_USUARIO = "ID_USUARIO";
   

    public UserSessionControl(HttpSession session) {
        super(session);
    }

    public void criarEDefinirDadosSessaoDeUsuario(String nomeDoUsuario, String cpf){
        this.definirAtributoNaSessao(NOME_USUARIO, nomeDoUsuario);
        this.definirAtributoNaSessao(ID_USUARIO, cpf);        
    }

    public String obterDadoSessaoDoUsuario(String dadoRequisitado){
        return (String) this.obterAtributoDaSessao(dadoRequisitado);
    }

    public static boolean sessaoEstaAtiva(HttpSession httpSession){
        if(httpSession.isNew()){
            return false;
        }
        else{
            return true;
        }
    }    

}
