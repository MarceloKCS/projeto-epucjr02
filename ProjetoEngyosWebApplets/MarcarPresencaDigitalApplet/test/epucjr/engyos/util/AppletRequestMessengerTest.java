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

        appletRequestMessenger.setParameterGET("nome", "José da Silva");
        appletRequestMessenger.setParameterGET("CPF", "4546545465");

        String valorEsperado = "nome=José da Silva&CPF=4546545465";

        assertEquals(valorEsperado, appletRequestMessenger.obterRequestMessageParameters());
    }

    @Test
    public void decodificarMensagemRecebida(){
        AppletClientMessenger appletClientMessenger = new AppletClientMessenger();
        String messageQuery = "resposta=Reuniao Cancelada";
        appletClientMessenger.setMessageQuery(messageQuery);

        String valorEsperado = "Reuniao Cancelada";

        assertEquals(valorEsperado, appletClientMessenger.obterValorCampo("resposta"));
    }

}
