package com.epucjr.engyos.tecnologia.dao;

import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Rogerio
 */
public class ObreiroDAOTest {
    private ObreiroDAO obreiroDAO = new ObreiroDAO();
    private CongregacaoDAO congregacaoDAO = new CongregacaoDAO();
    private DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
    

    @Test
    public void insertTest(){

        //Congregacao congregacao = new Congregacao("Congregacao Imaginacao", "Rua da sua cabeca, 1111");
        Congregacao congregacao = dataAccessObjectManager.obterCongregacao(25);
        Obreiro obreiro = new Obreiro("Castor Marroto", "Nerd", "591", congregacao, "abc123");

        //this.obreiroDAO.insert(obreiro);
        dataAccessObjectManager.persistirObreiro(obreiro);
        boolean operacaoExecutada = dataAccessObjectManager.isOperacaoEfetuada();
        dataAccessObjectManager.fecharEntityManager();
        assertEquals(true, operacaoExecutada);
    }

     @Test
    public void remove(){
         this.obreiroDAO.delete("591");
         boolean operacaoExecutada = this.obreiroDAO.isOperacaoExecutada();
         assertEquals(true, operacaoExecutada);
     }

}
