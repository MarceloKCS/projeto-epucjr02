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
    private IReuniao reuniao;

    public ReuniaoMonitor(IReuniao reuniao) {
            paginaDeReuniao = new PaginaDeReuniao();
            this.reuniao = reuniao;
            this.operacaoExecutada = false;
    }

    @Override
    public void inicializaReuniao() {

       this.paginaDeReuniao.carregarDadosDaPagina(this.reuniao.getData() , this.reuniao.getLocal(), this.reuniao.getHorario(), this.reuniao.getIdReuniao());

       
    }

    @Override
    public PaginaDeReuniao obterPaginaDeReuniaoInicializada() {
        return this.paginaDeReuniao;
    }



    @Override
    public void marcarPresencaPelaDigital(String impressaoDigital) {


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
