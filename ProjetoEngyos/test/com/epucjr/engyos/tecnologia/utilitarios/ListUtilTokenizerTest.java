/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.tecnologia.utilitarios;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author rogerio
 */
public class ListUtilTokenizerTest {

    @Test
    public void obterListaPelaStringToken(){
        String token = "Praça%Arvore%Estado%Pais";
       
        List<String> listaObtida = ListUtilTokenizer.obterListaString(token);
        long tamanhoLista = listaObtida.size();

        assertEquals(4, tamanhoLista);
    }

     @Test
    public void obterListaPelaStringComPadraPreDefinido(){
        String token = "Praça%Arvore%Estado%Pais";
       String padrao = "%";
        List<String> listaObtida = ListUtilTokenizer.obterListaString(token, padrao);
        long tamanhoLista = listaObtida.size();

        assertEquals(4, tamanhoLista);
    }

    @Test
    public void obterListaPelaStringComEscapeCharDefinidos() {
        String token = "Praça\tArvore\nEstado\rPais\fAno%Last";
        
        List<String> listaObtida = ListUtilTokenizer.obterListaString(token);
        long tamanhoLista = listaObtida.size();

        assertEquals(6, tamanhoLista);
    }


}
