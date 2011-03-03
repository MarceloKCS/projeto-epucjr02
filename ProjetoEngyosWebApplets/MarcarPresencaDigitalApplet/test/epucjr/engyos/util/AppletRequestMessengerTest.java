package epucjr.engyos.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Projeto Engyos
 */
public class AppletRequestMessengerTest {

    @Test
    public void verificarMensagemMontadaFormatoCorreto(){

        AppletClientMessenger appletRequestMessenger = new AppletClientMessenger();

        appletRequestMessenger.setParameterGET("nome", "Jos� da Silva");
        appletRequestMessenger.setParameterGET("CPF", "4546545465");

        String valorEsperado = "nome=Jos� da Silva&CPF=4546545465";

        assertEquals(valorEsperado, appletRequestMessenger.obterRequestMessageParameters());
    }

}
