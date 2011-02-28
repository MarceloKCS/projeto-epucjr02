/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.dominio.crud;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rogerio
 */
public class ValidadorCPFTest {

    @Test
    public void verificarCPFValido(){
        String cpfValido =  "91401924158";

        ValidatorCpf validatorCpf = new ValidatorCpf();

        boolean isCpfValido = validatorCpf.valido(cpfValido);

        assertTrue(isCpfValido);

    }

    @Test
    public void verificarCPFInvalido(){
        String cpfInvalido = "58965877789";

        ValidatorCpf validatorCpf = new ValidatorCpf();

        boolean isCPFValido = validatorCpf.valido(cpfInvalido);

        assertFalse(isCPFValido);
    }

}
