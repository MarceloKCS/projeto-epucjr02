/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.tecnologia.dao;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.utilitarios.DateTimeUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Rogerio
 */
public class ReuniaoDAOTest {
    private ReuniaoDAO reuniaoDAO = new ReuniaoDAO();

    @Test
    public void findByTimeIntervalTest(){
        List<Reuniao> listaDeReuniao = null;
        long tempoInicio = 1304218800;
        long tempoFinal = 1311994800;
        tempoInicio = DateTimeUtils.converterDateTimeToMilissegundos("30/05/2011", "00:00:00");
        tempoFinal = DateTimeUtils.converterDateTimeToMilissegundos("20/07/2011", "00:00:00");
        System.out.println("tempoInicio = " + tempoInicio);
        System.out.println("tempoFinal = " + tempoFinal);

        Long valor = 1293760800L * 1000;

        Date data = new Date(valor);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        System.out.println("dia: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("mes: " + calendar.get(Calendar.MONTH));
        System.out.println("ano: " + calendar.get(Calendar.YEAR));


        listaDeReuniao = reuniaoDAO.findByTimeInterval(tempoInicio, tempoFinal);
        System.out.println("TamanhoLista: " + listaDeReuniao.size());
        for(Reuniao reuniao : listaDeReuniao){
            System.out.println("data: " + reuniao.getData());
            System.out.println("local: " + reuniao.getLocal());
        }

        assertNotNull(listaDeReuniao);
    }

}
