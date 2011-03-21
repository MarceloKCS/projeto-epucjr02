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
     * Cria e define uma sess�o de usu�rio a aprtir de dados essesciais para o controle de usu�rio.
     * � utilizado o banco de dados para uma persist~encia tempor�ria dos dados apenas para reconhecimendo
     * da atividade da sess�o atrav�s da rede. � um tema que precisa ser discutido e aperfei�oado, quanto a real necessidade
     * da utiliza��o do banco de dados
     *
     * @param nomeDoUsuario O nome de usu�rio que efetuou o login
     * @param cpf A identifica��o do usu�rio
     * @param sessionStatus A sess�o, com a condi��o definida em inativa,  pertencente a
     * um Administrador que efetuar� um login
     */
    public void criarEDefinirDadosSessaoDeUsuario(String nomeDoUsuario, String cpf, SessionStatus sessionStatus){
        //1. Usa o dataAccessObjectManager para controle de sess�o usando o banco de dados para reconhecimento de
        //sess�o atrav�s da rede
        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();

        //2. Define os dados do usu�rio na sess�o
        this.definirAtributoNaSessao(NOME_USUARIO, nomeDoUsuario);
        this.definirAtributoNaSessao(ID_USUARIO, cpf);

        //3. Define os dados para condi��o de atividade da sess�o
        sessionStatus.setCURRENT_SESSION_ID(this.session.getId());
        sessionStatus.definirSessaoStatus(SessionStatus.SECAO_ATIVA);

        //4. Coloca na sess�o para consulta destes dados de forma mais r�pida
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
