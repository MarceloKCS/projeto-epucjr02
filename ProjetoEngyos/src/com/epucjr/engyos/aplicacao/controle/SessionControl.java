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

    public void definirSessionStatusAtivaInativa(String nomeDaSessao, boolean status);

    public boolean verificarSessionStatusAtiva();

    public boolean verificarValidadeDaSessao(String nomeDaSessao);

    public void definirAtributoNaSessao(String nomeDoCampo, Object valorDoCampo);

    public Object obterAtributoDaSessao(String nomeDoCampo);

    public boolean verificaAtributoExistente(String nomeDoCampo);

    public boolean isSessaoAtiva(String nomeDaSessao);

    public void removerAtributoNaSessao(String nomeDoAtributo);

    public void encerrarSessao();

}
