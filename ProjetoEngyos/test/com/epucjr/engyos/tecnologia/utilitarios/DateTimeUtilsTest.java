package com.epucjr.engyos.tecnologia.utilitarios;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Projeto Engyos
 */
public class DateTimeUtilsTest {

    @Test
    public void confirmarDataVerificadaMaiorQueDiaAtual() {
        String dataFornecida = "25/12/2011";

        boolean dataMaior = DateTimeUtils.verificarDataMaiorQueDataCorrente(dataFornecida);

        assertTrue(dataMaior);

    }

    @Test
    public void confirmarDataVerificadaMenorQueDiaAtual() {
        String dataFornecida = "25/01/2011";

        boolean dataMaior = DateTimeUtils.verificarDataMaiorQueDataCorrente(dataFornecida);

        assertFalse(dataMaior);

    }
}
