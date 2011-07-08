package com.epucjr.engyos.tecnologia.dao;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rogerio
 */
public class CongregacaoDAOTest {
    
    CongregacaoDAO congregacaoDAO = new CongregacaoDAO();

    @Test
    public void insertTest(){
        Congregacao congregacao = new Congregacao("Congregacao do Jardim Eliana", "Jardim Eliana");
        Obreiro obreiro1 = new Obreiro("Castor Marroto", "Nerd", "591", congregacao, "abc123");
        Obreiro obreiro2 = new Obreiro("Castor Marroto II", "Nerds", "592", congregacao, "abc123");

        congregacao.addObreiro(obreiro1);
        congregacao.addObreiro(obreiro2);

        congregacao.setCongregacaoPadrao(false);
        this.congregacaoDAO.insert(congregacao);
        boolean operacaoExecutada = this.congregacaoDAO.isOperacaoExecutada();

        assertEquals(true, operacaoExecutada);
    }

    @Test
    public void isConregacaoPadraoDefinidaTrueTest(){
        boolean congregacaoDefinida = this.congregacaoDAO.isConregacaoPadraoDefinida();

        assertTrue(congregacaoDefinida);
    }

    @Test
     public void obteCongregacaoPadraoNotNullTest(){
         Congregacao congregacao = this.congregacaoDAO.obteCongregacaoPadrao();

         if(congregacao != null){
             System.out.println("Nome: " + congregacao.getNome());
         }

         assertNotNull(congregacao);
    }

    @Test
    public void removeTest(){
        Congregacao congregacao = this.congregacaoDAO.obterCongregacaoPeloNome("Congregacao do Jardim Eliana");
        this.congregacaoDAO.delete(congregacao.getIdCongregacao());
        boolean operacaoExecutada = this.congregacaoDAO.isOperacaoExecutada();

        assertEquals(true, operacaoExecutada);
    }

    @After
    public void encerraEntityManagerTest(){
        this.congregacaoDAO.fecharEntityManager();
    }
//    @Test
//    public void isConregacaoPadraoDefinidaFalseTest(){
//        boolean congregacaoDefinida = this.congregacaoDAO.isConregacaoPadraoDefinida();
//
//        assertFalse(congregacaoDefinida);
//    }
//
//    @Test
//     public void obteCongregacaoPadraoNullTest(){
//         Congregacao congregacao = this.congregacaoDAO.obteCongregacaoPadrao();
//
//         assertNull(congregacao);
//    }

}
