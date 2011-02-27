/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.controle;

/**
 *
 * @author Rogerio
 */
public interface SessionControl {

    public void criarFlagSessionStatus(String nomeDaSessao, boolean status);

    public boolean obterFlagSessionStatus(String nomeDaSessao);

    public boolean obterValidadeSessao(String nomeDaSessao);

    public void definirAtributoNaSessao(String nomeDoCampo, String valorDoCampo);

    public Object obterAtributoDaSessao(String nomeDoCampo);

    public boolean verificaAtributoExistente(String nomeDoCampo);

    public boolean isSessaoFlagAtiva(String nomeDaSessao);

    public void encerrarSessao(String nomeDaSessao);

    public void matarTodasSessoes();

}
