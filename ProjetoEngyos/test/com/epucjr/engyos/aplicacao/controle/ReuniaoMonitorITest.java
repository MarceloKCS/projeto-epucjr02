package com.epucjr.engyos.aplicacao.controle;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Projeto Engyos
 */
public class ReuniaoMonitorITest {


    @Test
    public void marcarPresencaDeObreiroPeloCPFPorMeioDoMonitorComPersistencia() {
        ReuniaoMonitor reuniaoMonitor = new ReuniaoMonitor(3);
        
        reuniaoMonitor.marcarPresencaPeloCPF("88436038339");
        
        String msgStatus = reuniaoMonitor.getMensagemStatus();

        System.out.println("MSG = " + msgStatus);

        assertTrue(reuniaoMonitor.isOperacaoExecutada());
    }
}
