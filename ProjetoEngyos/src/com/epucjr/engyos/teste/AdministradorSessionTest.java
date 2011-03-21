/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.teste;

import com.epucjr.engyos.dominio.modelo.Administrador;
import com.epucjr.engyos.dominio.modelo.SessionStatus;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

/**
 *
 * @author Rogerio
 */
public class AdministradorSessionTest {

    public static void main(String[] args){
        AdministradorSessionTest.inserdAdmin();
    }

    public static void inserdAdmin(){
        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
        Administrador administrador = new Administrador("Michel", " Alves Silva", "123", "msilva@epucjr.com.br", "nights");
        SessionStatus sessionStatus = new SessionStatus();
        sessionStatus.definirSessaoStatus(SessionStatus.SECAO_INATIVA);
        sessionStatus.setCURRENT_SESSION_ID("");
        administrador.setSessionStatus(sessionStatus);
        dataAccessObjectManager.persistirObjeto(administrador);

        if(dataAccessObjectManager.isOperacaoEfetuada()){

            System.out.println("MSG_STATUS : " + dataAccessObjectManager.getMensagemStatus());
        }
        else{
            System.out.println("MSG_STATUS_ERRO : " + dataAccessObjectManager.getMensagemStatus());
        }
    }

    public static void obterAdministradorTestSession(){
        String id_admin_cpf = "123";
        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
        Administrador administrador = dataAccessObjectManager.obterAdministrador(id_admin_cpf);
        SessionStatus sessionStatus = administrador.getSessionStatus();
        sessionStatus.definirSessaoStatus(SessionStatus.SECAO_INATIVA);
        sessionStatus.setCURRENT_SESSION_ID("");

        dataAccessObjectManager.mergeDataObjeto(administrador);

        if(dataAccessObjectManager.isOperacaoEfetuada()){

            System.out.println("MSG_STATUS : " + dataAccessObjectManager.getMensagemStatus());
        }
        else{
            System.out.println("MSG_STATUS_ERRO : " + dataAccessObjectManager.getMensagemStatus());
        }
    }

    public static void obterSessionPeloId(){
        long id_session = 0;
        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
        SessionStatus sessionStatus = dataAccessObjectManager.obterSessionStatus(id_session);
        
//        sessionStatus.definirSessaoStatus(SessionStatus.SECAO_ATIVA);
//        sessionStatus.setCURRENT_SESSION_ID(1235);
//        dataAccessObjectManager.mergeDataObjeto(sessionStatus);

        if(dataAccessObjectManager.isOperacaoEfetuada()){
            System.out.println("MSG_STATUS : " + dataAccessObjectManager.getMensagemStatus());

            System.out.println("ID_SESSION_STATUS : " + sessionStatus.getIdSessionStatus());
            System.out.println("SESSION_ATIVA : " + sessionStatus.isSessaoAtiva());
             System.out.println("CURRENT_SESSION_ID : " + sessionStatus.getCURRENT_SESSION_ID());
            System.out.println("__________________________________________________");
        }
        else{
            System.out.println("MSG_STATUS_ERRO : " + dataAccessObjectManager.getMensagemStatus());
        }
    }

}
