/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.tecnologia.dao;

import com.epucjr.engyos.dominio.modelo.Reuniao;
import java.util.List;

/**
 *
 * @author Rogerio
 */
public interface IReuniaoDAO {

    public void insert(Reuniao reuniao);
    public void update(Reuniao reuniao);
    public void delete(long idReuniao);
    public List<Reuniao> findAll();
    public Reuniao findByPrimaryKey(long idReuniao);
    public boolean isIdReuniaoExistente(long idReuniao);

}
