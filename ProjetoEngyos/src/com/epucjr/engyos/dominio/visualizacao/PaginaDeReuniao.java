/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.dominio.visualizacao;

import com.epucjr.engyos.tecnologia.utilitarios.DateTimeUtils;

/**
 *
 * @author Rogerio
 */
public class PaginaDeReuniao {

    private long idReuniao;
    private String data;
    private String hora;
    private String minuto;
    private String segundo;
    private String horaFinal;
    private String minutoFinal;
    private String dia;
    private String mes;
    private String ano;
    private String local;
    private String tempoDeInicio;
    private String tempoFinal;
    private String mensagemDeAviso;
  
    public PaginaDeReuniao() {
        this.idReuniao = 0;
        this.data = "";
        this.hora = "";
        this.minuto = "";
        this.minuto = "";
        this.segundo = "";
        this.horaFinal = "";
        this.minutoFinal = "";
        this.dia = "";
        this.mes = "";
        this.ano = "";
        this.local = "";
        this.tempoDeInicio = "";
        this.tempoFinal = "";
        this.mensagemDeAviso = "";
    }

    public void carregarDadosDaPagina(String data, String local, String horario){
        this.setData(data);
        this.setTempoDeInicio(horario);
        this.setLocal(local);

        this.setMensagemDeAviso(mensagemDeAviso);

        this.setDia(DateTimeUtils.obterDiaDeDataBrasileira(data));
        this.setMes(DateTimeUtils.obterMesDeDataBrasileira(data));
        this.setAno(DateTimeUtils.obterAnoDeDataBrasileira(data));

        this.setHora(DateTimeUtils.obterHora(horario));
        this.setMinuto(DateTimeUtils.obterMinuto(horario));
        this.setSegundo(DateTimeUtils.obterSegundo(horario));
       
    }

    public void carregarDadosDaPagina(String data, String local, String horarioDeInicio, long idReuniao){
        this.setData(data);
        this.setTempoDeInicio(horarioDeInicio);
        this.setLocal(local);
        this.setTempoDeInicio(horarioDeInicio);
               

        this.setDia(DateTimeUtils.obterDiaDeDataBrasileira(data));
        this.setMes(DateTimeUtils.obterMesDeDataBrasileira(data));
        this.setAno(DateTimeUtils.obterAnoDeDataBrasileira(data));

        this.setHora(DateTimeUtils.obterHora(horarioDeInicio));
        this.setMinuto(DateTimeUtils.obterMinuto(horarioDeInicio));
        this.setSegundo(DateTimeUtils.obterSegundo(horarioDeInicio));

        this.setIdReuniao(idReuniao);

    }

    public long getIdReuniao() {
        return idReuniao;
    }

    private void setIdReuniao(long idReuniao) {
        this.idReuniao = idReuniao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
   
    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getMensagemDeAviso() {
        return mensagemDeAviso;
    }

    public void setMensagemDeAviso(String mensagemDeAviso) {
        this.mensagemDeAviso = mensagemDeAviso;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getMinuto() {
        return minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
    }

    public String getMinutoFinal() {
        return minutoFinal;
    }

    public void setMinutoFinal(String minutoFinal) {
        this.minutoFinal = minutoFinal;
    }

    public String getSegundo() {
        return segundo;
    }

    public void setSegundo(String segundo) {
        this.segundo = segundo;
    }

    public String getTempoDeInicio() {
        return tempoDeInicio;
    }

    public void setTempoDeInicio(String tempoDeInicio) {
        this.tempoDeInicio = tempoDeInicio;
    }

    public String getTempoFinal() {
        return tempoFinal;
    }

    public void setTempoFinal(String tempoFinal) {
        this.tempoFinal = tempoFinal;
    }

}
