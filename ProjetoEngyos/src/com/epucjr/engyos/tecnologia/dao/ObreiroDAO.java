package com.epucjr.engyos.tecnologia.dao;

import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;
/**
 *
 * @author Rogerio
 */
public class ObreiroDAO implements IObreiroDAO{
    private static org.apache.log4j.Logger log = Logger.getLogger(ObreiroDAO.class);
    private String mensagemStatus;
    private boolean operacaoExecutada;
    private DataAccessObjectManager dataAccessObjectManager;
    private EntityManager entityManager;

    public ObreiroDAO() {
        this.mensagemStatus = "";
        this.operacaoExecutada = false;
        this.dataAccessObjectManager = new DataAccessObjectManager();
        this.dataAccessObjectManager = new DataAccessObjectManager();
        this.entityManager = dataAccessObjectManager.getEntityManager();
    }

    public ObreiroDAO(EntityManager entityManager) {
        this.mensagemStatus = "";
        this.operacaoExecutada = false;
        this.dataAccessObjectManager = new DataAccessObjectManager();
        this.dataAccessObjectManager = new DataAccessObjectManager();
        this.entityManager = entityManager;
    }



    @Override
    public void delete(String cpfObreiro) {
        log.debug("apagando: " + cpfObreiro);
        if(this.dataAccessObjectManager.isObreiroExistente(cpfObreiro)){
            Obreiro obreiro = this.dataAccessObjectManager.obterObreiro(cpfObreiro);
            this.dataAccessObjectManager.deletarObjeto(obreiro);
            this.setOperacaoExecutada(this.dataAccessObjectManager.isOperacaoEfetuada());
            this.setMensagemStatus(this.dataAccessObjectManager.getMensagemStatus());
        }else{
            this.setOperacaoExecutada(true);
            this.setMensagemStatus("Obreiro inexistente no sistema.");
        }
        log.debug("apagado: " + this.isOperacaoExecutada());
        log.debug("mensagem: " + this.getMensagemStatus());
    }

    @Override
    public List<Obreiro> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Obreiro findByPrimaryKey(String cpfObreiro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insert(Obreiro obreiro) {
        this.dataAccessObjectManager.persistirObreiro(obreiro);
        this.setOperacaoExecutada(this.dataAccessObjectManager.isOperacaoEfetuada());
        this.setMensagemStatus(this.dataAccessObjectManager.getMensagemStatus());

        log.debug("inserido: " + this.isOperacaoExecutada());
        log.debug("mensagem: " + this.getMensagemStatus());
    }

     public void fecharEntityManager(){
        if(this.entityManager != null && this.getEntityManager().isOpen()){
            this.getEntityManager().close();
        }
    }

    @Override
    public boolean isAObreiroExistente(String cpfObreiro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Obreiro obreiro) {
        throw new UnsupportedOperationException("Not supported yet.");
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

    public EntityManager getEntityManager() {
        if (this.entityManager == null || !this.entityManager.isOpen()) {
            this.entityManager = dataAccessObjectManager.getEntityManager();
        }
        return this.entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }




}
