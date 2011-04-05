/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.dominio.modelo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *Classe responsável pelo gerenciamento de sessões de reuniões de maneira persistente
 *
 * @author Projeto Engyos Team
 */
@Entity
public class ReuniaoSessionStatus implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReuniaoSessionStatus;

    @Transient
    public static String SECAO_ATIVA = "SECAO_ATIVA";
    @Transient
    public static String SECAO_INATIVA = "SECAO_ATIVA";

    private boolean sessaoAtiva;

    /**
     * O Id da sessão utilizada em HttpSession
     */
    private String CURRENT_SESSION_ID;

    @Temporal(TemporalType.TIMESTAMP)
    private Date SESSION_START_TIME;

    @Temporal(TemporalType.TIMESTAMP)
    private Date SESSION_END_TIME;
    /**
     * Construtor
     */
    public ReuniaoSessionStatus() {
        this.idReuniaoSessionStatus = 0;
        this.sessaoAtiva = false;
        this.CURRENT_SESSION_ID = "";
        this.SESSION_START_TIME = null;
        this.SESSION_END_TIME = null;
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
        final ReuniaoSessionStatus other = (ReuniaoSessionStatus) obj;
        if (this.idReuniaoSessionStatus != other.idReuniaoSessionStatus) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.idReuniaoSessionStatus ^ (this.idReuniaoSessionStatus >>> 32));
        return hash;
    }

    


    public String getCURRENT_SESSION_ID() {
        return CURRENT_SESSION_ID;
    }

    public void setCURRENT_SESSION_ID(String CURRENT_SESSION_ID) {
        this.CURRENT_SESSION_ID = CURRENT_SESSION_ID;
    }

    public Date getSESSION_START_TIME() {
        return SESSION_START_TIME;
    }

    public void setSESSION_START_TIME(Date SESSION_START_TIME) {
        this.SESSION_START_TIME = SESSION_START_TIME;
    }

    public long getIdReuniaoSessionStatus() {
        return idReuniaoSessionStatus;
    }

    public void setIdReuniaoSessionStatus(long idReuniaoSessionStatus) {
        this.idReuniaoSessionStatus = idReuniaoSessionStatus;
    }

    public boolean isSessaoAtiva() {
        return sessaoAtiva;
    }

    public Date getSESSION_END_TIME() {
        return SESSION_END_TIME;
    }

    public void setSESSION_END_TIME(Date SESSION_END_TIME) {
        this.SESSION_END_TIME = SESSION_END_TIME;
    }

    

}
