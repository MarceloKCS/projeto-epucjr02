package com.epucjr.engyos.aplicacao.controle;

import com.epucjr.engyos.dominio.modelo.IReuniao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.dominio.modelo.ReuniaoSessionStatus;
import com.epucjr.engyos.dominio.visualizacao.PaginaDeReuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author Projeto Engyos Team
 */
public class ReuniaoMonitor implements IReuniaoMonitor{
    private static org.apache.log4j.Logger log = Logger.getLogger(ReuniaoMonitor.class);

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
     * Construtor com a fun��o de inicializar o atributo Reuni�o pelo id
     *
     * @pre Uma reuni�o da referida Id dever� estar previamente cadastrada no
     * banco de dados
     *
     * @param idReuniao O id da reuni�o a ser carregada
     */
     public ReuniaoMonitor(long idReuniao) {
            paginaDeReuniao = new PaginaDeReuniao();
            this.operacaoExecutada = false;
            this.mensagemStatus = "";
            this.setReuniao(this.carregarReuniao(idReuniao));


    }

     private IReuniao carregarReuniao(long idReuniao){
         if(idReuniao != 0){
             DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
             IReuniao reuniao = dataAccessObjectManager.obterReuniao(idReuniao);
             
             //Fechando o EntityManager de DataAccessObjectManager ap�s uso
             if (dataAccessObjectManager != null) {
                 dataAccessObjectManager.fecharEntityManager();
             }

             return reuniao;
         }
         else{
             return new Reuniao();
         }
     }

    @Override
    public void reuniaoLoader() {
        log.debug("aplicacao.controle.ReuniaoMonitor#reuniaoLoader");
        ReuniaoSessionStatus reuniaoSessionStatus = this.reuniao.getReuniaoSessionStatus();
        Date tempoInicioReuniao = reuniaoSessionStatus.getSESSION_START_TIME();
        Date tempoFimReuniao = reuniaoSessionStatus.getSESSION_END_TIME();
        long tempoDecorrido = 0;
        if(!reuniaoSessionStatus.isSessaoAtiva() && tempoFimReuniao != null && tempoInicioReuniao != null){
            log.debug("aplicacao.controle.ReuniaoMonitor#reuniaoLoader - calculando o tempo, de uma reuni�o n�o ativa, mas j� foi iniciada");
            tempoDecorrido = tempoFimReuniao.getTime() - tempoInicioReuniao.getTime();
        }
        else if(tempoInicioReuniao != null){
            log.debug("aplicacao.controle.ReuniaoMonitor#reuniaoLoader - calculando o tempo para o caso de j� existir uma reuni�o ativa");
            tempoDecorrido = (new Date().getTime()) - tempoInicioReuniao.getTime();
        }
        else{
            log.debug("aplicacao.controle.ReuniaoMonitor#reuniaoLoader - reuni�o nova, tempo n�o calculado");
        }
        log.debug("aplicacao.controle.ReuniaoMonitor#reuniaoLoader - tempoDecorrido = " + tempoDecorrido);
        //verificar se a requisi��o para o carregamento da se��o de reuni�o
        //seja de uma reuni�o que n�o passe, pelo menos, o dia de hoje

        //if(DateTimeUtils.verificarDataMaiorQueDataCorrente(this.getReuniao().getData())){
            this.paginaDeReuniao.carregarDadosDaPagina(this.reuniao.getData() , this.reuniao.getLocal(), this.reuniao.getHorario(), tempoDecorrido, this.reuniao.getIdReuniao());
            this.setOperacaoExecutada(true);
            this.setMensagemStatus("Reuniao Inicializada");
       // }
       // else{
         //   this.setOperacaoExecutada(false);
        //    this.setMensagemStatus("Erro na Inicializa��o");
        //    this.paginaDeReuniao.setMensagemDeAviso(this.getMensagemStatus());
            
      //  }
    }

    @Override
    public PaginaDeReuniao obterPaginaDeReuniaoInicializada() {
        return this.paginaDeReuniao;
    }



    @Override
    public void marcarPresencaPelaDigital(String impressaoDigital) {

        //Para persist�ncia imediata da presen�a pois a reuni�o n�o est� persistente na se��o
        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
        if (this.reuniao.verificaObreiroNaListaPelaDigital(impressaoDigital)) {
            if (!this.reuniao.verificarObreiroEstevePresenteNaReuniaoPelaDigital(impressaoDigital)) {
                this.reuniao.marcarPresencaDeObreiroNaListaPelaDigital(impressaoDigital);
                Obreiro obreiroPresente = this.reuniao.obterObreiroDaListaPelaDigital(impressaoDigital);

                dataAccessObjectManager.mergeDataObjeto(this.reuniao);

                if (dataAccessObjectManager.isOperacaoEfetuada()) {
                    this.setMensagemStatus("Presen�a de " + obreiroPresente.getNome() + " marcada");
                    this.setOperacaoExecutada(true);
                } else {
                    this.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
                    this.setOperacaoExecutada(false);
                }
            } else {
                Obreiro obreiroPresente = this.reuniao.obterObreiroDaListaPelaDigital(impressaoDigital);
                this.setMensagemStatus("Presen�a de " + obreiroPresente.getNome() + " j� foi marcada");
            }

        } else {
            this.setMensagemStatus("Obreiro n�o se encontra na lista...");
            this.setOperacaoExecutada(false);
        }

        //Fechando o EntityManager de DataAccessObjectManager ap�s uso
        if (dataAccessObjectManager != null) {
            dataAccessObjectManager.fecharEntityManager();
        }
    }

    /**
     * Marca presen�a de obreiro - verifica��es > Overhead :-(
     * @param cpfObreiro O cpf do obreiro
     *
     * @see DataAccessObjectManager#mergeDataObjeto(java.lang.Object)
     * @see Obreiro
     * @see Reuniao#verificaObreiroNaListaPeloCPF(java.lang.String)
     * @see Reuniao#verificarObreiroEstevePresenteNaReuniao(java.lang.String)
     * @see Reuniao#marcarPresencaDeObreiroNaListaPeloCPF(java.lang.String) 
     */
    @Override
    public void marcarPresencaPeloCPF(String cpfObreiro) {

        //Para persist�ncia imediata da presen�a pois a reuni�o n�o est� persistente na se��o

        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();

        if (this.reuniao.verificaObreiroNaListaPeloCPF(cpfObreiro)) {

            if(!this.reuniao.verificarObreiroEstevePresenteNaReuniao(cpfObreiro)){
                this.reuniao.marcarPresencaDeObreiroNaListaPeloCPF(cpfObreiro);

                Obreiro obreiroPresente = this.reuniao.obterObreiroDaListaPeloCPF(cpfObreiro);

                dataAccessObjectManager.mergeDataObjeto(this.reuniao);

                if(dataAccessObjectManager.isOperacaoEfetuada()){
                    this.setMensagemStatus("Presen�a de " + obreiroPresente.getNome() + " marcada");
                    this.setOperacaoExecutada(true);
                }
                else{
                    this.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
                    this.setOperacaoExecutada(false);
                }

                
            }
            else{
                Obreiro obreiroPresente = this.reuniao.obterObreiroDaListaPeloCPF(cpfObreiro);
                this.setMensagemStatus("Presen�a de " + obreiroPresente.getNome() + " j� foi marcada");
            }

            
        }
        else{
            this.setMensagemStatus("Obreiro n�o se encontra na lista...");
            this.setOperacaoExecutada(false);
        }

        //Fechando o EntityManager de DataAccessObjectManager ap�s uso
        if (dataAccessObjectManager != null) {
            dataAccessObjectManager.fecharEntityManager();
        }

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
