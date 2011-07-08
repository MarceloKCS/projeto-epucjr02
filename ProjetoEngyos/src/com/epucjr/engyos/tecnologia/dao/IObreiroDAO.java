/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.tecnologia.dao;

import com.epucjr.engyos.dominio.modelo.Obreiro;
import java.util.List;

/**
 *
 * @author Rogerio
 */
public interface IObreiroDAO {

    public void insert(Obreiro obreiro);
    public void update(Obreiro obreiro);
    public void delete(String cpfObreiro);
    public List<Obreiro> findAll();
    public Obreiro findByPrimaryKey(String cpfObreiro);
    public boolean isAObreiroExistente(String cpfObreiro);

}
