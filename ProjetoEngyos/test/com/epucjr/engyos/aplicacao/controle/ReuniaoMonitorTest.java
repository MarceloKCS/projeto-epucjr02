package com.epucjr.engyos.aplicacao.controle;

import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.IReuniao;
import com.epucjr.engyos.dominio.modelo.Identificacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Projeto Engyos Team
 */
public class ReuniaoMonitorTest {

    private IReuniao reuniao;
    private IReuniaoMonitor reuniaoMonitor;
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

        reuniao = new Reuniao("Na minha casa", "17/02/2010", "18:00");

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
    public void iniciarUmaReuniaoAgendadaPeloUsuario() {
        this.inicializarListaDePresenca();

        reuniaoMonitor = new ReuniaoMonitor();

        reuniaoMonitor.inicializaReuniao(reuniao);


        assertTrue(reuniaoMonitor.isOperacaoExecutada());
        
    }
}
