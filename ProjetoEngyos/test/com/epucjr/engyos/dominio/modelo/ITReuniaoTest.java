package com.epucjr.engyos.dominio.modelo;

import com.epucjr.engyos.tecnologia.ferramentas.ControleBioDeviceHardware;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
/**
 *Teste de Integra��o de reuni�o com o banco de dados
 * Com base no algoritmo de dispositivo de hardware executado pelo teste diferir no bd
 * no momento � impossivel replicar o teste
 * TODO criar o Banco de dados no momento do testes, testar, apagar dados de teste,
 * pois n�o podem ficar persistentes
 *
 * Objetivo: criar um m�dulo de instala��o da aplica��o que s� instale se os testes passarem
 * assegurando a qualidade, ou pelo menos a certeza de estar entregando um produto confi�vel
 *
 * @author Projeto Engyos Team
 */
//TODO usar JMock para testes
@RunWith(JMock.class)
public class ITReuniaoTest {

    //Uso do JMock com JUnity, em estudo
    Mockery context = new JUnit4Mockery();
    DataAccessObjectManager dataAccessObjectManager;
    ControleBioDeviceHardware dispositivoBioDeviceInterface;
    IReuniao reuniao;

    @Before
    public void Inicializar(){
        dataAccessObjectManager = new DataAccessObjectManager();
        long idReuniao = 3;
        reuniao = dataAccessObjectManager.obterReuniao(idReuniao);

        dispositivoBioDeviceInterface = new ControleBioDeviceHardware();
    }

    @Test
    public void verificarQtdObreiroNaListaDePresenca(){

        int qtdObreiros = reuniao.obterQuantidadeTotalDeObreirosNaLista();

        assertEquals(3, qtdObreiros);
    }

    @Test
    public void buscaDeObreiroPorAlgoritmoDeBusca() {

        String impressaoCadastradaProcurada = "AQAAABQAAADkAAAAAQASAAEAWgAAAAAA3AAAAP3z69XeWa03lS9qvZ*Stk5f2L6Wgq73t0KxQbnpJ4FSGRgers2UmkP4vajjG3TCoCruIFcjZYGZ1K2vIq9U7pG5DS3On6ptG7Xbw4oMBSycZTPiiGJpQZvkprTvk*MKdjxV0hoHFaw5BQMwdVnPAnFLxBBb5Aigr06vMLNu*RmXzIDmb6gHXT2jqa3MQ9y9GbUSGzy8t9Wg37ksreklmhvkNTaulsjcZQSm5CW4w5uU6*jJY0/vAtMsQk1*9MUIcdWlnxEHyflRLoDUpBwQ9O4fIhBP91egsMALYo0z56fz";

        Obreiro obreiro = reuniao.buscarObreiroNaListaDePresenca(impressaoCadastradaProcurada);

        Congregacao congregacao = new Congregacao("Congrega��o de Santo Amaro", "Avenida Santo Amaro");
        Identificacao identificacao = new Identificacao(impressaoCadastradaProcurada, "alaska");
        Obreiro obreiroEsperado = new Obreiro("Michel Alves Silva", "Faxineiro e Caf�", "51254418563", congregacao, identificacao);

        assertEquals(obreiroEsperado, obreiro);

    }

    @Test
    public void marcarPresencaDeObreiroPelaDigital(){
        String impressaoCadastradaProcurada = "AQAAABQAAADkAAAAAQASAAEAWgAAAAAA3AAAAP3z69XeWa03lS9qvZ*Stk5f2L6Wgq73t0KxQbnpJ4FSGRgers2UmkP4vajjG3TCoCruIFcjZYGZ1K2vIq9U7pG5DS3On6ptG7Xbw4oMBSycZTPiiGJpQZvkprTvk*MKdjxV0hoHFaw5BQMwdVnPAnFLxBBb5Aigr06vMLNu*RmXzIDmb6gHXT2jqa3MQ9y9GbUSGzy8t9Wg37ksreklmhvkNTaulsjcZQSm5CW4w5uU6*jJY0/vAtMsQk1*9MUIcdWlnxEHyflRLoDUpBwQ9O4fIhBP91egsMALYo0z56fz";
        
       if(reuniao.verificaObreiroNaListaPelaDigital(impressaoCadastradaProcurada)){

            reuniao.marcarPresencaDeObreiroNaListaPelaDigital(impressaoCadastradaProcurada);

        }
        
        boolean obreiroPresente = reuniao.verificarObreiroEstevePresenteNaReuniaoPelaDigital(impressaoCadastradaProcurada);
        
        assertEquals(true, obreiroPresente);
    }

    @Test
    public void persistirPresencaMarcada(){
        String impressaoCadastradaProcurada = "AQAAABQAAADkAAAAAQASAAEAWgAAAAAA3AAAAP3z69XeWa03lS9qvZ*Stk5f2L6Wgq73t0KxQbnpJ4FSGRgers2UmkP4vajjG3TCoCruIFcjZYGZ1K2vIq9U7pG5DS3On6ptG7Xbw4oMBSycZTPiiGJpQZvkprTvk*MKdjxV0hoHFaw5BQMwdVnPAnFLxBBb5Aigr06vMLNu*RmXzIDmb6gHXT2jqa3MQ9y9GbUSGzy8t9Wg37ksreklmhvkNTaulsjcZQSm5CW4w5uU6*jJY0/vAtMsQk1*9MUIcdWlnxEHyflRLoDUpBwQ9O4fIhBP91egsMALYo0z56fz";

       if(reuniao.verificaObreiroNaListaPelaDigital(impressaoCadastradaProcurada)){

            reuniao.marcarPresencaDeObreiroNaListaPelaDigital(impressaoCadastradaProcurada);

        }

        boolean obreiroPresente = reuniao.verificarObreiroEstevePresenteNaReuniaoPelaDigital(impressaoCadastradaProcurada);

        dataAccessObjectManager.mergeDataObjeto(reuniao);

        assertEquals(true, dataAccessObjectManager.isOperacaoEfetuada());
    }


    @Test
    public void verificaObreiroPresenteNaReuniao(){
        String impressaoCadastradaProcurada = "AQAAABQAAADkAAAAAQASAAEAWgAAAAAA3AAAAP3z69XeWa03lS9qvZ*Stk5f2L6Wgq73t0KxQbnpJ4FSGRgers2UmkP4vajjG3TCoCruIFcjZYGZ1K2vIq9U7pG5DS3On6ptG7Xbw4oMBSycZTPiiGJpQZvkprTvk*MKdjxV0hoHFaw5BQMwdVnPAnFLxBBb5Aigr06vMLNu*RmXzIDmb6gHXT2jqa3MQ9y9GbUSGzy8t9Wg37ksreklmhvkNTaulsjcZQSm5CW4w5uU6*jJY0/vAtMsQk1*9MUIcdWlnxEHyflRLoDUpBwQ9O4fIhBP91egsMALYo0z56fz";

        boolean obreiroPresente = reuniao.verificarObreiroEstevePresenteNaReuniaoPelaDigital(impressaoCadastradaProcurada);

        assertEquals(true, obreiroPresente);
    }


    //Executar ap�s marcar presen�a
     @Test
    public void verificarQuantidadeObreirosPresentesNaReuniao(){
        int qtdPresentes = reuniao.obterTotalDePresentesNaReuni�o();

        assertEquals(1, qtdPresentes);
    }

    @Test
    public void desmarcarPresencaDeObreiroPelaDigital() {
        String impressaoCadastradaProcurada = "AQAAABQAAADkAAAAAQASAAEAWgAAAAAA3AAAAP3z69XeWa03lS9qvZ*Stk5f2L6Wgq73t0KxQbnpJ4FSGRgers2UmkP4vajjG3TCoCruIFcjZYGZ1K2vIq9U7pG5DS3On6ptG7Xbw4oMBSycZTPiiGJpQZvkprTvk*MKdjxV0hoHFaw5BQMwdVnPAnFLxBBb5Aigr06vMLNu*RmXzIDmb6gHXT2jqa3MQ9y9GbUSGzy8t9Wg37ksreklmhvkNTaulsjcZQSm5CW4w5uU6*jJY0/vAtMsQk1*9MUIcdWlnxEHyflRLoDUpBwQ9O4fIhBP91egsMALYo0z56fz";

        if (reuniao.verificaObreiroNaListaPelaDigital(impressaoCadastradaProcurada)) {

            reuniao.desmarcarPresencaDeObreiroNaListaPelaDigital(impressaoCadastradaProcurada);

        }

        boolean obreiroPresente = reuniao.verificarObreiroEstevePresenteNaReuniaoPelaDigital(impressaoCadastradaProcurada);

        assertEquals(false, obreiroPresente);
    }

    @Test
    public void persistirPresencaDesmarcada() {
        String impressaoCadastradaProcurada = "AQAAABQAAADkAAAAAQASAAEAWgAAAAAA3AAAAP3z69XeWa03lS9qvZ*Stk5f2L6Wgq73t0KxQbnpJ4FSGRgers2UmkP4vajjG3TCoCruIFcjZYGZ1K2vIq9U7pG5DS3On6ptG7Xbw4oMBSycZTPiiGJpQZvkprTvk*MKdjxV0hoHFaw5BQMwdVnPAnFLxBBb5Aigr06vMLNu*RmXzIDmb6gHXT2jqa3MQ9y9GbUSGzy8t9Wg37ksreklmhvkNTaulsjcZQSm5CW4w5uU6*jJY0/vAtMsQk1*9MUIcdWlnxEHyflRLoDUpBwQ9O4fIhBP91egsMALYo0z56fz";

        if (reuniao.verificaObreiroNaListaPelaDigital(impressaoCadastradaProcurada)) {

            reuniao.desmarcarPresencaDeObreiroNaListaPelaDigital(impressaoCadastradaProcurada);

        }

        boolean obreiroPresente = reuniao.verificarObreiroEstevePresenteNaReuniaoPelaDigital(impressaoCadastradaProcurada);

        dataAccessObjectManager.mergeDataObjeto(reuniao);

        assertEquals(true, dataAccessObjectManager.isOperacaoEfetuada());
    }

    @Test
    public void verificaObreiroNaoPresenteNaReuniao() {
        String impressaoCadastradaProcurada = "AQAAABQAAADkAAAAAQASAAEAWgAAAAAA3AAAAP3z69XeWa03lS9qvZ*Stk5f2L6Wgq73t0KxQbnpJ4FSGRgers2UmkP4vajjG3TCoCruIFcjZYGZ1K2vIq9U7pG5DS3On6ptG7Xbw4oMBSycZTPiiGJpQZvkprTvk*MKdjxV0hoHFaw5BQMwdVnPAnFLxBBb5Aigr06vMLNu*RmXzIDmb6gHXT2jqa3MQ9y9GbUSGzy8t9Wg37ksreklmhvkNTaulsjcZQSm5CW4w5uU6*jJY0/vAtMsQk1*9MUIcdWlnxEHyflRLoDUpBwQ9O4fIhBP91egsMALYo0z56fz";
        boolean obreiroPresente = reuniao.verificarObreiroEstevePresenteNaReuniaoPelaDigital(impressaoCadastradaProcurada);

        assertEquals(false, obreiroPresente);
    }

    //Executar depois de remover a presenca
    @Test
    public void verificarQuantidadeObreirosPresentesNaReuniaoAposRemocao(){
        int qtdPresentes = reuniao.obterTotalDePresentesNaReuni�o();

        assertEquals(0, qtdPresentes);
    }

}