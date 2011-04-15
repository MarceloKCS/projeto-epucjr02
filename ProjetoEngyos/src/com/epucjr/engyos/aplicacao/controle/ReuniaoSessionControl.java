/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.controle;

import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.dominio.modelo.ReuniaoSessionStatus;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import com.epucjr.engyos.tecnologia.utilitarios.DateTimeUtils;
import java.util.Date;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rogerio
 */
public class ReuniaoSessionControl extends MainSessionControl{
    
    private final String REUNIAO_SESSION_TIME_START = "REUNIAO_SESSION";
    private final String ID_REUNIAO = "ID_REUNIAO";
    public static final String REUNIAO_SESSION_STATUS = "REUNIAO_SESSION_STATUS";
    private final long tempoMaxReuniaoEmMilissegundos = 86400000L;
    private String messageStatus;
    /**
     * Construtor, recebe a sessão em uso pelo usuário no gerenciamente de uma
     * sessão de reunião
     *
     * @param session A sessão corrente de um usuário no sistema
     */
    public ReuniaoSessionControl(HttpSession session) {
        super(session);
        this.messageStatus = "";
    }

    public ReuniaoSessionControl() {
        super(null);
        this.messageStatus = "";
    }

    /**
     * A ser apagado na versão final
     *
     * @deprecated Utilizar o seguinte método no lugar:
     * @see #criarEDefinirDadosSessaoDeReuniao(long, long, com.epucjr.engyos.dominio.modelo.ReuniaoSessionStatus)
     */
    @Deprecated
    public void criarSessaoDeReuniao(){
        if(!this.verificaAtributoExistente(REUNIAO_SESSION_TIME_START)){
            this.definirSessionStatusAtivaInativa(REUNIAO_SESSION_TIME_START, false);
        }
    }

    /**
     * Método que cria e define uma sessão de reunião através de uma reunião escolhida,
     * previamente cadastrada no sistema
     *
     * @param idReuniao  O ID da reunião escolhida para inicio da sessão
     */
    public void criarEDefinirDadosSessaoDeReuniao(long idReuniao){
        //1. Usa o dataAccessObjectManager para controle de sessão usando o banco de dados para reconhecimento de
        //sessão através da rede
        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();

        Reuniao reuniao = dataAccessObjectManager.obterReuniao(idReuniao);
        ReuniaoSessionStatus reuniaoSessionStatus = reuniao.getReuniaoSessionStatus();

        //2. Define os dados do usuário na sessão
        
        this.definirAtributoNaSessao(ID_REUNIAO, idReuniao);        

        //4. Coloca na sessão para consulta destes dados de forma mais rápida
        this.definirAtributoNaSessao(REUNIAO_SESSION_STATUS, reuniaoSessionStatus);

        //5. Persiste no banco para consulta na rede.
        dataAccessObjectManager.mergeDataObjeto(reuniaoSessionStatus);

        //Fechando o EntityManager de DataAccessObjectManager após uso
        if (dataAccessObjectManager != null) {
            dataAccessObjectManager.fecharEntityManager();
        }

       //6. TODO if !dataAccessObjectManager.isOperacaoExecutada throws Exception
    }

    /**
     * Método que tem como objetivo iniciar uma sessão de reunião préviamente criada
     * através de <code>criarEDefinirDadosSessaoDeReuniao(long idReuniao)</code>
     *
     *@pre Uma reunião deverá estar previamente criada e definida
     *
     * @see #criarEDefinirDadosSessaoDeReuniao(long) 
     */
    public void iniciarReuniao() {
        ReuniaoSessionStatus reuniaoSessionStatus = (ReuniaoSessionStatus) session.getAttribute(REUNIAO_SESSION_STATUS);
        DataAccessObjectManager dataAccessObjectManager = null;
        if (!reuniaoSessionStatus.isSessaoAtiva()) {
            dataAccessObjectManager = new DataAccessObjectManager();
            reuniaoSessionStatus.definirSessaoStatus(ReuniaoSessionStatus.SECAO_ATIVA);

            Date date = new Date();
            reuniaoSessionStatus.setSESSION_START_TIME(date);

            this.definirAtributoNaSessao(REUNIAO_SESSION_TIME_START, date.getTime());
            dataAccessObjectManager.mergeDataObjeto(reuniaoSessionStatus);

            //Fechando o EntityManager de DataAccessObjectManager após uso
            if (dataAccessObjectManager != null) {
                dataAccessObjectManager.fecharEntityManager();
            }

            //6. TODO if !dataAccessObjectManager.isOperacaoExecutada throws Exception

        }
    }
    /**
     * 
     * @param idReuniao
     * @return
     */
    public boolean verificarReuniaoValida(long idReuniao){
         DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
         Reuniao reuniao = dataAccessObjectManager.obterReuniao(idReuniao);
         boolean reuniaoValida = true;
         //Verifica se a reunião está ativa, então será dispendido esforço para
         //verificar se ela está no prazo
         System.out.println("Current Reuniao Status : " + reuniao.getReuniaoStatus());
        if (reuniao.getReuniaoStatus().equals(Reuniao.REUNIAO_ATIVA)) {
            //Verifica se a reunião está dentro do prazo minimo estipulado nesta classe em "tempoMaxReuniaoEmMilissegundos".
            //Que está representando um tempo de 24 de atraso do seu agendamento
            long momentoAgendamentoReuniaoEmMilis = DateTimeUtils.converterDateTimeToMilissegundos(reuniao.getData(), reuniao.getHorario());
            Date date = new Date();
            long tempoAtual = date.getTime();
            long diferenca = tempoAtual - momentoAgendamentoReuniaoEmMilis;

            if (diferenca > tempoMaxReuniaoEmMilissegundos) {
                reuniao.setReuniaoStatus(Reuniao.REUNIAO_INATIVA);
                dataAccessObjectManager.mergeDataObjeto(reuniao);
                this.setMessageStatus("Reuniao expirou o seu tempo mínimo de início...");
                reuniaoValida =  false;
            } 
        }
        else{
             reuniaoValida = false;
             this.setMessageStatus("Reuniao préviamente encerrada e invalidada.");
        }

         //Fechando o EntityManager de DataAccessObjectManager após uso
        if (dataAccessObjectManager != null) {
            dataAccessObjectManager.fecharEntityManager();
        }

         return reuniaoValida;
        
    }

    public void fecharSessaoReuniao(){
        DataAccessObjectManager dataAccessObjectManager = null;
        if(this.verificarSessionStatusAtiva()){
            ReuniaoSessionStatus reuniaoSessionStatus = (ReuniaoSessionStatus) this.obterAtributoDaSessao(REUNIAO_SESSION_STATUS);
            dataAccessObjectManager = new DataAccessObjectManager();
            reuniaoSessionStatus.definirSessaoStatus(ReuniaoSessionStatus.SECAO_INATIVA);
            Date date = new Date();
            //date.setTime(sessionTimeEnd);
            reuniaoSessionStatus.setSESSION_END_TIME(date);           
            this.encerrarSessao();

            dataAccessObjectManager.mergeDataObjeto(reuniaoSessionStatus);

            //Fechando o EntityManager de DataAccessObjectManager após uso
            if (dataAccessObjectManager != null) {
                dataAccessObjectManager.fecharEntityManager();
            }

            //6. TODO if !dataAccessObjectManager.isOperacaoExecutada throws Exception
        }
    }

    @Override
    public void encerrarSessao() {

        this.removerAtributoNaSessao(REUNIAO_SESSION_STATUS);
        this.removerAtributoNaSessao(ID_REUNIAO);
        this.removerAtributoNaSessao(REUNIAO_SESSION_TIME_START);


    }



     /**
      * Sobrecarga de método de verificarSessionStatusAtiva() para
      * auxiliar no controle de sessão de reunião
      *
      * //TODO: Refatorar para melhorar de modo a não precisar deste e
      * revisar o método verificarSessaoReuniaoAberta()
      *
      * @param nomeDaSessao
      * @return
      */
    @Override
     public boolean verificarSessionStatusAtiva(){

         ReuniaoSessionStatus sessionStatus = (ReuniaoSessionStatus) session.getAttribute(REUNIAO_SESSION_STATUS);
         if(sessionStatus != null && sessionStatus.isSessaoAtiva()){
            return true;
        }
        else{
            return false;
        }
        
    }

    public void armazenaIdReuniaoCorrente(String idReuniao){
        if(!this.verificaAtributoExistente(this.ID_REUNIAO)){
            this.definirAtributoNaSessao(this.ID_REUNIAO, idReuniao);
        }
    }

    public String obterIdReuniaoCorrente(){
        if(this.verificaAtributoExistente(ID_REUNIAO)){
            return this.obterAtributoDaSessao(ID_REUNIAO).toString();
        }
        else{
            return "";
        }
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

}
