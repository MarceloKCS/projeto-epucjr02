package com.epucjr.engyos.aplicacao.controle;

import com.epucjr.engyos.dominio.modelo.IReuniao;
import com.epucjr.engyos.dominio.visualizacao.PaginaDeReuniao;

/**
 *
 * @author Projeto Engyos Team
 */
public class ReuniaoMonitor implements IReuniaoMonitor{

    private PaginaDeReuniao paginaDeReuniao;
    private boolean operacaoExecutada;


    public ReuniaoMonitor() {
            paginaDeReuniao = new PaginaDeReuniao();
            this.operacaoExecutada = false;
    }

    @Override
    public void inicializaReuniao(IReuniao reuniao) {

       this.paginaDeReuniao.carregarDadosDaPagina(reuniao.getData() , reuniao.getLocal(), reuniao.getHorario());

       
    }

    @Override
    public PaginaDeReuniao obterPaginaDeReuniaoInicializada() {
        throw new UnsupportedOperationException("Not supported yet.");
    }



    @Override
    public void marcarPresencaPelaDigital() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void marcarPresencaPeloCPF() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isOperacaoExecutada() {
        return operacaoExecutada;
    }

}
