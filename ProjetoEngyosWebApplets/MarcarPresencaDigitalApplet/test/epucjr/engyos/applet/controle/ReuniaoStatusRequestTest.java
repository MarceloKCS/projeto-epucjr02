/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package epucjr.engyos.applet.controle;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Rogerio
 */
public class ReuniaoStatusRequestTest {

    @Test
    public void obterRespostaDoServlet(){
        ReuniaoStatusRequest reuniaoStatusRequest = new ReuniaoStatusRequest();

        String resposta = reuniaoStatusRequest.obterStatusDaSessaoDeReuniao();

        System.out.println("resposta = " + resposta);

        assertEquals("inativa", resposta);
        
    }

}
