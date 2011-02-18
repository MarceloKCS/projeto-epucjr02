package com.epucjr.engyos.dominio.modelo;

import java.util.List;

/**
 * Interface que define o conjunto de opera��es obrigat�rias para uma
 * reuni�o no sistema
 * @author Projeto Engyos Team
 */
public interface IReuniao {

    public void adicionarObreiroNaListaDePresenca(Obreiro obreiro);

    public boolean verificaObreiroNaLista(Obreiro obreiro);

    public boolean verificaObreiroNaListaPelaSenha(String senhaObreiro);

    public boolean verificaObreiroNaListaPelaDigital(String digitalObreiro);

    public boolean verificaObreiroNaListaPeloCPF(String cpfObreiro);

    public void marcarPresencaDeObreiroNaListaPelaSenha(String senhaObreiro);

    public void marcarPresencaDeObreiroNaListaPeloCPF(String senhaObreiro);

    public void marcarPresencaDeObreiroNaListaPelaDigital(String digitalObreiro);

    public void definirNovaListaDePresenca(List<PresencaObreiro> listaDePresencaObreiro);

    public int obterTotalDePresentesNaReuni�o();

    public int obterQuantidadeTotalDeObreirosNaLista();

    public boolean verificarObreiroEstevePresenteNaReuniao(String cpf);

    public boolean verificarObreiroEstevePresenteNaReuniaoPelaSenha(String senha);

    //public para teste, alterar para private

    public Obreiro buscarObreiroNaListaDePresenca(List<PresencaObreiro> listaDePresencaObreiro, String valorCPFProcurado, int left, int right);

    public List<PresencaObreiro> getListaDePresencaObreiro() ;

    public Obreiro buscarObreiroNaListaDePresenca(String digitalObreiro);
}
