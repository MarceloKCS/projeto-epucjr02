/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.tecnologia.dao;

import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Rogerio
 */
public class ReuniaoDAO implements IReuniaoDAO{
    private static org.apache.log4j.Logger log = Logger.getLogger(ObreiroDAO.class);
    private String mensagemStatus;
    private boolean operacaoExecutada;
    private DataAccessObjectManager dataAccessObjectManager;
    private EntityManager entityManager;

    public ReuniaoDAO() {
        this.mensagemStatus = "";
        this.operacaoExecutada = false;
        this.dataAccessObjectManager = new DataAccessObjectManager();
        this.entityManager = dataAccessObjectManager.getEntityManager();
    }

     public ReuniaoDAO(EntityManager entityManager) {
        this.mensagemStatus = "";
        this.operacaoExecutada = false;
        this.dataAccessObjectManager = new DataAccessObjectManager();
        this.entityManager = entityManager;
    }



    @Override
    public void delete(long idReuniao) {
        log.debug("apagando: " + idReuniao);

         if(this.dataAccessObjectManager.isReuniaoExistente(idReuniao)){
             Reuniao reuniao = this.dataAccessObjectManager.obterReuniao(idReuniao);
             this.dataAccessObjectManager.deletarObjeto(reuniao);
             this.setOperacaoExecutada(this.dataAccessObjectManager.isOperacaoEfetuada());
         }else{
            this.setOperacaoExecutada(true);
            this.setMensagemStatus("Reunião inexistente no sistema.");
        }
        log.debug("apagado: " + this.isOperacaoExecutada());
        log.debug("mensagem: " + this.getMensagemStatus());
    }

    @Override
    public List<Reuniao> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Reuniao findByPrimaryKey(long idReuniao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insert(Reuniao reuniao) {
        boolean reuniaoExiste = this.dataAccessObjectManager.isReuniaoExistente(reuniao.getIdReuniao());

        if(!reuniaoExiste){
            this.dataAccessObjectManager.persistir(reuniao);
            this.setOperacaoExecutada(true);
            this.setMensagemStatus("Reuniao inserida com sucesso.");
        }
        else{
            this.setOperacaoExecutada(false);
            this.setMensagemStatus("Já existe uma reunião com este identificador...");
        }

        log.debug("inserido: " + this.isOperacaoExecutada());
        log.debug("mensagem: " + this.getMensagemStatus());
    }

    @Override
    public boolean isIdReuniaoExistente(long idReuniao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Reuniao reuniao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

     public void fecharEntityManager(){
        if(this.entityManager != null && this.getEntityManager().isOpen()){
            this.getEntityManager().close();
        }
    }

    public EntityManager getEntityManager() {
        if (this.entityManager == null || !this.entityManager.isOpen()) {
            this.entityManager = dataAccessObjectManager.getEntityManager();
        }
        return this.entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public String getMensagemStatus() {
        return mensagemStatus;
    }

    public void setMensagemStatus(String mensagemStatus) {
        this.mensagemStatus = mensagemStatus;
    }

    public boolean isOperacaoExecutada() {
        return operacaoExecutada;
    }

    public void setOperacaoExecutada(boolean operacaoExecutada) {
        this.operacaoExecutada = operacaoExecutada;
    }



}
