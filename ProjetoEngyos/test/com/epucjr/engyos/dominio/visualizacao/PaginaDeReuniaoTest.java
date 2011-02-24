package com.epucjr.engyos.dominio.visualizacao;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Projeto Engyos Team
 */
public class PaginaDeReuniaoTest {

    @Test
    public void testarObtencaoDeDataDaPagina(){
        String horario = "19:51:00";
        String data = "19/02/2011";
        String local = "Local da Reuniao";

        PaginaDeReuniao paginaDeReuniao = new PaginaDeReuniao();

        paginaDeReuniao.carregarDadosDaPagina(data, local, horario);

        String diaEsperado = "19";
        String mesEsperado = "02";
        String anoEsperado = "2011";

        assertEquals(diaEsperado, paginaDeReuniao.getDia());
        assertEquals(mesEsperado, paginaDeReuniao.getMes());
        assertEquals(anoEsperado, paginaDeReuniao.getAno());
    }

    @Test
    public void testarObtencaoDeHorarioDaPagina(){
        String horario = "19:51:00";
        String data = "19/02/2011";
        String local = "Local da Reuniao";

        PaginaDeReuniao paginaDeReuniao = new PaginaDeReuniao();

        paginaDeReuniao.carregarDadosDaPagina(data, local, horario);

        String horaEsperada = "19";
        String horEsperada = "51";
        String segundosEsperado = "00";

        assertEquals(horaEsperada, paginaDeReuniao.getHora());
        assertEquals(horEsperada, paginaDeReuniao.getMinuto());
        assertEquals(segundosEsperado, paginaDeReuniao.getSegundo());
    }

//    @Test
//    public void testarObtencaoDeHorarioFinalDaPagina(){
//        String horario = "19:51:00";
//        String horarioFinal = "20:16:00";
//        String data = "19/02/2011";
//        String local = "Local da Reuniao";
//        boolean deviceStatusAtivo = true;
//
//        PaginaDeReuniao paginaDeReuniao = new PaginaDeReuniao();
//
//        paginaDeReuniao.carregarDadosDaPagina(data, local, horario, horarioFinal, deviceStatusAtivo);
//
//        String horaEsperada = "20";
//        String horEsperada = "16";
//
//        assertEquals(horaEsperada, paginaDeReuniao.getHoraFinal());
//        assertEquals(horEsperada, paginaDeReuniao.getMinutoFinal());
//    }

//    @Test
//    public void testarObtencaoDeMsgDeviceDaPagina(){
//        String horario = "19:51:00";
//        String horarioFinal = "20:16:00";
//        String data = "19/02/2011";
//        String local = "Local da Reuniao";
//        boolean deviceStatusAtivo = true;
//
//        PaginaDeReuniao paginaDeReuniao = new PaginaDeReuniao();
//
//        paginaDeReuniao.carregarDadosDaPagina(data, local, horario, horarioFinal, deviceStatusAtivo);
//
//
//        assertTrue(paginaDeReuniao.isStatusDispositivoBiometricoAtivo());
//    }
}
