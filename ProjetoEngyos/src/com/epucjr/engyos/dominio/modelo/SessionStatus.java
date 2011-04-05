package com.epucjr.engyos.dominio.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *Classe responsável pelo gerenciamento de sessões de usuários de maneira persistente
 *
 * @author Projeto Engyos Team
 */

@Entity
public class SessionStatus implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSessionStatus;

    @Transient
    public static String SECAO_ATIVA = "SECAO_ATIVA";
    @Transient
    public static String SECAO_INATIVA = "SECAO_ATIVA";

    private boolean sessaoAtiva;

    private String CURRENT_SESSION_ID;

    public SessionStatus() {
        this.idSessionStatus = 0;
        this.sessaoAtiva = false;
        this.CURRENT_SESSION_ID = "";
    }

    /**
     * Define o status de uma sessão, baseada em SECAO_ATIVA ou SECAO_INATIVA
     *
     * @param sessaoStatus Assume dois valores : SECAO_ATIVA ou SECAO_INATIVA
     *
     * TODO Exception se sessaoStatus for diferente de SECAO_ATIVA ou SECAO_INATIVA
     */
    public void definirSessaoStatus(String sessaoStatus){
        if(sessaoStatus.equals(SECAO_ATIVA)){
            this.sessaoAtiva = true;
        }
        else if(sessaoStatus.equals(SECAO_INATIVA)){
            this.sessaoAtiva = false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SessionStatus other = (SessionStatus) obj;
        if (this.idSessionStatus != other.idSessionStatus) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.idSessionStatus ^ (this.idSessionStatus >>> 32));
        return hash;
    }

    public boolean isSessaoAtiva(){
        return this.sessaoAtiva;
    }

    public long getIdSessionStatus() {
        return idSessionStatus;
    }

    public String getCURRENT_SESSION_ID() {
        return CURRENT_SESSION_ID;
    }

    public void setCURRENT_SESSION_ID(String CURRENT_SESSION_ID) {
        this.CURRENT_SESSION_ID = CURRENT_SESSION_ID;
    }

}
