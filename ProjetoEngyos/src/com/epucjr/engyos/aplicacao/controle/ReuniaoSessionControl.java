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
import org.apache.log4j.Logger;

/**
 *
 * @author Rogerio
 */
public class ReuniaoSessionControl extends MainSessionControl{
    private static org.apache.log4j.Logger log = Logger.getLogger(ReuniaoMonitor.class);
    
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
        log.debug("aplicacao.controle.ReuniaoSessionControl#criarEDefinirDadosSessaoDeReuniao(long idReuniao)");
        //1. Usa o dataAccessObjectManager para controle de sessão usando o banco de dados para reconhecimento de
        //sessão através da rede
        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();

        Reuniao reuniao = dataAccessObjectManager.obterReuniao(idReuniao);
        ReuniaoSessionStatus reuniaoSessionStatus = reuniao.getReuniaoSessionStatus();

        //1.9Define a identificação deste usuário através de sua sessão
        //mudar para add
        reuniaoSessionStatus.setCurrentUserSessionID(this.session.getId());

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
        log.debug("aplicacao.controle.ReuniaoSessionControl#iniciarReuniao");
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

    public void reiniciarReuniao() {
        log.debug("aplicacao.controle.ReuniaoSessionControl#reiniciarReuniao");
        ReuniaoSessionStatus reuniaoSessionStatus = (ReuniaoSessionStatus) session.getAttribute(REUNIAO_SESSION_STATUS);
        DataAccessObjectManager dataAccessObjectManager = null;
        if (!reuniaoSessionStatus.isSessaoAtiva()) {
            dataAccessObjectManager = new DataAccessObjectManager();
            reuniaoSessionStatus.definirSessaoStatus(ReuniaoSessionStatus.SECAO_ATIVA);           

            //Reunião já iniciada, obtendo o tempo já definido
            Date dateStart = reuniaoSessionStatus.getSESSION_START_TIME();
            Date dateEnd = reuniaoSessionStatus.getSESSION_END_TIME();
            Date date = null;

            //é reinicio mano
            //novo tempo de inicio de reunião
            long diferencaComecoFim = dateEnd.getTime() - dateStart.getTime();
            Date tempoCorrente = new Date();
            long novoTempoInicio = tempoCorrente.getTime() - diferencaComecoFim;
            log.debug("aplicacao.controle.ReuniaoSessionControl#reiniciarReuniao -  novoTempoInicio : " + novoTempoInicio);

            Date tempoInicioNovo = new Date();
            tempoInicioNovo.setTime(novoTempoInicio);
            reuniaoSessionStatus.setSESSION_START_TIME(tempoInicioNovo);

            //this.definirAtributoNaSessao(REUNIAO_SESSION_TIME_START, date.getTime());
            //Marca o tempo decorrido da seção
            //Date tempoDecorrido = DateTimeUtils.calcularTempoDecorrido(date.getTime());

            //reuniaoSessionStatus.setTempoDecorrido(tempoDecorrido);

            //UPDATE Session Status, seção ATIVA/INATIVA
            dataAccessObjectManager.mergeDataObjeto(reuniaoSessionStatus);

            //Fechando o EntityManager de DataAccessObjectManager após uso
            //6. TODO if !dataAccessObjectManager.isOperacaoExecutada throws Exception
        }
        if (dataAccessObjectManager != null) {
            dataAccessObjectManager.fecharEntityManager();
        }
    }

    public void inicializarSessaoUsuarioCorrenteReuniaoJaIniciada() {
        log.debug("aplicacao.controle.ReuniaoSessionControl#inicializarSessaoUsuarioCorrenteReuniaoJaIniciada");
        ReuniaoSessionStatus reuniaoSessionStatus = (ReuniaoSessionStatus) session.getAttribute(REUNIAO_SESSION_STATUS);
        //DataAccessObjectManager dataAccessObjectManager = null;
        if (reuniaoSessionStatus.isSessaoAtiva()) {
            //dataAccessObjectManager = new DataAccessObjectManager();
            //reuniaoSessionStatus.definirSessaoStatus(ReuniaoSessionStatus.SECAO_ATIVA);

            //Reunião já iniciada, obtendo o tempo já definido
            //Aqui não preciso verificar o Time End porque é uma reunião já iniciada em curso
            Date date = reuniaoSessionStatus.getSESSION_START_TIME();
            //TODO - verificar o seu uso e remover
            this.definirAtributoNaSessao(REUNIAO_SESSION_TIME_START, date.getTime());
            //Marca o tempo decorrido da seção
            Date tempoDecorrido = DateTimeUtils.calcularTempoDecorrido(date.getTime());

            reuniaoSessionStatus.setTempoDecorrido(tempoDecorrido);

            //UPDATE Session Status, seção ATIVA/INATIVA
            //dataAccessObjectManager.mergeDataObjeto(reuniaoSessionStatus);

            //Fechando o EntityManager de DataAccessObjectManager após uso
//            if (dataAccessObjectManager != null) {
//                dataAccessObjectManager.fecharEntityManager();
//            }

            //6. TODO if !dataAccessObjectManager.isOperacaoExecutada throws Exception

        }
    }

    /**
     * 
     * @param idReuniao
     * @return
     */
    public boolean verificarReuniaoValida(long idReuniao){
        log.debug("aplicacao.controle.ReuniaoSessionControl#verificarReuniaoValida(long idReuniao)");
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

    public boolean isSessaoDeReuniaoMinha(){
        log.debug("aplicacao.controle.ReuniaoSessionControl#isSessaoDeReuniaoMinha");
        ReuniaoSessionStatus reuniaoSessionStatus = (ReuniaoSessionStatus) session.getAttribute(REUNIAO_SESSION_STATUS);
        String reuniaoSessionStatusSoredId = reuniaoSessionStatus.getCurrentUserSessionID();
        String idReuniao = this.session.getId();
        boolean sessaoReuniaoEMinha = false;
        if(reuniaoSessionStatusSoredId != null && reuniaoSessionStatusSoredId.equals(idReuniao)){
            sessaoReuniaoEMinha = true;
        }

        return sessaoReuniaoEMinha;

    }

    public void fecharSessaoReuniao(){
        log.debug("aplicacao.controle.ReuniaoSessionControl#fecharSessaoReuniao");
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
        log.debug("aplicacao.controle.ReuniaoSessionControl#encerrarSessao");
        this.removerAtributoNaSessao(REUNIAO_SESSION_STATUS);
        this.removerAtributoNaSessao(ID_REUNIAO);
        this.removerAtributoNaSessao(REUNIAO_SESSION_TIME_START);


    }



     /**
      * Sobrecarga de método de verificarSessionStatusAtiva() para
      * auxiliar no controle de sessão de reunião
      * Método dependente da ssession a fim de evitar a concorrência
      * de banco de dados, mantendo obrigatória a criação de uma sessão
      * para um usuário especifico.
      *
      * //TODO: Refatorar para melhorar de modo a não precisar deste e
      * revisar o método verificarSessaoReuniaoAberta()
      *
      * @param nomeDaSessao
      * @return
      */
    @Override
     public boolean verificarSessionStatusAtiva(){
         log.debug("aplicacao.controle.ReuniaoSessionControl#verificarSessionStatusAtiva");
         ReuniaoSessionStatus sessionStatus = (ReuniaoSessionStatus) session.getAttribute(REUNIAO_SESSION_STATUS);
         if(sessionStatus != null && sessionStatus.isSessaoAtiva()){
            return true;
        }
        else{
            return false;
        }
        
    }

    /**
     *
     * @return
     */
    public boolean verificarSessaoJaPreviamenteIniciada(){
        log.debug("aplicacao.controle.ReuniaoSessionControl#verificarSessaoJaPreviamenteIniciada");
        ReuniaoSessionStatus reuniaoSessionStatus = (ReuniaoSessionStatus) session.getAttribute(REUNIAO_SESSION_STATUS);
        Date momentoInicio = reuniaoSessionStatus.getSESSION_START_TIME();
        boolean reuniaoPreviamenteIniciada = false;

        if(momentoInicio != null){
            reuniaoPreviamenteIniciada = true;
        }

        return reuniaoPreviamenteIniciada;
    }
    /**
     * 
     * @return
     */
    public Date obtemTempoParcialReuniaoIniciada(){
        log.debug("aplicacao.controle.ReuniaoSessionControl#obtemTempoParcialReuniaoIniciada");
        ReuniaoSessionStatus reuniaoSessionStatus = (ReuniaoSessionStatus) session.getAttribute(REUNIAO_SESSION_STATUS);
        Date momentoInicio = reuniaoSessionStatus.getSESSION_START_TIME();
        Date momentoEncerrado = reuniaoSessionStatus.getSESSION_END_TIME();
        if(momentoEncerrado != null){
            //calcula o tempo corrido da reunião em relação a hora atual
            //pega o tempo corrente
            Date date = new Date();
            long tempoCorrenteEmMili = date.getTime();
            long tempoInicioReuniaoEmMili = momentoEncerrado.getTime();
            long tempoDecorrido = tempoCorrenteEmMili - tempoInicioReuniaoEmMili;
            Date tempoDecorridoDate = new Date();
            tempoDecorridoDate.setTime(tempoDecorrido);
            return tempoDecorridoDate;
        }
        else if(momentoInicio != null){
            Date date = new Date();
            long tempoCorrenteEmMili = date.getTime();
            long tempoInicioReuniaoEmMili = momentoInicio.getTime();
            long tempoDecorrido = tempoCorrenteEmMili - tempoInicioReuniaoEmMili;
            Date tempoDecorridoDate = new Date();
            tempoDecorridoDate.setTime(tempoDecorrido);
            return tempoDecorridoDate;
        }
        else{
            return null;
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
