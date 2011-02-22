package com.epucjr.engyos.aplicacao.controle;

import com.epucjr.engyos.dominio.modelo.IReuniao;
import com.epucjr.engyos.dominio.visualizacao.PaginaDeReuniao;

/**
 *
 * @author Projeto Engyos Team
 */

public interface IReuniaoMonitor {
    public void marcarPresencaPelaDigital(String impressaoDigital);

    public void marcarPresencaPeloCPF();

    public void inicializaReuniao();

    public PaginaDeReuniao obterPaginaDeReuniaoInicializada();

    public boolean isOperacaoExecutada();
}
