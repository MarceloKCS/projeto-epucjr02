package com.epucjr.engyos.aplicacao.controle;

import com.epucjr.engyos.dominio.visualizacao.PaginaDeReuniao;

/**
 *
 * @author Projeto Engyos Team
 */

public interface IReuniaoMonitor {
    public void marcarPresencaPelaDigital(String impressaoDigital);

    public void marcarPresencaPeloCPF(String cpfObreiro);

    public void inicializaReuniao();

    public PaginaDeReuniao obterPaginaDeReuniaoInicializada();

    public boolean isOperacaoExecutada();

    public String getMensagemStatus();
}
