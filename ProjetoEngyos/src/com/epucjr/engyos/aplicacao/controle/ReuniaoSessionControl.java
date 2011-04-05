/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.controle;

import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.dominio.modelo.ReuniaoSessionStatus;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
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

    public ReuniaoSessionControl(HttpSession session) {
        super(session);
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

    public void criarEDefinirDadosSessaoDeReuniao(long idReuniao){
        //1. Usa o dataAccessObjectManager para controle de sessão usando o banco de dados para reconhecimento de
        //sessão através da rede
        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();

        Reuniao reuniao = dataAccessObjectManager.obterReuniao(idReuniao);
        ReuniaoSessionStatus reuniaoSessionStatus = reuniao.getReuniaoSessionStatus();

        //2. Define os dados do usuário na sessão
        
        this.definirAtributoNaSessao(ID_REUNIAO, idReuniao);

        //3. Define os dados para condição de atividade da sessão
        reuniaoSessionStatus.setCURRENT_SESSION_ID(this.session.getId());        
        

        //4. Coloca na sessão para consulta destes dados de forma mais rápida
        this.definirAtributoNaSessao(REUNIAO_SESSION_STATUS, reuniaoSessionStatus);

        //5. Persiste no banco para consulta na rede.
        dataAccessObjectManager.mergeDataObjeto(reuniaoSessionStatus);

       //6. TODO if !dataAccessObjectManager.isOperacaoExecutada throws Exception
    }

    /**
     *
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

            //6. TODO if !dataAccessObjectManager.isOperacaoExecutada throws Exception

        }
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
            reuniaoSessionStatus.setCURRENT_SESSION_ID("");
            this.encerrarSessao();

            dataAccessObjectManager.mergeDataObjeto(reuniaoSessionStatus);

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

}
