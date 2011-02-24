package com.epucjr.engyos.aplicacao.controle;

import com.epucjr.engyos.dominio.modelo.IReuniao;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.dominio.visualizacao.PaginaDeReuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import com.epucjr.engyos.tecnologia.utilitarios.DateTimeUtils;

/**
 *
 * @author Projeto Engyos Team
 */
public class ReuniaoMonitor implements IReuniaoMonitor{

    private PaginaDeReuniao paginaDeReuniao;
    private boolean operacaoExecutada;
    private String mensagemStatus;
    private IReuniao reuniao;

    public ReuniaoMonitor(IReuniao reuniao) {
            paginaDeReuniao = new PaginaDeReuniao();
            this.reuniao = reuniao;
            this.operacaoExecutada = false;
             this.mensagemStatus = "";
    }



    /**
     * Construtor com a função de inicializar o atributo Reunião pelo id
     *
     * @pre Uma reunião da referida Id deverá estar previamente cadastrada no
     * banco de dados
     *
     * @param idReuniao O id da reunião a ser carregada
     */
     public ReuniaoMonitor(long idReuniao) {
            paginaDeReuniao = new PaginaDeReuniao();
            this.operacaoExecutada = false;
            this.mensagemStatus = "";
            this.setReuniao(this.carregarReuniao(idReuniao));


    }

     private IReuniao carregarReuniao(long idReuniao){
         
         DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
         
         if(idReuniao != 0){
             return dataAccessObjectManager.obterReuniao(idReuniao);
         }
         else{
             return new Reuniao();
         }
     }

    @Override
    public void inicializaReuniao() {

        //verificar se a requisição para o carregamento da seção de reunião
        //seja de uma reunião que não passe, pelo menos, o dia de hoje

        if(DateTimeUtils.verificarDataMaiorQueDataCorrente(this.getReuniao().getData())){
            this.paginaDeReuniao.carregarDadosDaPagina(this.reuniao.getData() , this.reuniao.getLocal(), this.reuniao.getHorario(), this.reuniao.getIdReuniao());
            this.setOperacaoExecutada(true);
            this.setMensagemStatus("Reuniao Inicializada");
        }
        else{
            this.setOperacaoExecutada(false);
            this.setMensagemStatus("Erro na Inicialização");
            this.paginaDeReuniao.setMensagemDeAviso(this.getMensagemStatus());
            
        }
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

    private void setOperacaoExecutada(boolean operacaoExecutada) {
        this.operacaoExecutada = operacaoExecutada;
    }



    private IReuniao getReuniao() {
        return reuniao;
    }

    private void setReuniao(IReuniao reuniao) {
        this.reuniao = reuniao;
    }

    @Override
    public String getMensagemStatus() {
        return mensagemStatus;
    }

    public void setMensagemStatus(String mensagemStatus) {
        this.mensagemStatus = mensagemStatus;
    }


}
