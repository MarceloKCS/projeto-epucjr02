/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.dominio.modelo;

import java.util.List;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
/**
 *
 * @author Rogerio
 */
@RunWith(JMock.class)
public class ReuniaoTest {

    //Uso do JMock em estudo
    Mockery context = new JUnit4Mockery();
    private IReuniao reuniao;
    private Congregacao congregacao;
    private Congregacao congregacao2;
    private Congregacao congregacao3;
    private Obreiro obreiro1;
    private Identificacao identificacao1;
    private Obreiro obreiro2;
    private Identificacao identificacao2;
    private Obreiro obreiro3;
    private Identificacao identificacao3;
    private Obreiro obreiro4;
    private Identificacao identificacao4;
    private Obreiro obreiro5;
    private Identificacao identificacao5;
    private Obreiro obreiro6;
    private Identificacao identificacao6;

    @Before
    public void Inicializar(){
        //Tres congregações participantes
        congregacao = new Congregacao("Congragacao de Santo Amaro", "Avenida Santo Amaro");
        congregacao2 = new Congregacao("Congregacao de Taboão", "Taboão da Serra");
        congregacao3 = new Congregacao("Congregacao do Socorro", "Largo do Socorro");
       
        //6 Obreiros
        identificacao1 = new Identificacao("4654asd54as44f4a5sfg45as4fg54ag54adg", "alaska");
        obreiro1 = new Obreiro("Carlos Neves", "Publicitário", "55744297734", congregacao, identificacao1);

        identificacao2 = new Identificacao("as4df65as4f54sa65f4as654f65as4f65as465f", "alaska");
        obreiro2 = new Obreiro("Sayuri", "Moda", "44149644829", congregacao2, identificacao1);


        identificacao3 = new Identificacao("as4f65s4f654a6g4agh46465j4,l65", "alaska");
        obreiro3 = new Obreiro("Michel Silva", "Programador", "69857664040", congregacao3, identificacao3);


        identificacao4 = new Identificacao("v1awr4984894f65d1sg64ra98g4w6h4t84j85l65", "alaska");
        obreiro4 = new Obreiro("Erick dos Reis", "Faxineiro", "32455558657", congregacao, identificacao4);

        identificacao5 = new Identificacao("6d1231bh9rtu9835748g4s65v1t5t98qe4651g", "alaska");
        obreiro5 = new Obreiro("Calebe Santos", "Ti Manager", "71146042205", congregacao2, identificacao5);

        identificacao6 = new Identificacao("b1h9ar41b98j749841bht4jh98e45et1nq9qb19t", "alaska");
        obreiro6 = new Obreiro("CASAMA", "MIRAI", "51247712320", congregacao3, identificacao6);

        reuniao = new Reuniao("Na minha casa", "17/02/2010", "18:00", 1297972800);
        
    }

    private void inicializarListaDePresenca(){
        reuniao.adicionarObreiroNaListaDePresenca(obreiro1);
        reuniao.adicionarObreiroNaListaDePresenca(obreiro2);
        reuniao.adicionarObreiroNaListaDePresenca(obreiro3);
        reuniao.adicionarObreiroNaListaDePresenca(obreiro4);
        reuniao.adicionarObreiroNaListaDePresenca(obreiro5);
        reuniao.adicionarObreiroNaListaDePresenca(obreiro6);
    }

    @Test
    public void marcarPresencaObreiroNaListaDePresenca(){
        //vamos inicializar uma lista de presença para testes
        this.inicializarListaDePresenca();

       //Obreiro marca a presenca com o seguinte CPF:
       String cPF = "55744297734";

       //Vamos verificar se existe este obreiro na lista de presença
              
       boolean obreiroNalista = reuniao.verificaObreiroNaListaPeloCPF(cPF);
       //Se ele estiver na lista marcamos a sua presença
       if(obreiroNalista){
           reuniao.marcarPresencaDeObreiroNaListaPeloCPF(cPF);
       }

       boolean isObreiroPresente = reuniao.verificarObreiroEstevePresenteNaReuniao(cPF);

       assertTrue(isObreiroPresente);
       
    }

    @Test
    public void varreObreiroNaListaDePresenca(){
        this.inicializarListaDePresenca();

        int qtdObreiros = reuniao.obterQuantidadeTotalDeObreirosNaLista();

        assertEquals(6, qtdObreiros);
    }

    @Test
    public void buscaDeObreiroPorAlgoritmoDeBuscaBinaria() {
        this.inicializarListaDePresenca();
        String cPFProcurado = "55744297734";

        List<PresencaObreiro> listPresencaObreiro = reuniao.getListaDePresencaObreiro();

        Obreiro obreiro = reuniao.buscarObreiroNaListaDePresenca(listPresencaObreiro, cPFProcurado);

        Obreiro obreiroEsperado = new Obreiro("Carlos Neves", "Publicitário", "55744297734", congregacao, identificacao1);

        assertEquals(obreiroEsperado, obreiro);

    }

    @Test
    public void verificarQuantidadeDeObreirosPresentes(){
        //Inicializa a lista de presença
        this.inicializarListaDePresenca();

        //Pego três obreiros da lista e marco como presente
        String cpf1, cpf2, cpf3;
        cpf1 = "55744297734";
        cpf2 = "32455558657";
        cpf3 = "51247712320";

        reuniao.marcarPresencaDeObreiroNaListaPeloCPF(cpf1);
        reuniao.marcarPresencaDeObreiroNaListaPeloCPF(cpf2);
        reuniao.marcarPresencaDeObreiroNaListaPeloCPF(cpf3);

        int qtdPresentes = reuniao.obterTotalDePresentesNaReunião();

        assertEquals(3, qtdPresentes);
    }

    @Test
    public void obterTempoDeDuracaoDaReuniao(){
        int tempoDeDuracao = reuniao.obterTempoDeDuracaoReuniao();

        boolean tempoContabilizado = false;

        if(tempoDeDuracao > 0){
            tempoContabilizado = true;
        }

        assertTrue(tempoContabilizado);
    }


}
