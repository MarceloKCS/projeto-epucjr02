/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.dominio.visualizacao;

import com.epucjr.engyos.dominio.modelo.Congregacao;
import org.junit.Before;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import java.util.LinkedList;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author rogerio
 */
public class FormularioDeObreiroTest {

    Mockery context = new JUnit4Mockery();
    FormularioDeObreiro formObreiro;
    DataAccessObjectManager dao;

    @Before
    public void setup(){
        dao = context.mock(DataAccessObjectManager.class);
       // formObreiro = new FormularioDeObreiro(dao);
    }

    @Test
    public void carregarListaDeCongregacaoMudaMensagemDeStatus(){
        //especificar expectativas de colaboração
        context.checking(new Expectations(){
            {
                oneOf(dao).obterListaDeCongregacoes();
                will(returnValue(new LinkedList<Congregacao>()));
            }
        });

        //invocar operação testada
        formObreiro.carregarDadosDoFormulario();

        //validação / assert
        assertThat("Mensagem do formulario",
                    formObreiro.getMensagemStatus(),
                    containsString("Formulario Carregado"));
        
    }

}
