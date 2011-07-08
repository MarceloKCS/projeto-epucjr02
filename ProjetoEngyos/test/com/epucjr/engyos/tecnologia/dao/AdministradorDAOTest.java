package com.epucjr.engyos.tecnologia.dao;

import com.epucjr.engyos.dominio.modelo.Administrador;
import com.epucjr.engyos.dominio.modelo.Identificacao;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Rogerio
 */
public class AdministradorDAOTest {

    private AdministradorDAO administradorDAO = new AdministradorDAO();

    @Test
    public void insertTest(){

        Administrador administrador = new Administrador("Josué da Silva", "123456", new Identificacao("abc123"));
        this.administradorDAO.insert(administrador);
        boolean operacaoExecutada = this.administradorDAO.isOperacaoExecutada();

        assertEquals(true, operacaoExecutada);

    }

    @Test
    public void remove(){
        this.administradorDAO.delete("123456");
        boolean operacaoExecutada = this.administradorDAO.isOperacaoExecutada();
        assertEquals(true, operacaoExecutada);

    }
}
