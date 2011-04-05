/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.teste;

import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.dominio.modelo.ReuniaoSessionStatus;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import com.epucjr.engyos.tecnologia.utilitarios.DateTimeUtils;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Rogerio
 */
public class ReuniaoSessionTest {

    public static void main(String[] args){
        recuperaManipulaSessaoReuniao();
    }

    public static void reuniaoTimestapPersistTest(){

        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();

        //Dados de simulação de seção...
        String idSession = "28";

        //Pega o tempo corrente
        Date date = new Date();       

        Reuniao reuniao = new Reuniao("Rua do tamandua, 123", DateTimeUtils.obterDataCorrenteBr(), DateTimeUtils.obterTempoCorrente());

        ReuniaoSessionStatus reuniaoSessionStatus = new ReuniaoSessionStatus();
        reuniaoSessionStatus.setCURRENT_SESSION_ID(idSession);
        reuniaoSessionStatus.setSESSION_START_TIME(date);

        reuniao.setReuniaoSessionStatus(reuniaoSessionStatus);

        dataAccessObjectManager.persistirObjeto(reuniao);

        if(dataAccessObjectManager.isOperacaoEfetuada()){
            System.out.println("STATUS SUCESSO : " + dataAccessObjectManager.getMensagemStatus());
        }
        else{
             System.out.println("STATUS FRACASSO : " + dataAccessObjectManager.getMensagemStatus());
        }
    }

    public static void recuperaManipulaSessaoReuniao(){
        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
        long idReuniao = 8;
        Reuniao reuniao = dataAccessObjectManager.obterReuniao(idReuniao);

        Date date = reuniao.getReuniaoSessionStatus().getSESSION_START_TIME();

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        System.out.println("DIA : " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("MES : " + calendar.get(Calendar.MONTH));
        System.out.println("ANO : " + calendar.get(Calendar.YEAR));
        System.out.println("HORA : " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("MINUTO : " + calendar.get(Calendar.MINUTE));
        System.out.println("SECOND : " + calendar.get(Calendar.SECOND));
    }

}
