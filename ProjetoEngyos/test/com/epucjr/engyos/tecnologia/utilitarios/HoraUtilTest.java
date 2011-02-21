/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.tecnologia.utilitarios;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Rogerio
 */
public class HoraUtilTest {


    @Test
    public void obterTempoCorrente(){
        String horaCorrente = HoraUtil.obterHoraCorrente();        

        System.out.println(horaCorrente);
        
       if(horaCorrente != null){
             assertEquals(true, true);
        }
        else{
             assertEquals(true, false);
        }
    }

    @Test
    public void testarHoraCorrente(){
        String horaCorrente = HoraUtil.obterTempoCorrente();

        System.out.println(horaCorrente);

         if(horaCorrente != null){
             assertEquals(true, true);
        }
        else{
             assertEquals(true, false);
        }
    }


//    public void testaScheduleTimer(){
//
//        System.out.println("About to schedule task.");
//        HoraUtil.ReminderBeep(60);
//        System.out.println("Task scheduled.");
//        assertEquals(true, true);
//    }

}

