/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.tecnologia.dao;

import com.epucjr.engyos.dominio.modelo.Administrador;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Rogerio
 */
public class AdministradorDAO implements IAdministradorDAO{
    private static org.apache.log4j.Logger log = Logger.getLogger(AdministradorDAO.class);
    private String mensagemStatus;
    private boolean operacaoExecutada;
    private DataAccessObjectManager dataAccessObjectManager;

    public AdministradorDAO() {
        this.mensagemStatus = "";
        this.operacaoExecutada = false;
        this.dataAccessObjectManager = new DataAccessObjectManager();

    }



    @Override
    public void delete(String cpfAdministrador) {
        log.debug("apagando: " + cpfAdministrador);
        if(this.dataAccessObjectManager.isAdmnistradorExistente(cpfAdministrador)){
            Administrador administrador = this.dataAccessObjectManager.obterAdministrador(cpfAdministrador);
            this.dataAccessObjectManager.deletarObjeto(administrador);
            this.setOperacaoExecutada(this.dataAccessObjectManager.isOperacaoEfetuada());
            this.setMensagemStatus(this.dataAccessObjectManager.getMensagemStatus());
        }
        else{
            this.setOperacaoExecutada(true);
            this.setMensagemStatus("Usuário inexistente no sistema.");
        }

        this.dataAccessObjectManager.fecharEntityManager();
        log.debug("apagado: " + this.isOperacaoExecutada());
        log.debug("mensagem: " + this.getMensagemStatus());
    }

    @Override
    public List<Administrador> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Administrador findByPrimaryKey(String cpfAdministrador) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insert(Administrador administrador) {
        this.dataAccessObjectManager.persistirAdmnistrador(administrador);
        this.setOperacaoExecutada(this.dataAccessObjectManager.isOperacaoEfetuada());
        this.setMensagemStatus(this.dataAccessObjectManager.getMensagemStatus());

        log.debug("inserido: " + this.isOperacaoExecutada());
        log.debug("mensagem: " + this.getMensagemStatus());
    }

    @Override
    public boolean isAdministradorExistente(String cpfAdministrador) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Administrador administrador) {
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



}
