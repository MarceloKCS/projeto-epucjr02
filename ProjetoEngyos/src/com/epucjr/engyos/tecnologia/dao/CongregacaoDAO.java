/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.tecnologia.dao;

import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author Rogerio
 */
public class CongregacaoDAO implements ICongregacaoDAO{

    private static org.apache.log4j.Logger log = Logger.getLogger(CongregacaoDAO.class);

    private String mensagemStatus;
    private boolean operacaoExecutada;
    private DataAccessObjectManager dataAccessObjectManager;
    private EntityManager entityManager;

    public CongregacaoDAO() {
        this.mensagemStatus = "";
        this.operacaoExecutada = false;
        this.dataAccessObjectManager = new DataAccessObjectManager();
        this.entityManager = dataAccessObjectManager.getEntityManager();
    }

    @Override
    public void delete(long idCongregacao) {
        if(this.dataAccessObjectManager.isCongregacaoExistente(idCongregacao)){
            Congregacao congregacao = this.dataAccessObjectManager.obterCongregacao(idCongregacao);
            this.dataAccessObjectManager.deletarObjeto(congregacao);
            this.setOperacaoExecutada(this.dataAccessObjectManager.isOperacaoEfetuada());
            this.setMensagemStatus(this.dataAccessObjectManager.getMensagemStatus());
        }
        else{
            this.setOperacaoExecutada(true);
            this.setMensagemStatus("Congregacao inexistente no sistema.");
        }

    }

    @Override
    public List<Congregacao> findAll() {
        List<Congregacao> listaDeCongregacoesCadastradas = null;

        listaDeCongregacoesCadastradas = dataAccessObjectManager.obterListaDeCongregacoes();
   
        if(!dataAccessObjectManager.isOperacaoEfetuada()){
            return null;
        }

        return listaDeCongregacoesCadastradas;
    }

    @Override
    public Congregacao findByPrimaryKey(long idCongregacao) {
        Congregacao congregacao = this.dataAccessObjectManager.obterCongregacao(idCongregacao);
        this.setMensagemStatus(this.dataAccessObjectManager.getMensagemStatus());
        this.setOperacaoExecutada(this.dataAccessObjectManager.isOperacaoEfetuada());
        return congregacao;
    }

    @Override
    public void insert(Congregacao congregacao) {
        boolean congregacaoExiste = this.dataAccessObjectManager.isCongregacaoExistente(congregacao.getIdCongregacao());

        if(!congregacaoExiste){
            try {
                this.dataAccessObjectManager.persistir(congregacao);
                this.setMensagemStatus("Congregação inserida com sucesso!");
                this.setOperacaoExecutada(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                this.setMensagemStatus("Erro interno na operação");
                this.setOperacaoExecutada(false);
            }
        }
        else{
            this.setMensagemStatus("Já existe congregção com este identificador.");
            this.setOperacaoExecutada(false);
        }

    }

    @Override
    public void update(Congregacao congregacao) {
        this.dataAccessObjectManager.mergeDataObjeto(congregacao);
        this.setMensagemStatus(this.dataAccessObjectManager.getMensagemStatus());
        this.setOperacaoExecutada(this.dataAccessObjectManager.isOperacaoEfetuada());
    }

    @Override
    public boolean isCongregacaoExistente(long idCongregacao) {
        boolean congregacaoExiste = this.dataAccessObjectManager.isCongregacaoExistente(idCongregacao);
        return congregacaoExiste;
    }

    @Override
    public boolean isConregacaoPadraoDefinida() {
        String congregacaoPadraoDefinida = "";
        EntityManager entityManager = this.dataAccessObjectManager.getEntityManager();
        Query query = entityManager.createQuery("Select 'TRUE' as verdade from Congregacao where congregacaoPadrao=:arg1");
        query.setParameter("arg1", true);
        try{
            congregacaoPadraoDefinida = (String) query.getSingleResult();
        }catch(NoResultException ex){
            return false;
        }
        log.debug("CongregacaoDefinida = " + congregacaoPadraoDefinida);        
        if(congregacaoPadraoDefinida != null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Congregacao obteCongregacaoPadrao() {
        EntityManager entityManager = this.dataAccessObjectManager.getEntityManager();
        Query query = entityManager.createQuery("Select con from Congregacao con where con.congregacaoPadrao=:arg1");
        query.setParameter("arg1", true);
        Congregacao congregacao = null;
        try{
            congregacao = (Congregacao) query.getSingleResult();
        }catch(NoResultException ex){
            return congregacao;
        }

        return congregacao;
    }

    public Congregacao obterCongregacaoPeloNome(String nomeCongregacao){
        EntityManager entityManager = this.dataAccessObjectManager.getEntityManager();
        Query query = entityManager.createQuery("Select co from Congregacao co where co.nome=:arg1");
        query.setParameter("arg1", nomeCongregacao);
        Congregacao congregacao = (Congregacao) query.getSingleResult();
        return congregacao;
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

    



}
