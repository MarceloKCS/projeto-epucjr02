package com.epucjr.engyos.aplicacao.controle;

import com.epucjr.engyos.dominio.modelo.SessionStatus;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rogerio
 */
public class UserSessionControl extends MainSessionControl{

    public static final String NOME_USUARIO = "NOME_USUARIO";
    public static final String ID_USUARIO = "ID_USUARIO";
    public static final String SESSION_STATUS = "SESSION_STATUS";
   

    public UserSessionControl(HttpSession session) {
        super(session);
    }

    /**
     * Cria e define uma sessão de usuário a aprtir de dados essesciais para o controle de usuário.
     * É utilizado o banco de dados para uma persist~encia temporária dos dados apenas para reconhecimendo
     * da atividade da sessão através da rede. É um tema que precisa ser discutido e aperfeiçoado, quanto a real necessidade
     * da utilização do banco de dados
     *
     * @param nomeDoUsuario O nome de usuário que efetuou o login
     * @param cpf A identificação do usuário
     * @param sessionStatus A sessão, com a condição definida em inativa,  pertencente a
     * um Administrador que efetuará um login
     */
    public void criarEDefinirDadosSessaoDeUsuario(String nomeDoUsuario, String cpf, SessionStatus sessionStatus){
        //1. Usa o dataAccessObjectManager para controle de sessão usando o banco de dados para reconhecimento de
        //sessão através da rede
        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();

        //2. Define os dados do usuário na sessão
        this.definirAtributoNaSessao(NOME_USUARIO, nomeDoUsuario);
        this.definirAtributoNaSessao(ID_USUARIO, cpf);

        //3. Define os dados para condição de atividade da sessão
        sessionStatus.setCURRENT_SESSION_ID(this.session.getId());
        sessionStatus.definirSessaoStatus(SessionStatus.SECAO_ATIVA);

        //4. Coloca na sessão para consulta destes dados de forma mais rápida
        this.definirAtributoNaSessao(SESSION_STATUS, sessionStatus);

        //5. Persiste no banco para consulta na rede.
        dataAccessObjectManager.mergeDataObjeto(sessionStatus);

       //6. TODO if !dataAccessObjectManager.isOperacaoExecutada throws Exception
    }

    public String obterDadoSessaoDoUsuario(String dadoRequisitado){
        return (String) this.obterAtributoDaSessao(dadoRequisitado);
    }

    public static boolean sessaoEstaAtiva(HttpSession httpSession){

        SessionStatus sessionStatus = (SessionStatus) httpSession.getAttribute(SESSION_STATUS);


        if(sessionStatus != null && sessionStatus.isSessaoAtiva()){
            return true;
        }
        else{
            return false;
        }
    }    
}
