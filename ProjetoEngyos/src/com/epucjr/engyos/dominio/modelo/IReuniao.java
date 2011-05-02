package com.epucjr.engyos.dominio.modelo;

import java.util.List;

/**
 * Interface que define o conjunto de operações obrigatórias para uma
 * reunião no sistema
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

    public int obterTotalDePresentesNaReunião();

    public int obterQuantidadeTotalDeObreirosNaLista();

    public boolean verificarObreiroEstevePresenteNaReuniao(String cpf);

    public boolean verificarObreiroEstevePresenteNaReuniaoPelaSenha(String senha);

    public boolean verificarObreiroEstevePresenteNaReuniaoPelaDigital(String digitalObreiro);

    public void desmarcarPresencaDeObreiroNaListaPelaDigital(String digitalObreiro);

    public void desmarcarPresencaDeObreiroNaLista(String cpf);

    public Obreiro obterObreiroDaListaPeloCPF(String cpfObreiro);

    public Obreiro obterObreiroDaListaPelaDigital(String impressaoDigital);

    public String getDia();

    public String getMes();

    public String getAno();

    public String getMinuto();

    public String getLocal();

    public String getData();

    public String getHorario();

    public long getIdReuniao();

    public String getReuniaoStatus();  

    public int getQuantidadeMaxObreirosReuniao();

    public int getTempoEmMinutosDeTolerânciaContagemPresenca();

    public int obterTempoDeDuracaoReuniao();

    public ReuniaoSessionStatus getReuniaoSessionStatus();

    //public para teste, alterar para private

    public Obreiro buscarObreiroNaListaDePresenca(List<PresencaObreiro> listaDePresencaObreiro, String valorCPFProcurado);

    public List<PresencaObreiro> getListaDePresencaObreiro() ;

    public Obreiro buscarObreiroNaListaDePresenca(String digitalObreiro);

   
}
