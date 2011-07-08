/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.tecnologia.dao;

import com.epucjr.engyos.dominio.modelo.Administrador;
import java.util.List;

/**
 *
 * @author Rogerio
 */
public interface IAdministradorDAO {

    public void insert(Administrador administrador);
    public void update(Administrador administrador);
    public void delete(String cpfAdministrador);
    public List<Administrador> findAll();
    public Administrador findByPrimaryKey(String cpfAdministrador);
    public boolean isAdministradorExistente(String cpfAdministrador);

}
