package com.epucjr.engyos.tecnologia.utilitarios;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 *
 * @author Projeto Engyos
 */
public class AppletRequestMessageProcessorTest {

    @Test
    public void processarMensagemRecebidaPeloApplet(){
        String mensagem = "nome=Jos� da Silva&CPF=4546545465";

        AppletServerMessenger appletRequestMessageProcessor = new AppletServerMessenger(mensagem);

        String campoNome = appletRequestMessageProcessor.obterValorCampo("nome");
        String cpf = appletRequestMessageProcessor.obterValorCampo("CPF");

        assertEquals("Jos� da Silva", campoNome);

        assertEquals("4546545465", cpf);
    }

}
